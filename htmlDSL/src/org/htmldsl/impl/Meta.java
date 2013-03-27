package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Imeta;
import org.htmldsl.api.internal.Constants;

class Meta extends Element implements Imeta {

	Meta(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof MetaAttribute) {
					attribute((MetaAttribute) attr, attributes[0].get(attr));
				}
			}
		}
	}

	@Override
	public String startTag() {
		if (!attributes.containsKey(MetaAttribute.content)) {
			throw new RuntimeException(
					"Missing mandatory attribute content for element <meta>.");
		}

		return "<meta" + getUniversalAttributesAsString()
				+ getAttributesAsString();
	}

	@Override
	public String endTag() {
		return "/>" + Constants.CR;
	}

	@Override
	public Imeta attribute(MetaAttribute attr, String value) {
		attributes.put(attr, value);

		return this;
	}

}
