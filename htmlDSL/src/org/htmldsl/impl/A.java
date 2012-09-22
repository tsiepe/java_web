package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Ia;
import org.htmldsl.api.internal.Constants;

class A extends Element implements Ia {

	A(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	protected void handleAttributes(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);
		
		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof AAttribute) {
					attribute((AAttribute) attr,
							attributes[0].get(attr));
				}
			}
		}
	}
	
	@Override
	public Ia text(String content) {
		children.add(new Text(content));

		return this;
	}

	@Override
	public String startTag() {
		if (!attributes.containsKey(AAttribute.href)
				&& !attributes.containsKey(AAttribute.name)) {
			throw new RuntimeException(
					"You must define at least either the 'href' attribute or the 'name' attribute on an anchor.");
		}

		return "<a" + getUniversalAttributesAsString()
				+ getAttributesAsString() + ">" + Constants.CR;
	}

	@Override
	public String endTag() {
		return "</a>" + Constants.CR;
	}

	@Override
	public Ia attribute(AAttribute attr, String value) {
		attributes.put(attr, value);

		return this;
	}

}
