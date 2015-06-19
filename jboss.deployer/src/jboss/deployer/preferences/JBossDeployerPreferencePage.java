package jboss.deployer.preferences;

import jboss.deployer.Activator;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class JBossDeployerPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String STANDALONE_DEPLOYMENTS_PATH = "STANDALONE_DEPLOYMENTS_PATH";

	public JBossDeployerPreferencePage() {
		super(GRID);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("JBoss Deployer Preferences Page");
	}

	@Override
	protected void createFieldEditors() {
		addField(new DirectoryFieldEditor(STANDALONE_DEPLOYMENTS_PATH, "Standalone Deployments &Directory:",
				getFieldEditorParent()));
	}
}
