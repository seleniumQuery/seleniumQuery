package org.openqa.selenium.seleniumquery;

import org.openqa.selenium.WebDriver;

public class SeleniumQuery {
	
	public static final SeleniumQueryStatic $ = new SeleniumQueryStatic();
	
	public static SeleniumQueryObject $(String selector) {
		return new SeleniumQueryObject($.browser.getDefaultDriver(), selector);
	}
	
	public static SeleniumQueryObject $(WebDriver driver, String selector) {
		return new SeleniumQueryObject(driver, selector);
	}
	
}