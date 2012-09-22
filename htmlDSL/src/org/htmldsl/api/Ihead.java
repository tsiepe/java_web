package org.htmldsl.api;

import org.htmldsl.api.internal.IPrintable;

public interface Ihead extends IPrintable {

	Ihead attribute(HeadAttribute attr, String value);

	static enum HeadAttribute implements IAttribute {
		profile, dir, lang;

		@Override
		public String toString() {
			return name();
		}
	}
}
