package io.github.seleniumquery.wait.getters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.functions.PropFunction;

public class PropGetter<T> implements Getter<T> {
	
	private String propertyName;
	public PropGetter(String propertyName) { this.propertyName = propertyName; }
	
	@Override
	public T get(WebDriver driver, List<WebElement> elements) {
		return PropFunction.<T>prop(driver, elements, this.propertyName);
	}
	
	@Override
	public String toString() {
		return "attr(\""+this.propertyName+"\")";
	}
	
}