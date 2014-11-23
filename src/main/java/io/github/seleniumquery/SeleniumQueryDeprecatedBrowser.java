package io.github.seleniumquery;

import io.github.seleniumquery.globalfunctions.driver.SeleniumQueryDriver;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * The seleniumQuery browser. Adds several utility functions to the WebDriver class.
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 *
 * @deprecated This class refers to a deprecated way of accessing some functions and will be removed by the next release.
 * You'll find the functions for this object in <b>{@code $.function();}</b> or <b>{@code $.driver().function();}</b>.
 */
public class SeleniumQueryDeprecatedBrowser {
	
	private SeleniumQueryBrowserFunctions browser;
	public SeleniumQueryDeprecatedBrowser(SeleniumQueryBrowserFunctions browser) { this.browser = browser; }

	/** @deprecated Use: <b><code>$.driver();</code> */
	public SeleniumQueryDriver globalDriver() { return browser.driver(); }

	/** @deprecated Use: <b><code>$.driver().use(webDriverInstance);</code> */
	public SeleniumQueryDeprecatedBrowser setDefaultDriver(WebDriver defaultDriver) { browser.driver().use(defaultDriver); return this; }

	/** @deprecated Use: <b><code>$.driver().useHtmlUnit();</code> */
	public SeleniumQueryDeprecatedBrowser setDefaultDriverAsHtmlUnit() { browser.driver().useHtmlUnit(); return this; }

	/** @deprecated Use: <b><code>$.driver().useFirefox();</code> */
	public SeleniumQueryDeprecatedBrowser setDefaultDriverAsFirefox() { browser.driver().useFirefox(); return this; }

	/** @deprecated Use: <b><code>$.driver().useChrome();</code> */
	public SeleniumQueryDeprecatedBrowser setDefaultDriverAsChrome() { browser.driver().useChrome(); return this; }

	/** @deprecated Use: <b><code>$.driver().useChrome().withPathToChromeDriverExe(path);</code> */
	public SeleniumQueryDeprecatedBrowser setDefaultDriverAsChrome(String path) { browser.driver().useChrome().withPathToChromeDriverExe(path); return this; }

	/** @deprecated Use: <b><code>$.driver().useInternetExplorer();</code> */
	public SeleniumQueryDeprecatedBrowser setDefaultDriverAsIE() { browser.driver().useInternetExplorer(); return this; }

	/** @deprecated Use: <b><code>$.driver().useInternetExplorer().withPathToIEDriverServerExe(path);</code> */
	public SeleniumQueryDeprecatedBrowser setDefaultDriverAsIE(String path) { browser.driver().useInternetExplorer().withPathToIEDriverServerExe(path); return this; }

	/** @deprecated Use: <b><code>$.driver().usePhantomJS();</code> */
	public SeleniumQueryDeprecatedBrowser setDefaultDriverAsPhantomJS() { browser.driver().usePhantomJS(); return this; }

	/** @deprecated Use: <b><code>$.driver().usePhantomJS().withPathToPhantomJsExe(path);</code> */
	public SeleniumQueryDeprecatedBrowser setDefaultDriverAsPhantomJS(String path) { browser.driver().usePhantomJS().withPathToPhantomJsExe(path); return this; }

	/** @deprecated Use: <b><code>$.driver().get();</code> */
	public WebDriver getDefaultDriver() { return browser.driver().get(); }

	/** @deprecated Use: <b><code>$.driver().quit();</code> */
	public void quitDefaultDriver() { browser.driver().quit(); }

	/** @deprecated Use: <b><code>$.driver().quit();</code> */
	public void quitDefaultBrowser() { browser.driver().quit(); }

	/** @deprecated Use: <b><code>$.driver().quit();</code> */
	public void quit() { browser.driver().quit(); }
	
	/** @deprecated Use: <b><code>$.url(url);</code> */
	public void openUrl(String url) { browser.url(url); }

	/** @deprecated Use: <b><code>$.url(file);</code> */
	public void openUrl(File file) { browser.url(file); }

	/** @deprecated Use: <b><code>$.url(url);</code> */
	public void open(String url) { browser.url(url); }

	/** @deprecated Use: <b><code>$.url(file);</code> */
	public void open(File file) { browser.url(file); }

	/** @deprecated Use: <b><code>$.url(url);</code> */
	public void url(String url) { browser.url(url); }

	/** @deprecated Use: <b><code>$.url(file);</code> */
	public void url(File file) { browser.url(file); }

	/** @deprecated Use: <b><code>$.url();</code> */
	public String url() { return browser.url(); }
	
	/** @deprecated Use: <b><code>$.url();</code> */
	public String getCurrentUrl() { return browser.url(); }

	/** @deprecated Use: <b><code>$.pause(timeToPauseInMillis);</code> */
	public void sleep(int timeToWait, TimeUnit timeUnit) { browser.pause(MILLISECONDS.convert(timeToWait, timeUnit)); }

	/** @deprecated Use: <b><code>$.pause(timeToPauseInMillis);</code> */
	public void sleep(int millis) { browser.pause(millis); }

	/** @deprecated Use: <b><code>$.maximizeWindow();</code> */
	public void maximizeWindow() {
		browser.maximizeWindow();
	}

}