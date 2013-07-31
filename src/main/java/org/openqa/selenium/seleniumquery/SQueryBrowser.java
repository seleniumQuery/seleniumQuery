package org.openqa.selenium.seleniumquery;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SQueryBrowser {
	
	protected WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}

	public SQueryBrowser(WebDriver driver) {
		this.driver = driver;
		setDriverTimeout();
	}	
	
	private void setDriverTimeout() {
		this.driver.manage().timeouts().implicitlyWait(SQueryProperties.getGlobalTimeoutInMillisseconds(), TimeUnit.MILLISECONDS);
	}

	public void waitUntilAnyVisibleElementContainsText(String text) {
		try {
			new WebDriverWait(driver, SQueryProperties.getTimeoutInSeconds())
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(text(),'" + text + "')])")));
		} catch (TimeoutException e) {
			throw new RuntimeException("No element containing the text \""+text+"\" was found and visible.");
		}
	}

	public void sleep(int seconds) {
		try {
			System.out.println("Sleeping for "+seconds+" seconds...");
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean screenContainsText(String texto) {
		return this.driver.getPageSource().contains(texto);
	}
	
	public void openUrl(String url) {
		System.out.println("Opening URL: "+url);
		if (!"/".equals(url.substring(0, 1))) {
			url = "/" + url;
		}
		this.driver.get(SQuery.getDefaultContext() + url);
	}

}
