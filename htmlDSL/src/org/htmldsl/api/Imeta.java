package org.htmldsl.api;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Imeta extends IAttributable, IPrintable {

	static enum MetaAttribute implements IAttribute {
		name, content, scheme, http_equiv;

		@Override
		public String toString() {
			return name().replace('_', '-');
		}
	}

	Imeta attribute(MetaAttribute attr, String value);
}
