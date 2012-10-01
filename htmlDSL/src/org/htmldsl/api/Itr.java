package org.htmldsl.api;

import java.util.Map;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Itr extends IAttributable, IPrintable {

	static enum TrAttribute implements IAttribute {
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

	Itd td(Map<IAttribute, String>... attributes);

	Ith th(Map<IAttribute, String>... attributes);

	Itr attribute(TrAttribute attrKey, String attrValue);
}
