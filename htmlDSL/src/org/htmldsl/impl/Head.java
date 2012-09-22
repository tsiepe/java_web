package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Ihead;
import org.htmldsl.api.internal.Constants;
import org.htmldsl.util.Utils;

class Head extends Element implements Ihead {

	Head(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	public String startTag() {
		return "<head" + getUniversalAttributesAsString()
				+ getAttributesAsString() + ">" + Constants.CR;
	}

	@Override
	public String endTag() {
		return "</head>" + Constants.CR;
	}

	@Override
	public String toHtmlString(int lastIndent) {
		String indent = Utils.getInstance().getIndentation(lastIndent);

		StringBuilder sb = new StringBuilder();

		sb.append(indent + startTag());
		sb.append(indent + endTag());

		return sb.toString();
	}

	@Override
	public Ihead attribute(HeadAttribute attr, String value) {
		attributes.put(attr, value);

		return this;
	}
}
