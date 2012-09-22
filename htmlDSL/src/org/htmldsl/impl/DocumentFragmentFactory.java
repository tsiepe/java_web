package org.htmldsl.impl;

import org.htmldsl.api.internal.IPrintable;
import org.htmldsl.api.internal.ITextContainer;

public class DocumentFragmentFactory implements ITextContainer<IPrintable> {

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

	public IPrintable createFragmentRoot(String elementName) {
		char firstChar = elementName.charAt(0);

		try {
			return (IPrintable) Class.forName(
					elementImplPackage
							+ elementName.toLowerCase().replaceFirst(
									"" + firstChar,
									"" + Character.toUpperCase(firstChar)))
					.newInstance();
		} catch (Throwable t) {
			throw new RuntimeException("Element '" + elementName
					+ "' not allowed as document fragment root.", t);
		}
	}

	public IPrintable text(String content) {
		return new Text(content);
	}
}
