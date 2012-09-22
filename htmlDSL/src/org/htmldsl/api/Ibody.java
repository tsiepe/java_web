package org.htmldsl.api;

import java.util.Map;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Ibody extends IPrintable, IAttributable {

	Idiv div(Map<IAttribute, String>... attributes);
}
