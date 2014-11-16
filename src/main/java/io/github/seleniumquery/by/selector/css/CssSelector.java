package io.github.seleniumquery.by.selector.css;

import io.github.seleniumquery.by.selector.xpath.XPathExpression;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface CssSelector<T> {

	/**
	 * Tests if the given element, under the given driver, matches the selector.
	 * 
	 * @param stringMap map of strings that were extracted from the selector 
	 */
	boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, T selector);
	
	XPathExpression toXPath(Map<String, String> stringMap, T selector);
	
}