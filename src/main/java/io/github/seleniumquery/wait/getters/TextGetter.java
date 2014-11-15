package io.github.seleniumquery.wait.getters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.functions.jquery.manipulation.TextFunction;

public class TextGetter implements Getter<String> {
	
	public static TextGetter TEXT_GETTER = new TextGetter();

	private TextGetter() {
	}
	
	@Override
	public String get(WebDriver driver, List<WebElement> elements) {
		return TextFunction.text(elements);
	}
	
	@Override
	public String toString() {
		return "text()";
	}
	
}