package org.htmldsl.api;

public enum TypeAttributeMIMEType {
	serverSideJavaScript("application/x-javascript"), javaScript(
			"text/javascript");

	private String mimeType;

	TypeAttributeMIMEType(String mimeType) {
		this.mimeType = mimeType;
	}

	@Override
	public String toString() {
		return mimeType;
	}
}