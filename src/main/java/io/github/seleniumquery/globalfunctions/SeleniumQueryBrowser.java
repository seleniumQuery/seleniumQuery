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
 *
 * @since 0.9.0
 */
public class SeleniumQueryBrowser {
	
    private static final Log LOGGER = LogFactory.getLog(SeleniumQueryBrowser.class);
	
	private WebDriver defaultDriver;

	public SeleniumQueryBrowser() { }
	
	public SeleniumQueryBrowser setDefaultDriver(WebDriver defaultDriver) {
		this.defaultDriver = defaultDriver;
		this.setDriverTimeout();
		return this;
	}
	
	protected DriverInstantiationUtils driverInstantiationUtils = new DriverInstantiationUtils();

	public SeleniumQueryGlobalDriver globalDriver() {
		return new SeleniumQueryGlobalDriver(this);
	}

	/**
	 * @deprecated use <code>$.browser.globalDriver().useHtmlUnit();</code>
	 */
	@Deprecated
	public SeleniumQueryBrowser setDefaultDriverAsHtmlUnit() {
		return setDefaultDriver(driverInstantiationUtils.instantiateHtmlUnitDriverWithoutPath());
	}

	/**
	 * @deprecated use <code>$.browser.globalDriver().useFirefox();</code>
	 */
	@Deprecated
	public SeleniumQueryBrowser setDefaultDriverAsFirefox() {
		return setDefaultDriver(driverInstantiationUtils.instantiateFirefoxDriverWithoutPath());
	}

	/**
	 * @deprecated use <code>$.browser.globalDriver().useChrome();</code>
	 */
	@Deprecated
	public SeleniumQueryBrowser setDefaultDriverAsChrome() {
		return setDefaultDriver(driverInstantiationUtils.instantiateChromeDriverWithoutPath());
	}

	/**
	 * @deprecated use <code>$.browser.globalDriver().useChrome(path);</code>
	 */
	@Deprecated
	public SeleniumQueryBrowser setDefaultDriverAsChrome(String pathToChromeDriverExe) {
		return setDefaultDriver(driverInstantiationUtils.instantiateChromeDriverWithPath(pathToChromeDriverExe));
	}

	/**
	 * @deprecated use <code>$.browser.globalDriver().useInternetExplorer();</code>
	 */
	@Deprecated
	public SeleniumQueryBrowser setDefaultDriverAsIE() {
		return setDefaultDriver(driverInstantiationUtils.instantiateIeDriverWithoutPath());
	}

	/**
	 * @deprecated use <code>$.browser.globalDriver().useInternetExplorer(path);</code>
	 */
	@Deprecated
	public SeleniumQueryBrowser setDefaultDriverAsIE(String pathToIEDriverServerExe) {
		return setDefaultDriver(driverInstantiationUtils.instantiateIeDriverWithPath(pathToIEDriverServerExe));
	}

	/**
	 * @deprecated use <code>$.browser.globalDriver().usePhantomJS();</code>
	 */
	@Deprecated
	public SeleniumQueryBrowser setDefaultDriverAsPhantomJS() {
		return setDefaultDriver(driverInstantiationUtils.instantiatePhantomJsDriverWithoutPath());
	}

	/**
	 * @deprecated use <code>$.browser.globalDriver().usePhantomJS(path);</code>
	 */
	@Deprecated
	public SeleniumQueryBrowser setDefaultDriverAsPhantomJS(String pathToPhantomJs) {
		return setDefaultDriver(driverInstantiationUtils.instantiatePhantomJsDriverWithPath(pathToPhantomJs));
	}
	
	private void setDriverTimeout() {
		this.defaultDriver.manage().timeouts().implicitlyWait(SeleniumQueryConfig.getGlobalTimeout(), TimeUnit.MILLISECONDS);
	}

	/**
	 * <p>Returns the currently set default {@link WebDriver}.</p>
	 * <p><b>If no driver has been set before, it assigns a <code>HtmlUnitDriver</code> instance as driver and returns it.</b></p>
	 *
	 * @return the currently set default {@link WebDriver};
	 *
	 * @since 0.9.0
	 */
	public WebDriver getDefaultDriver() {
		if (this.defaultDriver == null) {
			globalDriver().useHtmlUnit();
		}
		return this.defaultDriver;
	}

	/**
	 * Quits the seleniumQuery global driver.
	 *
	 * @deprecated use <code>$.browser.quit();</code>
	 */
	@Deprecated
	public void quitDefaultDriver() {
		quit(this.defaultDriver);
	}

	/**
	 * Quits the seleniumQuery global driver.
	 */
	public void quit() {
		quit(this.defaultDriver);
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
	 * @since 0.9.0
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
	 * @since 0.9.0
	 */
	public void sleep(int millis) {
		sleep(millis, TimeUnit.MILLISECONDS);
	}

	/**
	 * @deprecated use <code>$.browser.open(url);</code>
	 */
	@Deprecated
	public void openUrl(String urlToOpen) {
		LOGGER.debug("Opening URL: "+urlToOpen);
		getDefaultDriver().get(urlToOpen);
	}

	/**
	 * @deprecated use <code>$.browser.open(file);</code>
	 */
	@Deprecated
	public void openUrl(File file) {
		openUrl(file.toURI().toString());
	}

	/**
	 * Opens the given URL in the default browser.
	 * @param urlToOpen The URL to be opened. Example: "http://seleniumquery.github.io"
	 *
	 * @since 0.9.0
	 */
	public void open(String urlToOpen) {
		LOGGER.debug("Opening URL: "+urlToOpen);
		getDefaultDriver().get(urlToOpen);
	}

	/**
	 * Opens the given file as a URL in the browser.
	 * @param fileToOpenAsURL the file to be opened.
	 *
	 * @since 0.9.0
	 */
	public void open(File fileToOpenAsURL) {
		openUrl(fileToOpenAsURL.toURI().toString());
	}

	/**
	 * Returns the current URL in the default browser.
	 * @return the currently opened URL.
	 * 
	 * @since 0.9.0
	 */
	public String url() {
		return getCurrentUrl();
	}
	
	/**
	 * Attempts to maximize the window of the default driver.
	 * 
	 * @since 0.9.0
	 */
	public void maximizeWindow() {
		getDefaultDriver().manage().window().maximize();
	}

	/**
	 * @deprecated use <code>$.browser.currentUrl();</code>
	 */
	@Deprecated
	public String getCurrentUrl() {
		return getDefaultDriver().getCurrentUrl();
	}

	/**
	 * Returns the current URL in the default browser.
	 * @return the currently opened URL.
	 *
	 * @since 0.9.0
	 */
	public String currentUrl() {
		return getDefaultDriver().getCurrentUrl();
	}
	
}