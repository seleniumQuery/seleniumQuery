package io.github.seleniumquery.by.evaluator;

import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;

public interface SqCSSCompiler<T> {

	/**
	 * Compiles the selector.
	 * 
	 * @param driver Is used here to allow driver feature sniffing, to determinate if the browser supports
	 * 			the CSS selector natively.
	 */
	CompiledSelector compile(WebDriver driver, T selector);
	
}