package io.github.seleniumquery.locator;

import io.github.seleniumquery.selectorxpath.XPathSelectorCompilerService;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class LocatorFactory {
	
	public static SearchContext create(WebDriver driver, String cssExtendedSelector) {
		
		String xPathExpression = XPathSelectorCompilerService.compileSelectorList(driver, cssExtendedSelector).toXPath();
		
		
		// TODO Auto-generated constructor stub
		
		return null;
	}

}