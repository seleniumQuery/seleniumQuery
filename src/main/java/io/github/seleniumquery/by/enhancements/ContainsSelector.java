package io.github.seleniumquery.by.enhancements;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.by.SeleniumQueryBy;

public class ContainsSelector implements SeleniumQueryEnhancement {
	
	/**
	 * With lookbehind to allow escaping: http://regex101.com/r/rC1eZ5
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
		
		List<WebElement> elementsFound = null;
		if (effectiveSelector.isEmpty()) {
			elementsFound = new By.ByCssSelector("*").findElements(context);
		} else {
			elementsFound = SeleniumQueryBy.byEnhancedSelector(effectiveSelector).findElements(context);
		}
		
		for (Iterator<WebElement> iterator = elementsFound.iterator(); iterator.hasNext();) {
			WebElement webElement = iterator.next();
			if (!webElement.getText().contains(textToContain)) {
				iterator.remove();
			}
		}

		return elementsFound;
	}

	public String removeQuotes(String text) {
		if (text.matches("^['\"].*['\"]$")) {
			return text.substring(1, text.length()-1);
		}
		return text;
	}

}