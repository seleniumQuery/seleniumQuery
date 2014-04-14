package org.openqa.selenium.seleniumquery.by.enhancements;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;

/**
 * @author acdcjunior
 * @since 0.5.0
 */
public class VisibleSelector implements SeleniumQueryEnhancement {
	
	private static final String VISIBLE_PATTERN = "(.*)"+"(?<!\\\\):"+"visible";

	@Override
	public boolean isApplicable(String selector) {
		return selector.matches(VISIBLE_PATTERN);
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		
		Pattern p = Pattern.compile(VISIBLE_PATTERN);
		Matcher m = p.matcher(selector);
		
		m.find(); // trigger regex matching so .group() is available
		effectiveSelector = m.group(1);
		
		List<WebElement> elementsFound = null;
		if (effectiveSelector.isEmpty()) {
			elementsFound = new By.ByCssSelector("*").findElements(context);
		} else {
			elementsFound = SeleniumQueryBy.byEnhancedSelector(effectiveSelector).findElements(context);
		}
		
		for (Iterator<WebElement> iterator = elementsFound.iterator(); iterator.hasNext();) {
			WebElement webElement = iterator.next();
			if (!webElement.isDisplayed()) {
				iterator.remove();
			}
		}

		return elementsFound;
	}

}