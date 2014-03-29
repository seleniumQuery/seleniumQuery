package org.openqa.selenium.seleniumquery.globalfunctions;


public class SeleniumQueryLocation {
	
	private SeleniumQueryBrowser browser;
	
	private String defaultContext = "";
	
	public SeleniumQueryLocation(SeleniumQueryBrowser browser) {
		this.browser = browser;
	}
	
	public String getDefaultContext() {
		return this.defaultContext;
	}
	
	public void setDefaultContext(String defaultContext) {
		this.defaultContext = defaultContext;
	}
	
	public void href(String url) {
		String urlToOpen = url;
		if (this.getDefaultContext() != null && !this.getDefaultContext().isEmpty()) {
			if (!"/".equals(urlToOpen.substring(0, 1))) {
				urlToOpen = "/" + urlToOpen;
			}
			urlToOpen = this.getDefaultContext() + urlToOpen;
		}
		System.out.println("Opening URL: "+urlToOpen);
		browser.getDefaultDriver().get(urlToOpen);
	}

}
