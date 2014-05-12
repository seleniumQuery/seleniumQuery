package io.github.seleniumquery.by.selector;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface SqCSSFilter {
	
	public static final SqCSSFilter FILTER_NOTHING = new SqCSSFilter() {
		@Override
		public List<WebElement> filter(List<WebElement> elements) {
			return elements;
		}
		@Override
		public String toString() {
			return "FILTER_NOTHING";
		};
	};

	List<WebElement> filter(List<WebElement> elements);
	
}