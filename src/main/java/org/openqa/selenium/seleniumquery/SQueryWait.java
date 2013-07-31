package org.openqa.selenium.seleniumquery;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class SQueryWait {
	
	private By by;
	private WebDriver driver;
	private String selector;

	public SQueryWait(WebDriver driver, By by, String selector) {
		this.driver = driver;
		this.by = by;
		this.selector = selector;
	}

	public WebElement fluentWait(Function<By, WebElement> function) {
		try {
			return new FluentWait<By>(this.by)
					.withTimeout(SQueryProperties.getTimeoutInSeconds(), TimeUnit.SECONDS)
					.pollingEvery(SQueryProperties.getPollingInMillisseconds(), TimeUnit.MILLISECONDS)
					.ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class)
					.until(function);
		} catch (TimeoutException te) {
			try {
				PrintWriter out = new PrintWriter(SQueryProperties.get("ERROR_PAGE_HTML_LOCATION"));
				out.println(this.driver.getPageSource());
				out.close();
				if (this.driver instanceof TakesScreenshot) {
					File srcFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(srcFile, new File(SQueryProperties.get("ERROR_PAGE_SCREENSHOT_LOCATION")));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			throw new RuntimeException("TimeoutException while waiting for selector: "+this.selector, te);
		}
	}

}
