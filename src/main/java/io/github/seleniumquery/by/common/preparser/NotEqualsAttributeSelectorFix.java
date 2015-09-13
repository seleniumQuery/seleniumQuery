/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by.common.preparser;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.seleniumquery.by.SelectorUtils.ESCAPED_SLASHES;

/**
 * See {@link NotEqualsAttributeSelectorFix#turnAttributeNotEqualsIntoNotAttributeEquals(String)} javadoc.
 */
class NotEqualsAttributeSelectorFix {

	public static final String ATTR_NEQ_REGEX = "(" + ESCAPED_SLASHES + "\\[.*?" + ESCAPED_SLASHES + ")!(=.*?" + ESCAPED_SLASHES + "\\])";
	public static final Pattern ATTR_NEQ_PATTERN = Pattern.compile(ATTR_NEQ_REGEX);

	/**
	 * Boldly attempts to change all "element[attribute!=value]" into a "element:not([attribute=value])".
	 *
	 * It uses regex, not a parser. In an idel world, the CSS parser would accept such syntax, but it'd
	 * be too much work to fork and extend it (and keep it updated).
	 *
	 * The method tries to ignore declarations inside strings, such as: "element:contains('[attribute!=value]')",
	 * and ":contains()" contents, even when not escaped, such as: "element:contains([attribute!=value])",
	 * which is a valid selector, btw.
	 *
	 * @param input An ordinary selector.
	 * @return The same selector with the "[attribute!=value]"s turned to ":not([attribute=value])".
	 */
	public String turnAttributeNotEqualsIntoNotAttributeEquals(String input) {
		if (!input.matches(".*" + ATTR_NEQ_REGEX + ".*")) {
			return input;
		}
		String inputWithoutStrings = removeStrings(input);
		String inputWithoutStringsAndContains = removeContains(inputWithoutStrings);

		StringBuilder sb = new StringBuilder(input);

		Matcher m = ATTR_NEQ_PATTERN.matcher(inputWithoutStringsAndContains);
		while (m.find()) {
			String leftPart = input.substring(m.start(1), m.end(1));
			String rightPart = input.substring(m.start(2), m.end(2));
			sb.replace(m.start(1), m.end(2), ":not("+leftPart+rightPart+")");
		}

		return sb.toString();
	}

	// package visibility due to test
	String removeStrings(String input) {
		Pattern p = Pattern.compile("\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*'");
		Matcher m = p.matcher(input);
		StringBuilder sb = new StringBuilder(input);
		while (m.find()) {
			sb.replace(m.start(), m.end(), StringUtils.repeat('_',  m.end()-m.start()));
		}
		return sb.toString();
	}

	// package visibility due to test
	String removeContains(String input) {
		// :contains matches, \:contains dont, \\:contains does
		Pattern p = Pattern.compile(ESCAPED_SLASHES + ":" + "contains\\(.*?" + ESCAPED_SLASHES + "\\)");
		Matcher m = p.matcher(input);
		StringBuilder sb = new StringBuilder(input);
		while (m.find()) {
			sb.replace(m.start(), m.end(), StringUtils.repeat('_',  m.end()-m.start()));
		}
		return sb.toString();
	}

}