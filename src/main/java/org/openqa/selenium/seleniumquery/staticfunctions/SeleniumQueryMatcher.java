package org.openqa.selenium.seleniumquery.staticfunctions;

public class SeleniumQueryMatcher {

	private SeleniumQueryBrowser browser;
	
	public SeleniumQueryMatcher(SeleniumQueryBrowser browser) {
		this.browser = browser;
	}
	
	public boolean screenContainsText(String texto) {
		return this.browser.getDefaultDriver().getPageSource().contains(texto);
	}
	
}