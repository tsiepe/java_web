package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Iscript;
import org.htmldsl.api.internal.Constants;

class Script extends Element implements Iscript {

	Script(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof ScriptAttribute) {
					attribute((ScriptAttribute) attr, attributes[0].get(attr));
				}
			}
		}
	}

	@Override
	public String startTag() {
		if (!attributes.containsKey(ScriptAttribute.type)) {
			throw new RuntimeException(
					"Missing mandatory attribute type on <script> element.");
		}

		return "<script" + getUniversalAttributesAsString()
				+ getAttributesAsString() + ">";
	}

	@Override
	public String endTag() {
		return "</script>" + Constants.CR;
	}

	@Override
	public Iscript text(String content) {
		children.add(new Text(content));

		return this;
	}

	@Override
	public Iscript attribute(ScriptAttribute attr, String value) {
		attributes.put(attr, value);

		return this;
	}

}
