package io.github.seleniumquery.globalfunctions;

import io.github.seleniumquery.SeleniumQueryConfig;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

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
		try {
			File chromeDriverExeFile = new File(pathToChromeDriverExe);
			String chromeDriverExeFilePath = chromeDriverExeFile.getCanonicalPath();
			if (!chromeDriverExeFile.exists()) {
				throw new RuntimeException("No Chrome Driver Server file was found at '"+chromeDriverExeFilePath
						+"'. Download the latest release at http://chromedriver.storage.googleapis.com/index.html " +
						"and place it there or specify a different path using " +
						"$.browser.setDefaultDriverAsChrome(\"other/path/to/chromedriver.exe\").");
			}
			System.setProperty("webdriver.chrome.driver", chromeDriverExeFilePath);
			return setDefaultDriver(new ChromeDriver());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * This method looks for the chromedriver.exe at the classpath. (Tipically at a resources/ folder of a
	 * maven project.)
	 */
	public SeleniumQueryDefaultBrowser setDefaultDriverAsChrome() {
		URL chromeDriverResource = getClass().getResource("/chromedriver.exe");
		String pathToChromeDriver = null;
		if (chromeDriverResource == null) {
			pathToChromeDriver = getClass().getResource("/").getPath()+"/chromedriver.exe";
		} else {
			pathToChromeDriver = chromeDriverResource.getPath();
		}
		return setDefaultDriverAsChrome(pathToChromeDriver);
	}
	
	/**
	 * This method looks for the IEDriverServer.exe at the classpath. (Tipically at a resources/ folder of a
	 * maven project.)
	 */
	public SeleniumQueryDefaultBrowser setDefaultDriverAsIE() {
		URL ieDriverServerResource = getClass().getResource("/IEDriverServer.exe");
		String pathToIeDriverServer = null;
		if (ieDriverServerResource == null) {
			pathToIeDriverServer = getClass().getResource("/").getPath()+"/IEDriverServer.exe";
		} else {
			pathToIeDriverServer = ieDriverServerResource.getPath();
		}
		return setDefaultDriverAsIE(pathToIeDriverServer);
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
		try {
			File ieDriverServerExeFile = new File(pathToIEDriverServerExe);
			String ieDriverServerExeFilePath = ieDriverServerExeFile.getCanonicalPath();
			if (!ieDriverServerExeFile.exists()) {
				throw new RuntimeException("No IE Driver Server file was found at '"+ieDriverServerExeFilePath
						+"'. Download the latest release at http://selenium-release.storage.googleapis.com/index.html " +
						"and place it there or specify a different path using " +
						"$.browser.setDefaultDriverAsIE(\"other/path/to/IEDriverServer.exe\").");
			}
			System.setProperty("webdriver.ie.driver", ieDriverServerExeFilePath);
			return setDefaultDriver(new InternetExplorerDriver());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public SeleniumQueryDefaultBrowser setDefaultDriverAsFirefox() {
		return setDefaultDriver(new FirefoxDriver());
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
			initializeDriverByClassName("org.openqa.selenium.htmlunit.HtmlUnitDriver");
			LOGGER.debug("Initialized HtmlUnitDriver as default driver.");
		} catch (Exception e) {
			throw new RuntimeException("No HtmlUnitDriver was found on the classpath. Please set " +
					"the global default driver for seleniumQuery -- the $() functions -- through " +
					"$.browser.setDefaultDriver(YourDriverClassInstance) before using it.");
		}
	}

	private SeleniumQueryDefaultBrowser initializeDriverByClassName(final String className) throws Exception {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(Boolean.TYPE);
		WebDriver driver = (WebDriver) constructor.newInstance(true);
		return setDefaultDriver(driver);
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