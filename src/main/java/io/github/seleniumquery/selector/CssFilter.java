package io.github.seleniumquery.selector;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface CssFilter {
	
	public static final CssFilter FILTER_NOTHING = new CssFilter() {
		@Override
		public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
			return elements;
		}
		@Override
		public String toString() {
			return "FILTER_NOTHING";
		};
	};

	List<WebElement> filter(WebDriver driver, List<WebElement> elements);
	
}