package io.github.seleniumquery.locator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class CssLocator {
	
	private String cssSelector;
	private By.ByCssSelector by;
	
	public CssLocator(String cssSelector) {
		this.cssSelector = cssSelector;
		this.by = new By.ByCssSelector(cssSelector);
	}

	public List<WebElement> locate(SearchContext context) {
		return by.findElements(context);
	}
	
	public String geCssSelector() {
		return this.cssSelector;
	}

}