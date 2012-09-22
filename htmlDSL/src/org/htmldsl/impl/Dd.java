package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Idd;

class Dd extends Element implements Idd {

	Dd(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	public String startTag() {
		return "<dd" + getUniversalAttributesAsString() + ">";
	}

	@Override
	public String endTag() {
		return "</dd>";
	}
}
