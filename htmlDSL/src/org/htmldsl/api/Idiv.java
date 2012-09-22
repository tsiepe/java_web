package org.htmldsl.api;

import java.util.Map;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;
import org.htmldsl.api.internal.ITextContainer;

public interface Idiv extends IPrintable, IAttributable, ITextContainer<Idiv> {

	static enum DivAttribute implements IAttribute {
		align;

		@Override
		public String toString() {
			return name();
		}
	}

	static enum AlignValue implements IAttribute {
		left, center, right, justify;

		@Override
		public String toString() {
			return name();
		}
	}

	Idiv attribute(DivAttribute attr, AlignValue value);

	Ia a(Map<IAttribute, String>... attributes);

	Idiv div(Map<IAttribute, String>... attributes);

	Idl dl(Map<IAttribute, String>... attributes);

	Iform form(Map<IAttribute, String>... attributes);
}
