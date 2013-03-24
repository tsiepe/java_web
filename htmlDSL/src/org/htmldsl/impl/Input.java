package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Iinput;

class Input extends Element implements Iinput {

	Input(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof InputAttribute) {
					attribute((InputAttribute) attr, attributes[0].get(attr));
				}
			}
		}
	}

	@Override
	public String startTag() {
		if (!attributes.containsKey(InputAttribute.type)) {
			attribute(InputAttribute.type, "" + TypeAttributeValue.text);
		}

		return "<input" + getUniversalAttributesAsString()
				+ getAttributesAsString();
	}

	@Override
	public String endTag() {
		return "/>";
	}

	@Override
	public Iinput attribute(InputAttribute attr, String value) {
		attributes.put(attr, value);

		return this;
	}

}
