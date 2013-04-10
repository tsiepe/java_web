package org.htmldsl.impl;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Ibody;
import org.htmldsl.api.Iframeset;
import org.htmldsl.api.Ihead;
import org.htmldsl.api.Ihtml;
import org.htmldsl.api.internal.Constants;
import org.htmldsl.api.internal.IPrintable;

class Html extends Element implements Ihtml {

	Html(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	private static enum HtmlChild implements IAttribute {
		head, body, frameset
	}

	private Map<HtmlChild, IPrintable> children = new TreeMap<HtmlChild, IPrintable>(
			new Comparator<HtmlChild>() {

				@Override
				public int compare(HtmlChild firstChild, HtmlChild secondChild) {
					return firstChild.ordinal() < secondChild.ordinal() ? -1
							: firstChild.ordinal() == secondChild.ordinal() ? 0
									: 1;
				}
			});

	@Override
	protected void handleAttributes(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof HtmlAttribute) {
					attribute((HtmlAttribute) attr, attributes[0].get(attr));
				}
			}
		}
	}

	@Override
	public Ihead head(Map<IAttribute, String>... attributes) {
		Ihead result = (Ihead) children.get(HtmlChild.head);

		if (null == result) {
			result = new Head(attributes);
			children.put(HtmlChild.head, result);
		}

		return result;
	}

	@Override
	public Ibody body(Map<IAttribute, String>... attributes) {
		Ibody result = (Ibody) children.get(HtmlChild.body);

		if (null == result) {
			if (children.containsKey(HtmlChild.head)) {
				if (!children.containsKey(HtmlChild.frameset)) {
					result = new Body(attributes);
					children.put(HtmlChild.body, result);
				} else {
					throw new RuntimeException(
							"Mutually exclusive elements at this level <body> and <frameset>. Please add either a <body> or a <frameset> but not both at the same time.");
				}
			} else {
				throw new RuntimeException(
						"You must create the <head> element before adding the <body> tag.");
			}
		}

		return result;
	}

	@Override
	public Iframeset frameset(Map<IAttribute, String>... attributes) {
		Iframeset result = (Iframeset) children.get(HtmlChild.frameset);

		if (null == result) {
			if (children.containsKey(HtmlChild.head)) {
				if (!children.containsKey(HtmlChild.body)) {
					result = new Frameset();
					children.put(HtmlChild.frameset, result);
				} else {
					throw new RuntimeException(
							"Mutually exclusive elements at this level <body> and <frameset>. Please add either a <body> or a <frameset> but not both at the same time.");
				}
			} else {
				throw new RuntimeException(
						"You must create the <head> element before adding the <frameset> tag.");
			}
		}

		return result;
	}

	public String startTag() {
		return "<html" + getAttributesAsString() + ">" + Constants.CR;
	}

	@Override
	public String endTag() {
		return "</html>" + Constants.CR;
	}

	private String insertDocType() {
		return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"
				+ Constants.CR;
	}

	@Override
	public String toHtmlString(int lastIndent) {
		StringBuilder sb = new StringBuilder();

		sb.append(insertDocType());
		sb.append(startTag());

		for (HtmlChild key : children.keySet()) {
			sb.append(children.get(key).toHtmlString(lastIndent));
		}

		sb.append(endTag());

		return sb.toString();
	}

	@Override
	public Ihtml attribute(HtmlAttribute attr, String value) {
		attributes.put(attr, value);

		return this;
	}
}
