package org.htmldsl.api;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Ilink extends IAttributable, IPrintable {

	static enum LinkAttribute implements IAttribute {
		charset, href, hreflang, media, rel, rev, target, type;

		@Override
		public String toString() {
			return name();
		}
	}

	static enum TargetAttributeValue {
		_blank, _parent, _self, _top;

		@Override
		public String toString() {
			return name();
		}
	}

	static enum TypeAttributeMIMETypeValue {
		serverSideJavaScript("application/x-javascript"), javaScript(
				"text/javascript");

		private String mimeType;

		TypeAttributeMIMETypeValue(String mimeType) {
			this.mimeType = mimeType;
		}

		@Override
		public String toString() {
			return mimeType;
		}
	}

	Ilink attribute(LinkAttribute attr, String value);
}
