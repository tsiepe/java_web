package org.htmldsl.client.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.htmldsl.api.Idiv;
import org.htmldsl.api.internal.IAttributable.UniversalAttribute;
import org.htmldsl.impl.DocumentFragmentFactory;

/**
 * Servlet implementation class HtmlFragmentGenerator
 */
@WebServlet("/fragment")
public class HtmlFragmentGenerator extends HttpServlet {

	private static final long serialVersionUID = -6890310197701187249L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Idiv myDiv = DocumentFragmentFactory.getInstance().createFragmentRoot(
				Idiv.class);
		myDiv.attribute(UniversalAttribute.STYLE, "display: none;").attribute(
				UniversalAttribute.ID, "message");

		myDiv.text("This is an AJAX response.");
		response.getWriter().write(myDiv.toHtmlString(0));
	}
}
