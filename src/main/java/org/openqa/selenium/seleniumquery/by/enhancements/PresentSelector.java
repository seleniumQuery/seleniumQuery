package org.openqa.selenium.seleniumquery.by.enhancements;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;

/**
 * @author acdcjunior
 * @since 0.5.0
 */
public class PresentSelector implements SeleniumQueryEnhancement {
	
	private static final String PRESENT_PATTERN = "(.*)"+"(?<!\\\\):"+"present";

	@Override
	public boolean isApplicable(String selector, SearchContext context) {
		return selector.matches(PRESENT_PATTERN);
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		
		Pattern p = Pattern.compile(PRESENT_PATTERN);
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
			if (!isPresent(webElement)) {
				iterator.remove();
			}
		}

		return elementsFound;
	}

	public static boolean isPresent(WebElement webElement) {
		try {
			// calling ANY method forces a staleness check
			webElement.isEnabled();
			// passed staleness check, thus present
			return true;
		} catch (StaleElementReferenceException expected) {
			// failed staleness check, so not present
			return false;
		}
	}

}