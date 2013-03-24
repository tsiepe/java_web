package org.htmldsl.api;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Iinput extends IAttributable, IPrintable {

	static enum InputAttribute implements IAttribute {
		accept, accesskey, @Deprecated
		align, alt, checked, disabled, ismap, maxlength, name, @Deprecated
		onblur, @Deprecated
		onfocus, @Deprecated
		onselect, readonly, size, src, tabindex, type, usemap, value;

		@Override
		public String toString() {
			return name();
		}
	}

	@Deprecated
	static enum AlignAttributeValue {
		top, middle, bottom, left, center, right;
	}

	static enum TypeAttributeValue {
		text, password, checkbox, radio, submit, reset, file, hidden, image, button;

		@Override
		public String toString() {
			return name();
		}
	}

	Iinput attribute(InputAttribute attr, String value);
}
