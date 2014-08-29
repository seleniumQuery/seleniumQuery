package io.github.seleniumquery.locator;

import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public interface Locator {
	
	List<WebElement> locate(SearchContext context);
	
}