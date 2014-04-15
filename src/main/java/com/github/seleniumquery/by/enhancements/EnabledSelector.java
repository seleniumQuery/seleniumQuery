package org.openqa.selenium.seleniumquery.by.enhancements;

import static org.openqa.selenium.seleniumquery.by.enhancements.SeleniumQueryEnhancementUtils.supportsNatively;

import java.util.Arrays;
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
public class EnabledSelector implements SeleniumQueryEnhancement {
	
	private static final String ENABLED_PATTERN = "(.*)"+"(?<!\\\\):"+"enabled";
	
	private static final List<String> ALLOWED_TAGS = Arrays.asList("input", "button", "optgroup", "option", "select", "textarea");

	@Override
	public boolean isApplicable(String selector, SearchContext context) {
		return selector.matches(ENABLED_PATTERN) && !supportsNatively(":enabled", context);
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		
		Pattern p = Pattern.compile(ENABLED_PATTERN);
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
			if (!webElement.isEnabled() || !ALLOWED_TAGS.contains(webElement.getTagName())) {
				iterator.remove();
			}
		}

		return elementsFound;
	}
	
}