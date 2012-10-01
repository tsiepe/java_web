package org.htmldsl.api;

import java.util.Map;

import org.htmldsl.api.internal.IAttributable;
import org.htmldsl.api.internal.IPrintable;

public interface Itable extends IAttributable, IPrintable {

	Itr tr(Map<IAttribute, String>... attributes);
}
