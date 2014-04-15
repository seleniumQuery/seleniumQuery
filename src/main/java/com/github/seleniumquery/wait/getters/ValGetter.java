package org.openqa.selenium.seleniumquery.wait.getters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.functions.ValFunction;

public class ValGetter implements Getter<String> {
	
	public static ValGetter VAL_GETTER = new ValGetter();

	private ValGetter() { }
	
	@Override
	public String get(WebDriver driver, List<WebElement> elements) {
		return ValFunction.val(elements);
	}
	
	@Override
	public String toString() {
		return "val()";
	}
	
}