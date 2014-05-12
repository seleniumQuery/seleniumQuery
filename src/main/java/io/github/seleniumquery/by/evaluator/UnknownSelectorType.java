package io.github.seleniumquery.by.evaluator;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UnknownSelectorType<T> implements CSSSelector<T> {
	
	private short type;
	
	public UnknownSelectorType(short type) {
		this.type = type;
	}

	@Override
	public boolean is(WebDriver driver, WebElement element, T selector) {
		throw new RuntimeException("CSS "+selector.getClass().getSimpleName()+" of type "+type+" is invalid or not supported!");
	}

	@Override
	public CompiledSelector compile(WebDriver driver, T selector) {
		// if it is unknown, we just push it forward, hoping the browser will know what to do
		return new CompiledSelector(selector.toString(), SqCSSFilter.FILTER_NOTHING);
	}
	
}