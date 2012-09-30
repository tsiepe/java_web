package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Idiv;
import org.htmldsl.api.Idl;
import org.htmldsl.api.Iform;
import org.htmldsl.api.internal.Constants;

class Form extends Element implements Iform {

	Form(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	protected void handleAttributes(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof FormAttribute) {
					attribute((FormAttribute) attr, attributes[0].get(attr));
				}
			}
		}
	}

	@Override
	public String startTag() {
		if (!attributes.containsKey(FormAttribute.action)) {
			throw new RuntimeException(
					"Element <form> must sport an 'action' attribute.");
		}

		return "<form" + getUniversalAttributesAsString()
				+ getAttributesAsString() + ">" + Constants.CR;
	}

	@Override
	public String endTag() {
		return "</form>";
	}

	@Override
	public Iform attribute(FormAttribute attr, String value) {
		attributes.put(attr, value);

		return this;
	}

	@Override
	public Iform attribute(FormAttribute attr, TargetType value) {
		attributes.put(attr, "" + value);

		return this;
	}

	@Override
	public Iform attribute(FormAttribute attr, MethodType value) {
		attributes.put(attr, "" + value);

		return this;
	}

	@Override
	public Idiv div(Map<IAttribute, String>... attributes) {
		return new Div(attributes);
	}

	@Override
	public Idl dl(Map<IAttribute, String>... attributes) {
		return new Dl(attributes);
	}
}
