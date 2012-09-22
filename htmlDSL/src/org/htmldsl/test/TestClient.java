package org.htmldsl.test;

import org.htmldsl.api.Ia.AAttribute;
import org.htmldsl.api.Ihtml;
import org.htmldsl.impl.DocumentFactory;

public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ihtml html = DocumentFactory.getInstance().html();
		html.head();
		html.body().div().a().text("Google Deutschland")
				.attribute(AAttribute.href, "http://www.google.de");

		System.out.println(html.toHtmlString(0));
	}

}
