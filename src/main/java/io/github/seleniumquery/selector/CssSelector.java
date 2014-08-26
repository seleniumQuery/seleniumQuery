package io.github.seleniumquery.selector;

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
	
	/**
	 * Compiles the selector.
	 * 
	 * @param driver Is used here to allow driver feature sniffing, to determinate if the browser supports
	 * 			the CSS selector natively.
	 */
	CompiledCssSelector compile(WebDriver driver, Map<String, String> stringMap, T selector);

	SqXPathSelector toXPath(WebDriver driver, Map<String, String> stringMap, T selector);
	
}