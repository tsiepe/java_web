package org.htmldsl.api.internal;

import java.util.List;

public interface IPrintable {
	List<IPrintable> children();

	<T extends IPrintable> List<T> children(Class<T> kind);

	String startTag();

	String endTag();

	String toHtmlString(int lastIndent);
}
