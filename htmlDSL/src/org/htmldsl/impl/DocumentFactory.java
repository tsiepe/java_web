package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Idoctype;
import org.htmldsl.api.Ihtml;
import org.htmldsl.util.Utils;

public class DocumentFactory {

	private static DocumentFactory instance = new DocumentFactory();

	protected DocumentFactory() {
	}

	public static synchronized DocumentFactory getInstance() {
		if (null == instance) {
			instance = Utils.getInstance()
					.<DocumentFactory> getConfiguredInstanceForClass(
							DocumentFactory.class);
		}

		return instance;
	}

	public Idoctype doctype(Idoctype.DoctypeKind kind) {
		return new Doctype(kind);
	}

	public Ihtml html(Map<IAttribute, String>... attributes) {
		return new Html(attributes);
	}
}
