package org.openqa.selenium.seleniumquery;

import org.openqa.selenium.seleniumquery.globalfunctions.SeleniumQueryBrowser;
import org.openqa.selenium.seleniumquery.globalfunctions.SeleniumQueryLocation;
import org.openqa.selenium.seleniumquery.globalfunctions.SeleniumQueryStaticWait;

public class SeleniumQueryStatic {
	
	public final SeleniumQueryBrowser browser;
	public final SeleniumQueryLocation location;
	public final SeleniumQueryStaticWait waitUntil;
	
	public SeleniumQueryStatic() {
		this.browser = new SeleniumQueryBrowser();
		this.location = new SeleniumQueryLocation(this.browser);
		this.waitUntil = new SeleniumQueryStaticWait(this.browser);
	}

}