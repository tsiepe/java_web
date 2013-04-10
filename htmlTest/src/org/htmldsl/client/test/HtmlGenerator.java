package org.htmldsl.client.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Ia.AAttribute;
import org.htmldsl.api.Ibody;
import org.htmldsl.api.Idiv;
import org.htmldsl.api.Iform.FormAttribute;
import org.htmldsl.api.Iform.MethodType;
import org.htmldsl.api.Ihead;
import org.htmldsl.api.Ihtml;
import org.htmldsl.api.Ilink.LinkAttribute;
import org.htmldsl.api.Imeta.MetaAttribute;
import org.htmldsl.api.Itable;
import org.htmldsl.api.Itr;
import org.htmldsl.impl.DocumentFactory;

/**
 * Servlet implementation class HtmlGenerator
 */
@WebServlet("/")
public class HtmlGenerator extends HttpServlet {

	private static final long serialVersionUID = -4247423577713075088L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HtmlGenerator() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Ihtml html = DocumentFactory.getInstance().html();
		Ihead head = html.head();
		head.link()
				.attribute(LinkAttribute.rel, "stylesheet")
				.attribute(LinkAttribute.href,
						"http://code.jquery.com/mobile/1.3.0/jquery.mobile-1.3.0.min.css");
		Map<IAttribute, String> metaAttrs = new HashMap<IAttribute, String>();
		metaAttrs.put(MetaAttribute.http_equiv, "expires");
		metaAttrs.put(MetaAttribute.content, "Sat, 01 Dec 2001 00:00:00 GMT");
		html.head().meta(metaAttrs);
		Ibody body = html.body();
		html.body().div().a().text("Google Deutschland") // re-using the already
															// existing body tag
															// thus obviating
															// the need to pass
															// the reference to
															// the body tag and
															// having to worry
															// about only
															// calling body()
															// once in the
															// entire
															// application
				.attribute(AAttribute.href, "http://www.google.de");
		body.div().form().attribute(FormAttribute.action, MethodType.post) // this
																			// on
																			// the
																			// other
																			// hand
																			// is
																			// an
																			// example
																			// where
																			// the
																			// body
																			// tag
																			// reference
																			// is
																			// being
																			// passed
				.div().text("This is form content.");
		Itable table = body.children(Idiv.class).get(1).table(); // selecting
																	// the
																	// existing
																	// second
																	// child div
																	// in the
																	// body
																	// element
		// Itable table = body.div().table();
		Itr tr1 = table.tr();
		tr1.th().text("First row");
		tr1.td().text("First data cell");
		tr1.td().text("Second data cell");
		Itr tr2 = table.tr();
		tr2.th().text("Second row");
		tr2.td().text("First data cell...");
		tr2.td().text("Second data cell...");

		response.getWriter().write(html.toHtmlString(0));
	}
}
