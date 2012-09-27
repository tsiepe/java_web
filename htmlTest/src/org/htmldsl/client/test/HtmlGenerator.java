package org.htmldsl.client.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.htmldsl.api.Ia.AAttribute;
import org.htmldsl.api.Ihtml;
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
		html.head();
		html.body().div().a().text("Google Deutschland")
				.attribute(AAttribute.href, "http://www.google.de");

		response.getWriter().write(html.toHtmlString(0));
	}

}
