package io.github.seleniumquery.by.evaluator;

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
	
}