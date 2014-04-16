package io.github.seleniumquery.by.enhancements;

import static io.github.seleniumquery.by.enhancements.SeleniumQueryEnhancementUtils.supportsNatively;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.by.SeleniumQueryBy;

/**
 * @author acdcjunior
 * @since 0.5.0
 */
public class DisabledSelector implements SeleniumQueryEnhancement {
	
	private static final String DISABLED_PATTERN = "(.*)"+"(?<!\\\\):"+"disabled";
	
	private static final List<String> ALLOWED_TAGS = Arrays.asList("input", "button", "optgroup", "option", "select", "textarea");

	@Override
	public boolean isApplicable(String selector, SearchContext context) {
		return selector.matches(DISABLED_PATTERN) && !supportsNatively(":disabled", context);
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		
		Pattern p = Pattern.compile(DISABLED_PATTERN);
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
			if (webElement.isEnabled() || !ALLOWED_TAGS.contains(webElement.getTagName())) {
				iterator.remove();
			}
		}

		return elementsFound;
	}
	
}