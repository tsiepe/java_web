package org.htmldsl.api;

import java.util.Map;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Itable extends IAttributable, IPrintable {

	Itr tr(Map<IAttribute, String>... attributes);

	Itable attribute(TableAttribute attr, String value);

	static enum TableAttribute {
		align, border, bgcolor, cellpadding, cellspacing, frame, rules, summary, width;

		@Override
		public String toString() {
			return name();
		}
	}

	static enum AlignAttributeValue {
		left, center, right;

		@Override
		public String toString() {
			return name();
		}
	}

	static enum FrameAttributeValue {
		Void, above, below, hsides, lhs, rhs, vsides, box, border;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	static enum RulesAttributeValue {
		none, groups, rows, cols, all;

		@Override
		public String toString() {
			return name();
		}
	}
}
