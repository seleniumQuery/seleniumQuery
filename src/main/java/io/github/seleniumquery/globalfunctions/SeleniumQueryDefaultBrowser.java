package io.github.seleniumquery.globalfunctions;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import io.github.seleniumquery.SeleniumQueryConfig;

/**
 * @author acdcjunior
 * @since 0.2.0
 */
public class SeleniumQueryDefaultBrowser {
	
    private static final Log LOGGER = LogFactory.getLog(SeleniumQueryDefaultBrowser.class);
	
	private WebDriver defaultDriver;

	public SeleniumQueryDefaultBrowser() { }
	
	public SeleniumQueryDefaultBrowser setDefaultDriver(WebDriver defaultDriver) {
		this.defaultDriver = defaultDriver;
		this.setDriverTimeout();
		return this;
	}
	
	private void setDriverTimeout() {
		this.defaultDriver.manage().timeouts().implicitlyWait(SeleniumQueryConfig.getGlobalTimeoutInMillisseconds(), TimeUnit.MILLISECONDS);
	}
	
	public WebDriver getDefaultDriver() {
		if (this.defaultDriver == null) {
			initializeHtmlUnitDefaultDriver();
		}
		return this.defaultDriver;
	}

	/**
	 * Attempts to initialize the default driver with a HtmlUnitDriver, whatever
	 * version is available at the classpath.
	 */
	private void initializeHtmlUnitDefaultDriver() {
		try {
			LOGGER.debug("No default browser was set. Attempting to initialize HtmlUnitDriver.");
			Class<?> clazz = Class.forName("org.openqa.selenium.htmlunit.HtmlUnitDriver");
			Constructor<?> constructor = clazz.getConstructor(Boolean.TYPE);
			WebDriver driver = (WebDriver) constructor.newInstance(true);
			setDefaultDriver(driver);
			LOGGER.debug("Initialized HtmlUnitDriver as default driver.");
		} catch (Exception e) {
			throw new RuntimeException("No HtmlUnitDriver was found on the classpath. Please set " +
					"the global default driver for seleniumQuery -- the $() functions -- through " +
					"$.browser.setDefaultDriver(YourDriverClassInstance) before using it.");
		}
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
			long timeInMillis = TimeUnit.MILLISECONDS.convert(timeToWait, timeUnit);
			int millis = safeToInt(timeInMillis);
			LOGGER.debug("Sleeping for "+millis+" milliseconds.");
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private int safeToInt(long longNum) {
		if (longNum > Integer.MAX_VALUE) {
			longNum = Integer.MAX_VALUE;
		}
		return (int) longNum;
	}
	
	/**
	 * Instructs the browser (thread) to wait (sleep) for the time <b>in milliseconds</b> passed as argument.<br>
	 * <br>
	 * Example: <code>$.browser.sleep(10 * 1000); // sleeps for 10 seconds</code>
	 * 
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public void sleep(int millis) {
		sleep(millis, TimeUnit.MILLISECONDS);
	}

	/**
	 * Sets the URL for the default browser.
	 * @param url TODO
	 * 
	 * @author acdcjunior
	 * @since 0.5.0
	 */
	public void openUrl(String urlToOpen) {
		LOGGER.debug("Opening URL: "+urlToOpen);
		getDefaultDriver().get(urlToOpen);
	}

	/**
	 * Opens the given file as a URL in the browser.
	 * @param file the file to open.
	 * 
	 * @author acdcjunior
	 * @since 0.5.0
	 */
	public void openUrl(File file) {
		openUrl(file.toURI().toString());
	}

	/**
	 * Returns the current URL in the default browser.
	 * @return the currently opened url.
	 * 
	 * @author acdcjunior
	 * @since 0.5.0
	 */
	public String url() {
		return getDefaultDriver().getCurrentUrl();
	}
	
	/**
	 * Attempts to maximize the window of the default driver.
	 * 
	 * @author acdcjunior
	 * @since 0.7.0
	 */
	public void maximizeWindow() {
		getDefaultDriver().manage().window().maximize();
	}
	
	/**
	 * Returns the current URL in the default browser.
	 * @return the currently opened url.
	 * 
	 * @author acdcjunior
	 * @since 0.5.0
	 */
	public String getCurrentUrl() {
		return getDefaultDriver().getCurrentUrl();
	}
	
}