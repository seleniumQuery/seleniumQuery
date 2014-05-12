package io.github.seleniumquery.by.evaluator;

import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;

public interface SqCSSCompiler<T> {

	/**
	 * Compiles the selector.
	 */
	CompiledSelector compile(WebDriver driver, T selector);
	
}