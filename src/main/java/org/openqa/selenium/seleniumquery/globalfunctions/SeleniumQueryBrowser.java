package org.openqa.selenium.seleniumquery.globalfunctions;

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
	
	public void quitDefaultBrowser() {
		this.defaultDriver.quit();
	}
	
	public void quit(WebDriver webDriver) {
		webDriver.quit();
	}
	
	/**
	 * Instructs the browser (thread) to wait (sleep) for the time passed as argument.<br>
	 * <br>
	 * Example: <code>$.browser.sleep(10, TimeUnit.SECONDS); // sleeps for 10 seconds</code>
	 * 
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public void sleep(int timeToWait, TimeUnit timeUnit) {
		try {
			long secondsToWait = timeUnit.convert(timeToWait, TimeUnit.SECONDS) * 1000;
			if (secondsToWait > Integer.MAX_VALUE) {
				secondsToWait = Integer.MAX_VALUE;
			}
			System.out.println("Sleeping for "+secondsToWait+" seconds...");
			Thread.sleep((int) secondsToWait);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Instructs the browser (thread) to wait (sleep) for the time <b>in seconds</b> passed as argument.<br>
	 * <br>
	 * Example: <code>$.browser.sleep(10); // sleeps for 10 seconds</code>
	 * 
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public void sleep(int timeToWaitInSeconds) {
		sleep(timeToWaitInSeconds, TimeUnit.SECONDS);
	}

}