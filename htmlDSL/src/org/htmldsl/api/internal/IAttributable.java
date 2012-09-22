package org.htmldsl.api.internal;

import org.htmldsl.api.IAttribute;

public interface IAttributable {

	public static enum UniversalAttribute implements IAttribute {
		CLASS, ID, STYLE, TITLE, DIR, LANG, ONCLICK, ONDBLCLICK, ONMOUSEDOWN, ONMOUSEUP, ONMOUSEOVER, ONMOUSEMOVE, ONMOUSEOUT, ONKEYPRESS, ONKEYDOWN, ONKEYUP;

		public String toString() {
			return name().toLowerCase();
		}
	}

	IAttributable attribute(UniversalAttribute attr, String value);
}
