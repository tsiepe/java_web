package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Ihead;
import org.htmldsl.api.Ilink;
import org.htmldsl.api.Imeta;
import org.htmldsl.api.Iscript;
import org.htmldsl.api.Ititle;
import org.htmldsl.api.internal.Constants;

class Head extends Element implements Ihead {

	Head(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	protected void handleAttributes(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof HeadAttribute) {
					attribute((HeadAttribute) attr, attributes[0].get(attr));
				}
			}
		}
	}

	@Override
	public String startTag() {
		return "<head" + getUniversalAttributesAsString()
				+ getAttributesAsString() + ">" + Constants.CR;
	}

	@Override
	public String endTag() {
		return "</head>" + Constants.CR;
	}

	@Override
	public Ihead attribute(HeadAttribute attr, String value) {
		attributes.put(attr, value);

		return this;
	}

	@Override
	public Ilink link(Map<IAttribute, String>... attributes) {
		Ilink link = new Link(attributes);
		children.add(link);

		return link;
	}

	@Override
	public Imeta meta(Map<IAttribute, String>... attributes) {
		Imeta meta = new Meta(attributes);
		children.add(meta);

		return meta;
	}

	@Override
	public Iscript script(Map<IAttribute, String>... attributes) {
		Iscript script = new Script(attributes);
		children.add(script);

		return script;
	}

	@Override
	public Ititle title(Map<IAttribute, String>... attributes) {
		Ititle title = new Title(attributes);
		children.add(title);

		return title;
	}
}
