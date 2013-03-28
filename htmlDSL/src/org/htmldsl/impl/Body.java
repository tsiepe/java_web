package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Ibody;
import org.htmldsl.api.Idiv;
import org.htmldsl.api.Iscript;
import org.htmldsl.api.internal.Constants;

public class Body extends Element implements Ibody {

	Body(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	public String startTag() {
		return "<body" + getUniversalAttributesAsString() + ">" + Constants.CR;
	}

	@Override
	public String endTag() {
		return "</body>" + Constants.CR;
	}

	@Override
	public Idiv div(Map<IAttribute, String>... attributes) {
		Idiv result = new Div(attributes);
		children.add(result);

		return result;
	}

	@Override
	public Iscript script(Map<IAttribute, String>... attributes) {
		Iscript result = new Script(attributes);
		children.add(result);

		return result;
	}
}
