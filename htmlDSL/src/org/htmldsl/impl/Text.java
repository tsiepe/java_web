package org.htmldsl.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<IPrintable> children() {
		return new ArrayList<IPrintable>();
	}

	@Override
	public <T extends IPrintable> List<T> children(Class<T> kind) {
		return new ArrayList<T>();
	}

}
