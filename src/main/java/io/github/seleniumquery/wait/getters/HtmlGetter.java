package io.github.seleniumquery.wait.getters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.functions.HtmlFunction;

public class HtmlGetter implements Getter<String> {
	
	public static HtmlGetter HTML_GETTER = new HtmlGetter();

	private HtmlGetter() {
	}
	
	@Override
	public String get(WebDriver driver, List<WebElement> elements) {
		return HtmlFunction.html(driver, elements);
	}
	
	@Override
	public String toString() {
		return "html()";
	}
	
}