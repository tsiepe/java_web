package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Itd;
import org.htmldsl.api.Ith;
import org.htmldsl.api.Itr;

class Tr extends Element implements Itr {

	Tr(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof TrAttribute) {
					attribute((TrAttribute) attr, "" + attributes[0].get(attr));
				}
			}
		}
	}

	@Override
	public String startTag() {
		return "<tr" + getUniversalAttributesAsString()
				+ getAttributesAsString() + ">";
	}

	@Override
	public String endTag() {
		return "</tr>";
	}

	@Override
	public Itr attribute(TrAttribute attrKey, String attrValue) {
		attributes.put(attrKey, attrValue);

		return this;
	}

	@Override
	public Itd td(Map<IAttribute, String>... attributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ith th(Map<IAttribute, String>... attributes) {
		// TODO Auto-generated method stub
		return null;
	}

}
