package io.github.seleniumquery;

import io.github.seleniumquery.globalfunctions.driver.SeleniumQueryDriver;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * The seleniumQuery browser. Adds several utility functions to the WebDriver class.
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
@Deprecated
public class SeleniumQueryDeprecatedBrowser {
	
	private SeleniumQueryBaseBrowser browser;
	public SeleniumQueryDeprecatedBrowser(SeleniumQueryBaseBrowser browser) { this.browser = browser; }

	/** This function has been deprecated.<br> Use: <b><code>$.driver();</code> */
	@Deprecated public SeleniumQueryDriver globalDriver() { return browser.driver(); }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().use(webDriverInstance);</code> */
	@Deprecated public SeleniumQueryDeprecatedBrowser setDefaultDriver(WebDriver defaultDriver) { browser.driver().use(defaultDriver); return this; }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().useHtmlUnit();</code> */
	@Deprecated public SeleniumQueryDeprecatedBrowser setDefaultDriverAsHtmlUnit() { browser.driver().useHtmlUnit(); return this; }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().useFirefox();</code> */
	@Deprecated public SeleniumQueryDeprecatedBrowser setDefaultDriverAsFirefox() { browser.driver().useFirefox(); return this; }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().useChrome();</code> */
	@Deprecated public SeleniumQueryDeprecatedBrowser setDefaultDriverAsChrome() { browser.driver().useChrome(); return this; }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().useChrome().withPathToChromeDriverExe(path);</code> */
	@Deprecated public SeleniumQueryDeprecatedBrowser setDefaultDriverAsChrome(String path) { browser.driver().useChrome().withPathToChromeDriverExe(path); return this; }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().useInternetExplorer();</code> */
	@Deprecated public SeleniumQueryDeprecatedBrowser setDefaultDriverAsIE() { browser.driver().useInternetExplorer(); return this; }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().useInternetExplorer().withPathToIEDriverServerExe(path);</code> */
	@Deprecated public SeleniumQueryDeprecatedBrowser setDefaultDriverAsIE(String path) { browser.driver().useInternetExplorer().withPathToIEDriverServerExe(path); return this; }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().usePhantomJS();</code> */
	@Deprecated public SeleniumQueryDeprecatedBrowser setDefaultDriverAsPhantomJS() { browser.driver().usePhantomJS(); return this; }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().usePhantomJS().withPathToPhantomJsExe(path);</code> */
	@Deprecated public SeleniumQueryDeprecatedBrowser setDefaultDriverAsPhantomJS(String path) { browser.driver().usePhantomJS().withPathToPhantomJsExe(path); return this; }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().get();</code> */
	@Deprecated public WebDriver getDefaultDriver() { return browser.driver().get(); }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().quit();</code> */
	@Deprecated public void quitDefaultDriver() { browser.driver().quit(); }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().quit();</code> */
	@Deprecated public void quitDefaultBrowser() { browser.driver().quit(); }

	/** This function has been deprecated.<br> Use: <b><code>$.driver().quit();</code> */
	@Deprecated public void quit() { browser.driver().quit(); }
	
	/** This function has been deprecated.<br> Use: <b><code>$.url(url);</code> */
	@Deprecated public void openUrl(String url) { browser.url(url); }

	/** This function has been deprecated.<br> Use: <b><code>$.url(file);</code> */
	@Deprecated public void openUrl(File file) { browser.url(file); }

	/** This function has been deprecated.<br> Use: <b><code>$.url(url);</code> */
	@Deprecated public void open(String url) { browser.url(url); }

	/** This function has been deprecated.<br> Use: <b><code>$.url(file);</code> */
	@Deprecated public void open(File file) { browser.url(file); }

	/** This function has been deprecated.<br> Use: <b><code>$.url(url);</code> */
	@Deprecated public void url(String url) { browser.url(url); }

	/** This function has been deprecated.<br> Use: <b><code>$.url(file);</code> */
	@Deprecated public void url(File file) { browser.url(file); }

	/** This function has been deprecated.<br> Use: <b><code>$.url();</code> */
	@Deprecated public String url() { return browser.url(); }
	
	/** This function has been deprecated.<br> Use: <b><code>$.url();</code> */
	@Deprecated public String getCurrentUrl() { return browser.url(); }

	/**
	 * Attempts to maximize the window of the default driver.
	 *
	 * @since 0.9.0
	 */
	public void maximizeWindow() {
		getDefaultDriver().manage().window().maximize();
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
			System.out.println("Sleeping for " + millis + " milliseconds.");
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

}