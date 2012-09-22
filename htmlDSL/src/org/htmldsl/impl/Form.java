package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Iform;
import org.htmldsl.api.internal.Constants;

class Form extends Element implements Iform {

	Form(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
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

}
