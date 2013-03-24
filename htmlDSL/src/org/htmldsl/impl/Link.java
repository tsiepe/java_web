package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Ilink;

class Link extends Element implements Ilink {

	Link(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof LinkAttribute) {
					attribute((LinkAttribute) attr, attributes[0].get(attr));
				}
			}
		}
	}

	@Override
	public String startTag() {
		if (!attributes.containsKey(LinkAttribute.media)) {
			attributes.put(LinkAttribute.media, "screen");
		}

		return "<link" + getUniversalAttributesAsString()
				+ getAttributesAsString();
	}

	@Override
	public String endTag() {
		return "/>";
	}

	@Override
	public Ilink attribute(LinkAttribute attr, String value) {
		attributes.put(attr, value);

		return this;
	}

}
