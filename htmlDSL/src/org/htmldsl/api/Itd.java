package org.htmldsl.api;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;
import org.htmldsl.api.internal.ITextContainer;

public interface Itd extends IAttributable, IPrintable, ITextContainer<Itd> {

	static enum TdAttribute implements IAttribute {
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

	Itd attribute(TdAttribute attr, String value);
}
