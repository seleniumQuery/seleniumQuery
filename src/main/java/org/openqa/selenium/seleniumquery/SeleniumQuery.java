package org.openqa.selenium.seleniumquery;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.seleniumquery.element.SeleniumQueryHtmlElementList;

public class SeleniumQuery {
	
	public static final SeleniumQueryStaticFunctions $ = new SeleniumQueryStaticFunctions();
	
	public static SeleniumQueryHtmlElementList $(String selector) {
		return new SeleniumQueryHtmlElementList($.browser.getDefaultDriver(), selector);
	}
	
	public static SeleniumQueryHtmlElementList $(WebDriver driver, String selector) {
		return new SeleniumQueryHtmlElementList(driver, selector);
	}
	
}