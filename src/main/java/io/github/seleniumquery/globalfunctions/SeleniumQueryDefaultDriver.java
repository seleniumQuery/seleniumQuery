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
 * @since 1.0.0
 */
public class SeleniumQueryDefaultDriver {
	
    private static final Log LOGGER = LogFactory.getLog(SeleniumQueryDefaultDriver.class);
	
	private WebDriver defaultDriver;

	public SeleniumQueryDefaultDriver() { }
	
	public SeleniumQueryDefaultDriver setDefaultDriver(WebDriver defaultDriver) {
		this.defaultDriver = defaultDriver;
		this.setDriverTimeout();
		return this;
	}
	
	private DriverInstantiationUtils driverInstantiationUtils = new DriverInstantiationUtils();
	
	public SeleniumQueryDefaultDriver setDefaultDriverAsHtmlUnit() {
		return setDefaultDriver(driverInstantiationUtils.instantiateHtmlUnitDriverWithoutPath());
	}
	
	public SeleniumQueryDefaultDriver setDefaultDriverAsFirefox() {
		return setDefaultDriver(driverInstantiationUtils.instantiateFirefoxDriverWithoutPath());
	}

	/**
	 * Sets <b>Chrome</b> as the default {@link WebDriver} for seleniumQuery.
	 * <p>
	 * Note that the Chrome driver needs a <i>server executable</i> to bridge Selenium to the browser and as such
	 * Selenium must have the path to it. It is a file usually named <code>chromedriver.exe</code> and its latest
	 * version can be downloaded from <a href="http://chromedriver.storage.googleapis.com/index.html">ChromeDriver's
	 * download page</a> -- or check <a
	 * href="https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-Chrome-as-WebDriver-Browser">
	 * seleniumQuery and Chrome as WebDriver/Browser wiki page</a> for the latest info.
	 * </p>
	 * <p><b> This method looks for the chromedriver.exe at the CLASSPATH.</b> If you wish to directly specify a path,
	 * use {@link #setDefaultDriverAsChrome(String)}</p>
	 * <p>
	 * For more info, see <a href="https://code.google.com/p/selenium/wiki/ChromeDriver">ChromeDriver's official wiki</a>.
	 * </p>
	 */
	public SeleniumQueryDefaultDriver setDefaultDriverAsChrome() {
		return setDefaultDriver(driverInstantiationUtils.instantiateChromeDriverWithoutPath());
	}

	/**
	 * Sets <b>Chrome</b> as the default {@link WebDriver} for seleniumQuery.
	 * <p>
	 * Note that the Chrome driver needs a <i>server executable</i> to bridge Selenium to the browser and as such
	 * Selenium must have the path to it. It is a file usually named <code>chromedriver.exe</code> and its latest
	 * version can be downloaded from <a href="http://chromedriver.storage.googleapis.com/index.html">ChromeDriver's
	 * download page</a> -- or check <a
	 * href="https://github.com/seleniumQuery/seleniumQuery/wiki/seleniumQuery-and-Chrome-as-WebDriver-Browser">
	 * seleniumQuery and Chrome as WebDriver/Browser wiki page</a> for the latest info.
	 * </p>
	 * <p><b> This method looks for the chromedriver.exe at the path specified by the <code>pathToChromeDriverExe</code>
	 * argument.</b></p>
	 * <p>
	 * For more info, see <a href="https://code.google.com/p/selenium/wiki/ChromeDriver">ChromeDriver's official wiki</a>.
	 * </p>
	 *
	 * @param pathToChromeDriverExe The full path to the executable server file. Examples:
	 *     <code>"C:\\myFiles\\chromedriver.exe"</code>; can be relative, as in <code>"..\\stuff\\chromedriver.exe"</code>,
	 *     does not matter if the .exe was renamed, such as <code>"drivers\\chrome\\chromedriver_v12345.exe"</code>.
	 */
	public SeleniumQueryDefaultDriver setDefaultDriverAsChrome(String pathToChromeDriverExe) {
		return setDefaultDriver(driverInstantiationUtils.instantiateChromeDriverWithPath(pathToChromeDriverExe));
	}
	
	/**
	 * This method looks for the IEDriverServer.exe at the CLASSPATH. (Tipically at a resources/ folder of a
	 * maven project.)
	 */
	public SeleniumQueryDefaultDriver setDefaultDriverAsIE() {
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
	public SeleniumQueryDefaultDriver setDefaultDriverAsIE(String pathToIEDriverServerExe) {
		return setDefaultDriver(driverInstantiationUtils.instantiateIeDriverWithPath(pathToIEDriverServerExe));
	}
	
	public SeleniumQueryDefaultDriver setDefaultDriverAsPhantomJS() {
		return setDefaultDriver(driverInstantiationUtils.instantiatePhantomJsDriverWithoutPath());
	}
	
	public SeleniumQueryDefaultDriver setDefaultDriverAsPhantomJS(String pathToPhantomJs) {
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
	 * @since 1.0.0
	 */
	public WebDriver getDefaultDriver() {
		if (this.defaultDriver == null) {
			setDefaultDriverAsHtmlUnit();
		}
		return this.defaultDriver;
	}

	/**
	 * Quits the seleniumQuery default WebDriver (the one available at <code>$.browser.getDefaultDriver()</code>).
	 */
	public void quitDefaultDriver() {
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
	 * Opens the given URL in the default browser.
	 * @param urlToOpen The URL to be opened. Example: "http://seleniumquery.github.io"
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