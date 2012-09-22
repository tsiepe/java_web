package org.htmldsl.api.internal;


public interface IPrintable {

	String startTag();

	String endTag();

	String toHtmlString(int lastIndent);
}
