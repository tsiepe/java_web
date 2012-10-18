package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Ith;
import org.htmldsl.api.internal.Constants;

class Th extends Element implements Ith {

	Th(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	protected void handleAttributes(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof ThAttribute) {
					attribute((ThAttribute) attr, attributes[0].get(attr));
				}
			}
		}
	}

	@Override
	public String startTag() {
		return "<th" + getUniversalAttributesAsString()
				+ getAttributesAsString() + ">" + Constants.CR;
	}

	@Override
	public String endTag() {
		return "</th>" + Constants.CR;
	}

	@Override
	public Ith attribute(ThAttribute attr, String value) {
		attributes.put(attr, value);

		return this;
	}

	@Override
	public Ith text(String content) {
		children.add(new Text(content));

		return this;
	}

}
