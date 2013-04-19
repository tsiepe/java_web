package org.htmldsl.api;

import java.util.Map;

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

	Ilink link(Map<IAttribute, String>... attributes);

	Imeta meta(Map<IAttribute, String>... attributes);

	Iscript script(Map<IAttribute, String>... attributes);

	Ititle title(Map<IAttribute, String>... attributes);
}
