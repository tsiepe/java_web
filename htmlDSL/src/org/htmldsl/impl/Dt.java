package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Idt;

class Dt extends Element implements Idt {

	Dt(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	public String startTag() {
		return "<dt" + getUniversalAttributesAsString() + ">";
	}

	@Override
	public String endTag() {
		return "</dt>";
	}

}
