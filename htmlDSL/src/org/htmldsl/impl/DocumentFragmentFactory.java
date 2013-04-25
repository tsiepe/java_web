package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.internal.IPrintable;

public class DocumentFragmentFactory {

	private static final String elementImplPackage = DocumentFragmentFactory.class
			.getName().substring(
					0,
					DocumentFragmentFactory.class.getName().indexOf(
							DocumentFragmentFactory.class.getSimpleName()));

	private static final DocumentFragmentFactory instance = new DocumentFragmentFactory();

	protected DocumentFragmentFactory() {
	}

	public static DocumentFragmentFactory getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public <T extends IPrintable> T createFragmentRoot(String elementName) {
		char firstChar = elementName.charAt(0);

		try {
			return (T) Class
					.forName(
							elementImplPackage
									+ elementName
											.toLowerCase()
											.replaceFirst(
													"" + firstChar,
													""
															+ Character
																	.toUpperCase(firstChar)))
					.getDeclaredConstructor(Map[].class).newInstance();
		} catch (Throwable t) {
			throw new RuntimeException("Element '" + elementName
					+ "' not allowed as document fragment root.", t);
		}
	}
}
