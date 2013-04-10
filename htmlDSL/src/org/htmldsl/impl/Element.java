package org.htmldsl.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;
import org.htmldsl.util.Utils;

abstract class Element implements IPrintable, IAttributable {

	private Map<UniversalAttribute, String> universalAttributes = new HashMap<IAttributable.UniversalAttribute, String>();

	protected List<IPrintable> children = new ArrayList<IPrintable>();
	protected Map<Object, String> attributes = new HashMap<Object, String>();

	public List<IPrintable> children() {
		return children;
	}

	public <T extends IPrintable> List<T> children(Class<T> kind) {
		List<T> result = new ArrayList<T>();

		for (IPrintable e : children) {
			if (kind.isAssignableFrom(e.getClass())) {
				result.add((T) e);
			}
		}

		return result;
	}

	protected void handleAttributes(Map<IAttribute, String>... attributes) {
		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof UniversalAttribute) {
					attribute((UniversalAttribute) attr,
							attributes[0].get(attr));
				}
			}
		}
	}

	@Override
	public IAttributable attribute(UniversalAttribute attr, String value) {
		universalAttributes.put(attr, value);

		return this;
	}

	protected String getUniversalAttributesAsString() {
		String universalAttributesString = "";

		for (UniversalAttribute attr : universalAttributes.keySet()) {
			universalAttributesString += " " + attr + "=\""
					+ universalAttributes.get(attr) + "\"";
		}

		return universalAttributesString;
	}

	protected String getAttributesAsString() {
		String attributesAsString = "";

		for (Object attr : attributes.keySet()) {
			attributesAsString += " " + attr + "=\"" + attributes.get(attr)
					+ "\"";
		}

		return attributesAsString;
	}

	@Override
	public String toHtmlString(int lastIndent) {
		String indent = Utils.getInstance().getIndentation(lastIndent);

		StringBuilder sb = new StringBuilder();

		sb.append(indent + startTag());

		int newIndent = lastIndent + 4;
		for (IPrintable printable : children) {
			sb.append(printable.toHtmlString(newIndent));
		}

		sb.append(indent + endTag());

		return sb.toString();
	}
}
