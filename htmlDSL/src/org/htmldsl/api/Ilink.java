package org.htmldsl.api;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Ilink extends IAttributable, IPrintable {

	static enum LinkAttribute implements IAttribute {
		charset, href, hreflang, media, rel, rev, target, type;

		@Override
		public String toString() {
			return name();
		}
	}

	Ilink attribute(LinkAttribute attr, String value);
}
