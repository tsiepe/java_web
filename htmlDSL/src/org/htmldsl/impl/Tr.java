package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Itr;

public class Tr extends Element implements Itr {

	Tr(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {

			}
		}
	}

	@Override
	public String startTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String endTag() {
		// TODO Auto-generated method stub
		return null;
	}

}
