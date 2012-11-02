package org.htmldsl.test;

import org.htmldsl.api.Ia.AAttribute;
import org.htmldsl.api.Ibody;
import org.htmldsl.api.Iform.FormAttribute;
import org.htmldsl.api.Iform.MethodType;
import org.htmldsl.api.Ihtml;
import org.htmldsl.api.Itable;
import org.htmldsl.api.Itr;
import org.htmldsl.impl.DocumentFactory;

public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ihtml html = DocumentFactory.getInstance().html();
		html.head();
		Ibody body = html.body();
		body.div().a().text("Google Deutschland")
				.attribute(AAttribute.href, "http://www.google.de");
		body.div().form().attribute(FormAttribute.action, MethodType.post)
				.div().text("This is form content.");
		Itable table = body.div().table();
		Itr tr = table.tr();
		tr.th().text("First row");
		tr.td().text("First data cell");
		tr.td().text("Second data cell");
		Itr tr_ = table.tr();
		tr_.th().text("Second row");
		tr_.td().text("First data cell...");
		tr_.td().text("Second data cell...");

		System.out.println(html.toHtmlString(0));
	}

}
