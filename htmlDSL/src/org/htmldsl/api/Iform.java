package org.htmldsl.api;

import java.util.Map;

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

	static enum MethodType implements IAttribute {
		get, post;

		@Override
		public String toString() {
			return name();
		}
	}

	Iform attribute(FormAttribute attr, String value);

	Iform attribute(FormAttribute attr, TargetType value);

	Iform attribute(FormAttribute attr, MethodType value);

	Idiv div(Map<IAttribute, String>... attributes);

	Idl dl(Map<IAttribute, String>... attributes);
}
