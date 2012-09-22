package org.htmldsl.impl;

import org.htmldsl.api.internal.Constants;
import org.htmldsl.api.internal.IPrintable;
import org.htmldsl.util.Utils;

class Text implements IPrintable {

	private String content;

	Text(String content) {
		this.content = content;
	}

	@Override
	public String startTag() {
		return "";
	}

	@Override
	public String endTag() {
		return "";
	}

	@Override
	public String toHtmlString(int lastIndent) {
		return Utils.getInstance().getIndentation(lastIndent) + content
				+ Constants.CR;
	}

}
