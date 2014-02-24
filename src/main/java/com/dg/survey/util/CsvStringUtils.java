package com.dg.survey.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author johnver
 * 
 */
public class CsvStringUtils {

	public static String toCsvString(final Collection<String> collection,
			final String separator) {

		final StringBuilder result = new StringBuilder();

		if (collection != null && !collection.isEmpty()) {
			for (final String value : collection) {

				result.append(value);
				result.append(separator);

			}
		}

		final String ret = result.length() > 0 ? result.substring(0,
				result.length() - separator.length()) : "";

		return ret;
	}

	public static List<String> toArray(final String csvString,
			final String separator) {

		final List<String> arrayOfString = new ArrayList<String>();
		final StringTokenizer tokenizer = new StringTokenizer(csvString,
				separator);
		while (tokenizer.hasMoreTokens()) {
			final String token = tokenizer.nextToken();
			arrayOfString.add(token);
		}
		;
		return arrayOfString;
	}
}
