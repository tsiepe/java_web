package org.htmldsl.api;

import org.htmldsl.api.internal.IPrintable;

public interface Ititle extends IPrintable {

	static enum TitleAttribute implements IAttribute {
		dir, lang;

		@Override
		public String toString() {
			return name();
		}
	}

	Ititle text(String content);

	Ititle attribute(TitleAttribute attr, String value);
}
