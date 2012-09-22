package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Ihtml;

public class DocumentFactory {

	private static final DocumentFactory instance = new DocumentFactory();

	protected DocumentFactory() {
	}

	public static DocumentFactory getInstance() {
		return instance;
	}

	public Ihtml html(Map<IAttribute, String>... attributes) {
		return new Html(attributes);
	}
}
