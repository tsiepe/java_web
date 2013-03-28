package org.htmldsl.api;

public enum TargetType implements IAttribute {
	_blank, _parent, _self, _top;

	@Override
	public String toString() {
		return name();
	}
}