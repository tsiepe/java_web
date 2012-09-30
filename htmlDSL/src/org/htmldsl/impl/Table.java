package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Itable;
import org.htmldsl.api.Itr;

public class Table extends Element implements Itable {

	@Override
	public String startTag() {
		return "<table" + getUniversalAttributesAsString()
				+ getAttributesAsString() + ">";
	}

	@Override
	public String endTag() {
		return "</table>";
	}

	@Override
	public Itr tr(Map<IAttribute, String>... attributes) {
		Tr tr = new Tr(attributes);
		children.add(tr);

		return tr;
	}

	@Override
	public Itable attribute(TableAttribute attrKey,
			AlignAttributeValue attrValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Itable attribute(TableAttribute attrKey,
			ValignAttributeValue attrValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
