package org.htmldsl.api;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Iform extends IPrintable, IAttributable {

	static enum FormAttribute implements IAttribute {
		action, accept, accept_charset, enctype, method, name, @Deprecated
		onreset, @Deprecated
		onsubmit, target;

		@Override
		public String toString() {
			return name().replace("_", "-");
		}
	}

	static enum TargetType implements IAttribute {
		_blank, _parent, _self, _top;

		@Override
		public String toString() {
			return name();
		}
	}

	static enum MethodType implements IAttribute {
		get, post;

		@Override
		public String toString() {
			return name();
		}
	}
}
