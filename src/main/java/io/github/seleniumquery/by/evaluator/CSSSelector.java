package io.github.seleniumquery.by.evaluator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface CSSSelector<T> {

	boolean is(WebDriver driver, WebElement element, T selector);
	
}