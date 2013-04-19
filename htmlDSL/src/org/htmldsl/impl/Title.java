package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Ititle;
import org.htmldsl.api.internal.Constants;

class Title extends Element implements Ititle {

	Title(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	protected void handleAttributes(Map<IAttribute, String>... attributes) {
		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				attribute((TitleAttribute) attr, attributes[0].get(attr));
			}
		}
	}

	@Override
	public String startTag() {
		return "<title" + getAttributesAsString() + ">";
	}

	@Override
	public String endTag() {
		return "</title>" + Constants.CR;
	}

	@Override
	public Ititle text(String content) {
		children.add(new Text(content));

		return this;
	}

	@Override
	public Ititle attribute(TitleAttribute attr, String value) {
		attributes.put(attr, value.toString());

		return this;
	}
}
