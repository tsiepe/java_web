package org.htmldsl.util;

public class Utils {

	private static final Utils instance = new Utils();

	protected Utils() {
	}

	public static Utils getInstance() {
		return instance;
	}

	public String getIndentation(int lastIndent) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < (lastIndent + 4); ++i) {
			sb.append(" ");
		}

		return sb.toString();
	}
}
