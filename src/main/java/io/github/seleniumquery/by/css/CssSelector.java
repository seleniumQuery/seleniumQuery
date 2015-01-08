package io.github.seleniumquery.by.css;

import io.github.seleniumquery.by.xpath.component.XPathComponent;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface CssSelector<T, C extends XPathComponent> {

	/**
	 * Tests if the given element, under the given driver, matches the selector.
	 * 
	 * @param stringMap map of strings that were extracted from the selector 
	 */
	boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, T selector);

	C toXPath(Map<String, String> stringMap, T selector);
	
}