package org.openqa.selenium.seleniumquery.staticfunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.seleniumquery.SeleniumQueryConfig;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumQueryStaticWait {
	
	private SeleniumQueryBrowser browser;
	
	public SeleniumQueryStaticWait(SeleniumQueryBrowser browser) {
		this.browser = browser;
	}
	
	public void anyVisibleElementContainsText(String text) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(browser.getDefaultDriver(), SeleniumQueryConfig.getWaitTimeoutInSeconds());
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(text(),'" + text + "')])")));
		} catch (TimeoutException e) {
			throw new RuntimeException("No element containing the text \""+text+"\" was found and visible.");
		}
	}
	
	/**
	 * Instructs the browser (thread) to wait (sleep) for the time passed as argument.<br>
	 * <br>
	 * Example: <code>$.browser.waitUntil.after(10, TimeUnit.SECONDS); // sleeps for 10 seconds</code>
	 * 
	 * @param timeToWait
	 * @param timeUnit
	 */
	public void after(int timeToWait, TimeUnit timeUnit) {
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
	
	public void after(int timeToWaitInSeconds) {
		after(timeToWaitInSeconds, TimeUnit.SECONDS);
	}

}