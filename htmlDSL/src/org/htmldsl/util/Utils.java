package org.htmldsl.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

	private static final Utils instance = new Utils();

	protected Utils() {
	}

	public static Utils getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public <T> T getConfiguredInstanceForClass(Class<?> clazz) {
		T result = null;
		String line = null;
		BufferedReader buf = null;

		try {
			buf = new BufferedReader(new InputStreamReader(new FileInputStream(
					System.getProperty("org.htmldsl.config.file"))));

			while (null != (line = buf.readLine())) {
				if (line.trim().startsWith(clazz.getName())) {
					result = (T) Class.forName(line.split("=")[1])
							.newInstance();

					break;
				}
			}
		} catch (Exception ex) {
			System.out
					.println("Either no config file given via -Dorg.htmldsl.config.file parameter or the file could not be found or no instance of the configured class could be created.");
			ex.printStackTrace();
		} finally {
			if (null != buf)
				try {
					buf.close();
				} catch (IOException e) {
				}
		}

		return result;
	}

	public String getIndentation(int lastIndent) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < (lastIndent + 4); ++i) {
			sb.append(" ");
		}

		return sb.toString();
	}
}
