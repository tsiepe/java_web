package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Idd;
import org.htmldsl.api.Idl;
import org.htmldsl.api.Idt;
import org.htmldsl.api.internal.Constants;

class Dl extends Element implements Idl {

	Dl(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	public Idd dd(Map<IAttribute, String>... attributes) {
		Idd dd = new Dd(attributes);
		children.add(dd);

		return dd;
	}

	@Override
	public Idt dt(Map<IAttribute, String>... attributes) {
		Idt dt = new Dt(attributes);
		children.add(dt);

		return dt;
	}

	@Override
	public String startTag() {
		return "<dl" + getUniversalAttributesAsString()
				+ getAttributesAsString() + (children.size() > 0 ? ">" : "/>")
				+ Constants.CR;
	}

	@Override
	public String endTag() {
		return children.size() > 0 ? "</dl>" + Constants.CR : "";
	}
}
