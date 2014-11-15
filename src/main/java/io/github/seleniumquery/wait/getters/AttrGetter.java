package io.github.seleniumquery.wait.getters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.functions.jquery.attributes.AttrFunction;

public class AttrGetter implements Getter<String> {
	
	private String attributeName;
	public AttrGetter(String attributeName) { this.attributeName = attributeName; }
	
	@Override
	public String get(WebDriver driver, List<WebElement> elements) {
		return AttrFunction.attr(driver, elements, this.attributeName);
	}
	
	@Override
	public String toString() {
		return "attr(\""+this.attributeName+"\")";
	}
	
}