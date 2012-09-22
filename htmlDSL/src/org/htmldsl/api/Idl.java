package org.htmldsl.api;

import java.util.Map;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Idl extends IPrintable, IAttributable {

	Idd dd(Map<IAttribute, String>... attributes);

	Idt dt(Map<IAttribute, String>... attributes);
}
