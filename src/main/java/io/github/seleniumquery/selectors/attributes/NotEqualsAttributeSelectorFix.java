package io.github.seleniumquery.selectors.attributes;

import io.github.seleniumquery.selector.SelectorUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class NotEqualsAttributeSelectorFix {
	
	public String removeStrings(String input) {
		Pattern p = Pattern.compile("\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*'");
		Matcher m = p.matcher(input);
		StringBuilder sb = new StringBuilder(input);
		while (m.find()) {
			sb.replace(m.start(), m.end(), StringUtils.repeat('_',  m.end()-m.start()));
		}
		return sb.toString();
	}

	public String removeContains(String input) {
		// :contains matches, \:contains dont, \\:contains does
		Pattern p = Pattern.compile(SelectorUtils.ESCAPED_SLASHES+":"+"contains\\(.*?"+SelectorUtils.ESCAPED_SLASHES+"\\)");
		Matcher m = p.matcher(input);
		StringBuilder sb = new StringBuilder(input);
		while (m.find()) {
			sb.replace(m.start(), m.end(), StringUtils.repeat('_',  m.end()-m.start()));
		}
		return sb.toString();
	}

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
	public String fixAttributeNotEquals(String input) {
		String PATTERN = "("+SelectorUtils.ESCAPED_SLASHES+"\\[.*?"+SelectorUtils.ESCAPED_SLASHES+")!(=.*?"+SelectorUtils.ESCAPED_SLASHES+"\\])";
		if (!input.matches(".*" + PATTERN + ".*")) {
			return input;
		}
		String inputWithoutStrings = removeStrings(input);
		String inputWithoutStringsAndContains = removeContains(inputWithoutStrings);
		
		StringBuilder sb = new StringBuilder(input);
		
		Pattern p = Pattern.compile(PATTERN);
		Matcher m = p.matcher(inputWithoutStringsAndContains);
		while (m.find()) {
			String leftPart = input.substring(m.start(1), m.end(1));
			String rightPart = input.substring(m.start(2), m.end(2));
			sb.replace(m.start(1), m.end(2), ":not("+leftPart+rightPart+")");
		}
		
		return sb.toString();
	}

}