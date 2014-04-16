package io.github.seleniumquery.wait.getters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SizeGetter implements Getter<Integer> {
	
	public static SizeGetter SIZE_GETTER = new SizeGetter();

	private SizeGetter() { }
	
	@Override
	public Integer get(WebDriver driver, List<WebElement> elements) {
		return elements.size();
	}
	
	@Override
	public String toString() {
		return "size()";
	}
	
}