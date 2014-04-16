package io.github.seleniumquery.globalfunctions;

import java.io.File;


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
	
	/**
	 * Sets the URL for the default browser.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
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
	
	/**
	 * Opens the given file in the browser.
	 * 
	 * @param file the file to open.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public void href(File file) {
		this.href(file.toURI().toString());
	}
	
	/**
	 * Returns the current URL in the default browser.
	 * @return the currently opened url.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public String href() {
		return browser.getDefaultDriver().getCurrentUrl();
	}

}