package jboss.deployer.handlers;

import static jboss.deployer.Activator.PLUGIN_ID;
import static jboss.deployer.preferences.JBossDeployerPreferencePage.STANDALONE_DEPLOYMENTS_PATH;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import jboss.deployer.Activator;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.locator.DefaultModelLocator;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ClassDeployHandler extends AbstractHandler {

	private static final String defaultTargetDir = "target", productName = "Jboss.deployer",
			productIconPath = "icons/product.gif", mavenNatureId = "org.eclipse.m2e.core.maven2Nature",
			defaultDeploymentLibDir = "~/JBoss-AS-7.1.1-Final/standalone/deployments/mareon-ear/lib",
			deployPrompt = "Start JBoss deployment?", projectPrefix = "Project ", at = " @ ", earLib = "lib",
			packagingJar = "jar", packagingEjb = "ejb", packagingEar = "ear", packagingWar = "war";

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (MessageDialog.openQuestion(HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell(), productName,
				deployPrompt)) {
			MessageConsole mc = new MessageConsole(PLUGIN_ID, Activator.getImageDescriptor(productIconPath));
			ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { mc });
			mc.activate();
			mc.clearConsole();

			try (MessageConsoleStream doc = mc.newMessageStream()) {
				doc.setActivateOnWrite(true);

				OUTER: for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
					doc.println(projectPrefix + project.getName() + at + project.getLocationURI().toString());

					try {
						if (project.hasNature(mavenNatureId) && project.isNatureEnabled(mavenNatureId)) {
							doc.println();

							MavenProject mvnProject = new MavenProject(new MavenXpp3Reader().read(new FileReader(
									new DefaultModelLocator().locatePom(new File(project.getLocationURI())))));

							String configuredPackaging = mvnProject.getPackaging();
							String outputDirectory = mvnProject.getBuild().getOutputDirectory();

							if (null == outputDirectory) {
								outputDirectory = project.getLocationURI() + File.separator + defaultTargetDir;
							}

							final File outputDirectoryFile = new File(new URI(outputDirectory));

							if (packagingEar.equals(configuredPackaging)) {
								// collect EAR contents
								Files.walkFileTree(outputDirectoryFile.toPath(), new SimpleFileVisitor<Path>() {

									@Override
									public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
											throws IOException {
										FileVisitResult result = super.visitFile(file, attrs);

										String fileName = file.getFileName().toString();
										String projectName = fileName.substring(0, fileName.lastIndexOf('.'));
										String[] fileNameParts = fileName.split("\\.");
										String fileExtension = fileNameParts[fileNameParts.length - 1];

										try {
											if (packagingWar.equals(fileExtension)) { // war
												copyEarArtifact(
														ResourcesPlugin.getWorkspace().getRoot()
																.getProject(projectName), fileName, doc);
											} else if (packagingJar.equals(fileExtension)) { // utility
																								// jar
																								// or
																								// ejb-jar
												IProject project = ResourcesPlugin.getWorkspace().getRoot()
														.getProject(projectName);
												MavenProject mvnProject = new MavenProject(new MavenXpp3Reader()
														.read(new FileReader(new DefaultModelLocator()
																.locatePom(new File(project.getLocationURI())))));

												copyEarArtifact(project,
														packagingEjb.equals(mvnProject.getPackaging()) ? null : earLib,
														doc);
											} else { // any other old file
												File outputFile = new File(Platform.getPreferencesService().getString(
														PLUGIN_ID, STANDALONE_DEPLOYMENTS_PATH,
														defaultDeploymentLibDir, null), outputDirectoryFile.toPath()
														.relativize(file).toString());
												outputFile.getParentFile().mkdirs();
												Files.copy(file, outputFile.toPath(),
														StandardCopyOption.REPLACE_EXISTING,
														StandardCopyOption.COPY_ATTRIBUTES);
											}
										} catch (XmlPullParserException | URISyntaxException e) {
											e.printStackTrace();
										}

										return result;
									}
								});

								break OUTER;
							}

						}
					} catch (CoreException | XmlPullParserException | URISyntaxException cox) {
						cox.printStackTrace();
					}

					doc.println();
				}

				doc.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private void copyEarArtifact(IProject project, String targetFile, MessageConsoleStream doc)
			throws FileNotFoundException, IOException, XmlPullParserException, URISyntaxException {
		String deployPath = Platform.getPreferencesService().getString(PLUGIN_ID, STANDALONE_DEPLOYMENTS_PATH,
				defaultDeploymentLibDir, null);
		final File deployFile = null != targetFile ? new File(deployPath, targetFile) : new File(deployPath);

		if (deployFile.isFile()) {
			deployFile.getParentFile().mkdirs();
		}

		MavenProject mvnProject = new MavenProject(new MavenXpp3Reader().read(new FileReader(new DefaultModelLocator()
				.locatePom(new File(project.getLocationURI())))));
		String outputDirectory = mvnProject.getBuild().getOutputDirectory();

		if (null == outputDirectory) {
			outputDirectory = project.getLocationURI() + File.separator + defaultTargetDir;
		}

		final File outputDirectoryFile = new File(new URI(outputDirectory));

		if (outputDirectoryFile.exists()) {
			Files.walkFileTree(outputDirectoryFile.toPath(), new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					FileVisitResult result = super.preVisitDirectory(dir, attrs);

					File newDir = new File(deployFile, outputDirectoryFile.toPath().relativize(dir).toString());

					if (!newDir.exists()) {
						newDir.mkdirs();
					}

					return result;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					FileVisitResult result = super.visitFile(file, attrs);

					if (!file.toString().endsWith(packagingWar)) {
						Files.copy(
								file,
								new File(deployFile, outputDirectoryFile.toPath().relativize(file).toString()).toPath(),
								StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
					}

					return result;
				}
			});
		}
	}
}
