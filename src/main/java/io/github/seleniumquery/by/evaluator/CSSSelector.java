package io.github.seleniumquery.by.evaluator;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface CSSSelector<T> extends SqCSSCompiler<T> {

	/**
	 * Tests if the given element, under the given driver, matches the selector.
	 */
	boolean is(WebDriver driver, WebElement element, T selector);
	
}