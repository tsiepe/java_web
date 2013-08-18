package org.htmldsl.impl;

import java.util.HashMap;
import java.util.Map;

import org.htmldsl.api.internal.IPrintable;
import org.htmldsl.util.Utils;

public class DocumentFragmentFactory {

	private static final String elementImplPackage = DocumentFragmentFactory.class
			.getName().substring(
					0,
					DocumentFragmentFactory.class.getName().indexOf(
							DocumentFragmentFactory.class.getSimpleName()));

	private static DocumentFragmentFactory instance = new DocumentFragmentFactory();

	protected DocumentFragmentFactory() {
	}

	public static synchronized DocumentFragmentFactory getInstance() {
		if (null == instance) {
			instance = Utils.getInstance()
					.<DocumentFragmentFactory> getConfiguredInstanceForClass(
							DocumentFragmentFactory.class);
		}

		return instance;
	}

	@SuppressWarnings("unchecked")
	public <T extends IPrintable> T createFragmentRoot(
			Class<T> elementInterfaceClass) {
		T result = null;
		String simpleImplClassName = elementInterfaceClass.getSimpleName()
				.substring(1);

		if (elementInterfaceClass.isInterface()) {
			try {
				char firstChar = simpleImplClassName.charAt(0);
				result = (T) Class
						.forName(
								elementImplPackage
										+ simpleImplClassName
												.toLowerCase()
												.replaceFirst(
														"" + firstChar,
														""
																+ Character
																		.toUpperCase(firstChar)))
						.getDeclaredConstructor(Map[].class)
						.newInstance((Object) new HashMap[] {});
			} catch (Throwable t) {
				throw new RuntimeException("Element '" + simpleImplClassName
						+ "' not allowed as document fragment root.", t);
			}
		} else {
			throw new RuntimeException("Class '" + elementInterfaceClass
					+ "' is not a valid interface class.");
		}

		return result;
	}
}
