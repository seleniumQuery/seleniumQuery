package io.github.seleniumquery.by.enhancements;

import static io.github.seleniumquery.by.enhancements.SeleniumQueryEnhancementUtils.isNotHtmlUnitDriver;
import static io.github.seleniumquery.by.enhancements.SeleniumQueryEnhancements.isNativeCss;
import io.github.seleniumquery.by.SeleniumQueryBy;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * @author acdcjunior
 * @since 0.5.0
 */
public class CheckedSelector implements SeleniumQueryEnhancement {
	
	private static final String CHECKED_PATTERN = "(.*)"+"(?<!\\\\):"+"checked";
	
	private static final List<String> ALLOWED_TAGS = Arrays.asList("input", "option");

	@Override
	public boolean isApplicable(String selector, SearchContext context) {
		if (!selector.matches(CHECKED_PATTERN)) {
			return false;
		}
		
		// HtmlUnit's :checked is not consistent across versions, so we do it ourselves
		if (isNotHtmlUnitDriver(context) && !isNativeCss(selector, context)) {
			return false;
		}
		return true;
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		
		Pattern p = Pattern.compile(CHECKED_PATTERN);
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
			if (!ALLOWED_TAGS.contains(webElement.getTagName()) || !webElement.isSelected()) {
				iterator.remove();
			}
		}

		return elementsFound;
	}

}