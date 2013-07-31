package org.openqa.selenium.seleniumquery;

import org.openqa.selenium.WebDriver;

public class SQuery {
	
	private static String defaultContext = "";
	
	public static String getDefaultContext() {
		return SQuery.defaultContext;
	}

	public static void setDefaultContext(String defaultContext) {
		SQuery.defaultContext = defaultContext;
	}

	private static WebDriver defaultDriver;
	
	public static WebDriver getDefaultDriver() {
		return SQuery.defaultDriver;
	}

	public static void setDefaultDriver(WebDriver defaultDriver) {
		SQuery.defaultDriver = defaultDriver;
	}
	
	public static SQueryBrowser sQ() {
		return new SQueryBrowser(SQuery.getDefaultDriver());
	}
	
	public static SQueryHtmlElement sQ(String selector) {
		return new SQueryHtmlElement(SQuery.getDefaultDriver(), selector);
	}
	
	public static SQueryBrowser sQ(WebDriver driver) {
		return new SQueryBrowser(driver);
	}
	
	public static SQueryHtmlElement sQ(WebDriver driver, String selector) {
		return new SQueryHtmlElement(driver, selector);
	}
	
	public static SQueryHtmlElementList sQList(String selector) {
		return new SQueryHtmlElementList(SQuery.getDefaultDriver(), selector);
	}

}
