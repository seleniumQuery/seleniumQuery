package org.openqa.selenium.seleniumquery.staticfunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.seleniumquery.SeleniumQueryConfig;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class SeleniumQueryBrowser {
	
	private WebDriver defaultDriver;
	
	public SeleniumQueryBrowser() {
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
		driver.setJavascriptEnabled(true);
		
		this.defaultDriver = driver;
		this.setDriverTimeout();
	}
	
	public SeleniumQueryBrowser setDefaultDriver(WebDriver defaultDriver) {
		this.defaultDriver = defaultDriver;
		this.setDriverTimeout();
		return this;
	}
	
	private void setDriverTimeout() {
		this.defaultDriver.manage().timeouts().implicitlyWait(SeleniumQueryConfig.getGlobalTimeoutInMillisseconds(), TimeUnit.MILLISECONDS);
	}
	
	public WebDriver getDefaultDriver() {
		return this.defaultDriver;
	}
	
	public void quit() {
		this.defaultDriver.quit();
	}
	
	public void quit(WebDriver webDriver) {
		webDriver.quit();
	}

}