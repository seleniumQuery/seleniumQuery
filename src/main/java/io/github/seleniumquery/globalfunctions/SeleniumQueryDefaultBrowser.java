package io.github.seleniumquery.globalfunctions;

import io.github.seleniumquery.SeleniumQueryConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

/**
 * The seleniumQuery browser. Adds several utility functions to the WebDriver class.
 * 
 * @author acdcjunior
 * @since 1.0.0
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
	
	private DriverInstantiationUtils driverInstantiationUtils = new DriverInstantiationUtils();
	
	public SeleniumQueryDefaultBrowser setDefaultDriverAsHtmlUnit() {
		return setDefaultDriver(driverInstantiationUtils.instantiateHtmlUnitDriverWithoutPath());
	}
	
	public SeleniumQueryDefaultBrowser setDefaultDriverAsFirefox() {
		return setDefaultDriver(driverInstantiationUtils.instantiateFirefoxDriverWithoutPath());
	}

	/**
	 * This method looks for the chromedriver.exe at the classpath. (Tipically at a resources/ folder of a
	 * maven project.)
	 */
	public SeleniumQueryDefaultBrowser setDefaultDriverAsChrome() {
		return setDefaultDriver(driverInstantiationUtils.instantiateChromeDriverWithoutPath());
	}

	/**
	 * Sets Chrome as the default driver for seleniumQuery.
	 * <p>
	 * Note that, as Chrome needs a "server" to bridge selenium to the browser, you have
	 * to point the path to it. It is a file usually named "chromedriver.exe" and its latest
	 * version can be downloaded from http://chromedriver.storage.googleapis.com/index.html.
	 * </p>
	 * <p>
	 * For more info, check https://code.google.com/p/selenium/wiki/ChromeDriver
	 * </p>
	 * 
	 * @param pathToChromeDriverExe The full path to the chromedriver.exe file.
	 */
	public SeleniumQueryDefaultBrowser setDefaultDriverAsChrome(String pathToChromeDriverExe) {
		return setDefaultDriver(driverInstantiationUtils.instantiateChromeDriverWithPath(pathToChromeDriverExe));
	}
	
	/**
	 * This method looks for the IEDriverServer.exe at the classpath. (Tipically at a resources/ folder of a
	 * maven project.)
	 */
	public SeleniumQueryDefaultBrowser setDefaultDriverAsIE() {
		return setDefaultDriver(driverInstantiationUtils.instantiateIeDriverWithoutPath());
	}

	/**
	 * Sets IE as the default driver for seleniumQuery.
	 * <p>
	 * Note that, as IE needs a "server" to bridge selenium to the browser, you have
	 * to point the path to it. It is a file usually named "IEDriverServer.exe" and its latest
	 * version can be downloaded from http://selenium-release.storage.googleapis.com/index.html.
	 * </p>
	 * <p>
	 * For more info, check https://code.google.com/p/selenium/wiki/InternetExplorerDriver
	 * </p>
	 * 
	 * @param pathToIEDriverServerExe The full path to the IEDriverServer.exe file.
	 */
	public SeleniumQueryDefaultBrowser setDefaultDriverAsIE(String pathToIEDriverServerExe) {
		return setDefaultDriver(driverInstantiationUtils.instantiateIeDriverWithPath(pathToIEDriverServerExe));
	}
	
	public SeleniumQueryDefaultBrowser setDefaultDriverAsPhantomJS() {
		return setDefaultDriver(driverInstantiationUtils.instantiatePhantomJsDriverWithoutPath());
	}
	
	public SeleniumQueryDefaultBrowser setDefaultDriverAsPhantomJS(String pathToPhantomJs) {
		return setDefaultDriver(driverInstantiationUtils.instantiatePhantomJsDriverWithPath(pathToPhantomJs));
	}
	
	private void setDriverTimeout() {
		this.defaultDriver.manage().timeouts().implicitlyWait(SeleniumQueryConfig.getGlobalTimeoutInMillisseconds(), TimeUnit.MILLISECONDS);
	}
	
	public WebDriver getDefaultDriver() {
		if (this.defaultDriver == null) {
			setDefaultDriverAsHtmlUnit();
		}
		return this.defaultDriver;
	}

	/**
	 * Quits the seleniumQuery default WebDriver (the one available at <code>$.browser.getDefaultBrowser()</code>).
	 */
	public void quitDefaultBrowser() {
		this.defaultDriver.quit();
	}
	
	/**
	 * Quits the given WebDriver.
	 * 
	 * @param webDriver The WebDriver instance to be quitted.
	 */
	public void quit(WebDriver webDriver) {
		webDriver.quit();
	}
	
	/**
	 * Instructs the browser (thread) to wait (sleep) for the time passed as argument.<br>
	 * <br>
	 * Example: <code>$.browser.sleep(10, TimeUnit.SECONDS); // sleeps for 10 seconds</code>
	 * 
	 * @since 1.0.0
	 */
	public void sleep(int timeToWait, TimeUnit timeUnit) {
		try {
			long timeInMillis = TimeUnit.MILLISECONDS.convert(timeToWait, timeUnit);
			int millis = (int) Math.min(timeInMillis, Integer.MAX_VALUE); // using .min() to allow a safe casting
			LOGGER.debug("Sleeping for "+millis+" milliseconds.");
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Instructs the browser (thread) to wait (sleep) for the time <b>in milliseconds</b> passed as argument.<br>
	 * <br>
	 * Example: <code>$.browser.sleep(10 * 1000); // sleeps for 10 seconds</code>
	 * 
	 * @since 1.0.0
	 */
	public void sleep(int millis) {
		sleep(millis, TimeUnit.MILLISECONDS);
	}

	/**
	 * Sets the URL for the default browser.
	 * @param url The URL to be opened. Example: "http://seleniumquery.github.io"
	 * 
	 * @since 1.0.0
	 */
	public void openUrl(String urlToOpen) {
		LOGGER.debug("Opening URL: "+urlToOpen);
		getDefaultDriver().get(urlToOpen);
	}

	/**
	 * Opens the given file as a URL in the browser.
	 * @param file the file to open.
	 * 
	 * @since 1.0.0
	 */
	public void openUrl(File file) {
		openUrl(file.toURI().toString());
	}

	/**
	 * Returns the current URL in the default browser.
	 * @return the currently opened URL.
	 * 
	 * @since 1.0.0
	 */
	public String url() {
		return getCurrentUrl();
	}
	
	/**
	 * Attempts to maximize the window of the default driver.
	 * 
	 * @since 1.0.0
	 */
	public void maximizeWindow() {
		getDefaultDriver().manage().window().maximize();
	}
	
	/**
	 * Returns the current URL in the default browser.
	 * @return the currently opened URL.
	 * 
	 * @since 1.0.0
	 */
	public String getCurrentUrl() {
		return getDefaultDriver().getCurrentUrl();
	}
	
}