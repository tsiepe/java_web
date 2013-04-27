package org.htmldsl.impl;

import java.util.List;

import org.htmldsl.api.Idoctype;
import org.htmldsl.api.internal.Constants;
import org.htmldsl.api.internal.IPrintable;

class Doctype implements Idoctype {

	private DoctypeKind kind;

	Doctype(DoctypeKind kind) {
		this.kind = kind;
	}

	@Override
	public List<IPrintable> children() {
		return null;
	}

	@Override
	public <T extends IPrintable> List<T> children(Class<T> kind) {
		return null;
	}

	@Override
	public String startTag() {
		return "<!DOCTYPE " + kind;
	}

	@Override
	public String endTag() {
		return ">" + Constants.CR;
	}

	@Override
	public String toHtmlString(int lastIndent) {
		return startTag() + endTag();
	}

}
