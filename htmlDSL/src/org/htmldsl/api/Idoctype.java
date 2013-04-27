package org.htmldsl.api;

import org.htmldsl.api.internal.IPrintable;

public interface Idoctype extends IPrintable {

	static enum DoctypeKind {
		HTML4("HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" "
				+ "\"http://www.w3.org/TR/html4/loose.dtd\""), HTML5("HTML"), XHMTL(
				"HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" "
						+ "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\""), HTML4Frameset(
				"HTML PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" "
						+ "\"http://www.w3.org/TR/html4/frameset.dtd\""), HTML2(
				"HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\""), HTML3_2(
				"HTML PUBLIC \"-//W3C//DTD HTML 3.2//EN\"");

		private String qualifier;

		DoctypeKind(String qualifier) {
			this.qualifier = qualifier;
		}

		@Override
		public String toString() {
			return qualifier;
		}
	}
}
