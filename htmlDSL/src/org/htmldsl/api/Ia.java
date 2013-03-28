package org.htmldsl.api;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;
import org.htmldsl.api.internal.ITextContainer;

public interface Ia extends IPrintable, IAttributable, ITextContainer<Ia> {

	static enum AAttribute implements IAttribute {
		accesskey, charset, coords, href, hreflang, name, @Deprecated
		onblur, @Deprecated
		onfocus, rel, rev, shape, tabindex, target, type;

		@Override
		public String toString() {
			return name();
		}
	}

	static enum ShapeValue implements IAttribute {
		RECT, CIRCLE, POLY, DEFAULT;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	Ia attribute(AAttribute attr, String value);
}
