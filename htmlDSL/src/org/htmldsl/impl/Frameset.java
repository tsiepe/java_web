package org.htmldsl.impl;

import org.htmldsl.api.Iframeset;
import org.htmldsl.api.internal.Constants;

class Frameset extends Element implements Iframeset {

	@Override
	public String startTag() {
		return "<frameset" + getUniversalAttributesAsString() + ">"
				+ Constants.CR;
	}

	@Override
	public String endTag() {
		return "</frameset>" + Constants.CR;
	}
}
