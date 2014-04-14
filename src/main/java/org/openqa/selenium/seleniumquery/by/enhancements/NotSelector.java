package org.openqa.selenium.seleniumquery.by.enhancements;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;

public class NotSelector implements SeleniumQueryEnhancement {
	
	/**
	 * This is a simple, regex-based approach.
	 * Allows up to three levels of bracket nesting, which feels enough right now.
	 * 
	 * Example of three level nesting: div:not(:not(:not(.c3)))
	 * 
	 * In the future, the intention is to switch for a, hopefully, simple, lightweight parser approach.
	 */
	private static final String NOT_PATTERN = "(.*?)"+"(?<!\\\\):"+"not"+"\\("+"((?:[^()]*|\\((?:[^()]*|\\([^()]*\\))*\\))*)"+"\\)";
	
	@Override
	public boolean isApplicable(String selector) {
		return selector.matches(NOT_PATTERN);
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		String exclusionSelector = null;
		
		Pattern p = Pattern.compile(NOT_PATTERN);
		Matcher m = p.matcher(selector);
		if (m.find()) {
			effectiveSelector = m.group(1);
			exclusionSelector = m.group(2);
		}
		
		List<WebElement> elementsFound = null;
		if (effectiveSelector.isEmpty()) {
			elementsFound = new By.ByCssSelector("*").findElements(context);
		} else {
			elementsFound = SeleniumQueryBy.byEnhancedSelector(effectiveSelector).findElements(context);
		}

		List<WebElement> exclusionElements = SeleniumQueryBy.byEnhancedSelector(exclusionSelector).findElements(context);
		elementsFound.removeAll(exclusionElements);
		
		return elementsFound;
	}

}