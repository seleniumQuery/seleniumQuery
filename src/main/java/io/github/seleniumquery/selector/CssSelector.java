package io.github.seleniumquery.by.evaluator;


import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface CSSSelector<T> extends SqCSSCompiler<T> {

	/**
	 * Tests if the given element, under the given driver, matches the selector.
	 * @param stringMap TODO
	 * @param stringMap map of strings that were extracted from the selector 
	 */
	boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, T selector);
	
}