package org.htmldsl.api;

import java.util.Map;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Ihtml extends IPrintable, IAttributable {

	static enum HtmlAttribute implements IAttribute {
		dir, lang;

		@Override
		public String toString() {
			return name();
		}
	}

	static enum DirValue implements IAttribute {
		rtl, ltr;

		@Override
		public String toString() {
			return name();
		}
	}

	Ihtml attribute(HtmlAttribute attr, String value);

	Ihead head(Map<IAttribute, String>... attributes);

	Ibody body(Map<IAttribute, String>... attributes);

	Iframeset frameset(Map<IAttribute, String>... attributes);
}
