package org.htmldsl.api;

import java.util.Map;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Itable extends IAttributable, IPrintable {

	static enum TableAttribute {
		align, valign;

		@Override
		public String toString() {
			return name();
		}
	}

	static enum AlignAttributeValue {
		Char, charoff;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	static enum ValignAttributeValue {
		top, middle, bottom, baseline;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	Itr tr(Map<IAttribute, String>... attributes);

	Itable attribute(TableAttribute attrKey, AlignAttributeValue attrValue);

	Itable attribute(TableAttribute attrKey, ValignAttributeValue attrValue);
}
