package io.github.seleniumquery.by.enhancements;

import io.github.seleniumquery.by.SeleniumQueryBy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class ContainsSelector implements SeleniumQueryEnhancement {
	
	/**
	 * http://regex101.com/r/rC1eZ5
	 */
	private static final String CONTAINS_PATTERN = "(.*)"+"(?<!\\\\):"+"contains"+"\\((\"(?:\\\\.|[^\"])*\"|'(?:\\\\.|[^'])*'|[^)]+)\\)";

	@Override
	public boolean isApplicable(String selector, SearchContext context) {
		return selector.matches(CONTAINS_PATTERN);
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		String textToContain = null;
		
		Pattern p = Pattern.compile(CONTAINS_PATTERN);
		Matcher m = p.matcher(selector);
		if (m.find()) {
			effectiveSelector = m.group(1);
			textToContain = removeQuotes(m.group(2));
		}
		
		String xPathExpression = "//*[descendant-or-self::*[contains(text(), " + singleQuoteEscape(textToContain) + ")]]";
		List<WebElement> allElementsCointainingText = By.xpath(xPathExpression).findElements(context);
		
		if (effectiveSelector.isEmpty()) {
			return allElementsCointainingText;
		}
		
		Set<WebElement> allElementsCointaininTextSet = new HashSet<WebElement>(allElementsCointainingText);
		List<WebElement> elementsFound = SeleniumQueryBy.byEnhancedSelector(effectiveSelector).findElements(context);
		elementsFound.retainAll(allElementsCointaininTextSet);
		return elementsFound;
	}

	private String removeQuotes(String text) {
		if (text.matches("^['\"].*['\"]$")) {
			return text.substring(1, text.length()-1);
		}
		return text;
	}
	
	/**
	 * Handles XPath 1.0 single-quote escaping
	 * The string -- "I'm reading \"Harry Potter\""
	 * becomes ----- "concat('I', "'", 'm reading "Harry Potter"', '')"
	 */
	private String singleQuoteEscape(String textToContain) {
		return "concat('"+textToContain.replace("'", "', \"'\", '") + "', '')";
	}
	
}