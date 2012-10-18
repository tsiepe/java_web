package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Itd;
import org.htmldsl.api.internal.Constants;

class Td extends Element implements Itd {

	Td(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	protected void handleAttributes(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof TdAttribute) {
					attribute((TdAttribute) attr, attributes[0].get(attr));
				}
			}
		}
	}

	@Override
	public String startTag() {
		return "<td" + getUniversalAttributesAsString()
				+ getAttributesAsString() + ">" + Constants.CR;
	}

	@Override
	public String endTag() {
		return "</td>" + Constants.CR;
	}

	@Override
	public Itd attribute(TdAttribute attr, String value) {
		attributes.put(attr, value);

		return this;
	}

	@Override
	public Itd text(String content) {
		children.add(new Text(content));

		return this;
	}

}
