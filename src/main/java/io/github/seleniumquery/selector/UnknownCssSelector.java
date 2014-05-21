package io.github.seleniumquery.selector;

import java.util.Map;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents an unknown CSS selector type.
 */
public class UnknownCssSelector<T> implements CssSelector<T> {
	
	private short type;
	
	public UnknownCssSelector(short type) {
		this.type = type;
	}

	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, T selector) {
		throw new RuntimeException("CSS "+selector.getClass().getSimpleName()+" of type "+type+" is invalid or not supported!");
	}

	@Override
	public CompiledCssSelector compile(WebDriver driver, Map<String, String> stringMap, T selector) {
		// if it is unknown, we just push it forward, hoping the browser will know what to do
		return new CompiledCssSelector(selector.toString(), CssFilter.FILTER_NOTHING);
	}
	
}