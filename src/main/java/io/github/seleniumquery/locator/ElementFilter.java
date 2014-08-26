package io.github.seleniumquery.locator;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ElementFilter {
	
	public static final ElementFilter FILTER_NOTHING = new ElementFilter() {
		@Override
		public List<WebElement> filterElements(WebDriver driver, List<WebElement> elements) {
			return elements;
		}
		@Override
		public String toString() {
			return "FILTER_NOTHING";
		};
	};

	List<WebElement> filterElements(WebDriver driver, List<WebElement> elements);
	
}