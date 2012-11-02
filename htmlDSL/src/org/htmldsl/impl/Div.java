package org.htmldsl.impl;

import java.util.Map;

import org.htmldsl.api.IAttribute;
import org.htmldsl.api.Ia;
import org.htmldsl.api.Idiv;
import org.htmldsl.api.Idl;
import org.htmldsl.api.Iform;
import org.htmldsl.api.Itable;
import org.htmldsl.api.internal.Constants;

class Div extends Element implements Idiv {

	Div(Map<IAttribute, String>... attributes) {
		handleAttributes(attributes);
	}

	@Override
	protected void handleAttributes(Map<IAttribute, String>... attributes) {
		super.handleAttributes(attributes);

		if (attributes.length > 0) {
			for (IAttribute attr : attributes[0].keySet()) {
				if (attr instanceof DivAttribute) {
					attribute((DivAttribute) attr,
							AlignValue.valueOf(attributes[0].get(attr)));
				}
			}
		}
	}

	@Override
	public String startTag() {
		return "<div" + getUniversalAttributesAsString()
				+ getAttributesAsString() + (children.size() > 0 ? ">" : "/>")
				+ Constants.CR;
	}

	@Override
	public String endTag() {
		return children.size() > 0 ? "</div>" + Constants.CR : "";
	}

	@Override
	public Ia a(Map<IAttribute, String>... attributes) {
		Ia a = new A(attributes);
		children.add(a);

		return a;
	}

	@Override
	public Idiv attribute(DivAttribute attr, AlignValue value) {
		attributes.put(attr, value.toString());

		return this;
	}

	@Override
	public Idiv text(String content) {
		children.add(new Text(content));

		return this;
	}

	@Override
	public Idiv div(Map<IAttribute, String>... attributes) {
		Idiv div = new Div(attributes);
		children.add(div);

		return div;
	}

	@Override
	public Idl dl(Map<IAttribute, String>... attributes) {
		Idl dl = new Dl(attributes);
		children.add(dl);

		return dl;
	}

	@Override
	public Iform form(Map<IAttribute, String>... attributes) {
		Iform form = new Form(attributes);
		children.add(form);

		return form;
	}

	@Override
	public Itable table(Map<IAttribute, String>... attributes) {
		Itable table = new Table(attributes);
		children.add(table);

		return table;
	}

}
