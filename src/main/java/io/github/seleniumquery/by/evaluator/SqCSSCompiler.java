package io.github.seleniumquery.by.evaluator;

import java.util.Map;

import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;

public interface SqCSSCompiler<T> {

	/**
	 * Compiles the selector.
	 * 
	 * @param driver Is used here to allow driver feature sniffing, to determinate if the browser supports
	 * 			the CSS selector natively.
	 * @param stringMap TODO
	 */
	CompiledSelector compile(WebDriver driver, Map<String, String> stringMap, T selector);
	
}