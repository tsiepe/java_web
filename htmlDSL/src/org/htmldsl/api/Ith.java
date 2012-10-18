package org.htmldsl.api;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;
import org.htmldsl.api.internal.ITextContainer;

public interface Ith extends IAttributable, IPrintable, ITextContainer<Ith> {

	static enum ThAttribute implements IAttribute {
		abbr, @Deprecated
		align, @Deprecated
		bgcolor, Char, charoff, @Deprecated
		colspan, headers, @Deprecated
		height, @Deprecated
		nowrap, rowspan, scope, @Deprecated
		valign, @Deprecated
		width;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	Ith attribute(ThAttribute attr, String value);
}
