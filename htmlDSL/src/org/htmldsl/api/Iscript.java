package org.htmldsl.api;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Iscript extends IAttributable, IPrintable {

	static enum ScriptAttribute implements IAttribute {
		charset, defer, event, @Deprecated
		language, For, src, type;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	Iscript attribute(ScriptAttribute attr, String value);

	Iscript text(String content);
}
