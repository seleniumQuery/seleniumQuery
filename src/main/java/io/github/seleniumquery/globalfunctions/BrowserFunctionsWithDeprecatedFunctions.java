package io.github.seleniumquery.globalfunctions;

import io.github.seleniumquery.globalfunctions.driver.SeleniumQueryDriver;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Represents the seleniumQuery global browser instance.
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
@SuppressWarnings({"deprecation", "unused"})
public class BrowserFunctionsWithDeprecatedFunctions extends BrowserFunctions {
	
	/**
	 * Series of functions that set the default browser behavior.<br>
	 *
	 * This field has been deprecated.<br>
	 *     You'll find the functions for this object in <b><code>$.function();</code</b> or <b><code>$.driver().function();</code></b>.
	 *
	 * @since 0.9.0
	 */
	@Deprecated public final OldBrowserFunctions browser = new OldBrowserFunctions(this);

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
	public static final class OldBrowserFunctions {

		private BrowserFunctions browser;
		public OldBrowserFunctions(BrowserFunctions browser) { this.browser = browser; }

		/** @deprecated Use: <b><code>$.driver();</code> */
		public SeleniumQueryDriver globalDriver() { return browser.driver(); }

		/** @deprecated Use: <b><code>$.driver().use(webDriverInstance);</code> */
		public OldBrowserFunctions setDefaultDriver(WebDriver defaultDriver) { browser.driver().use(defaultDriver); return this; }

		/** @deprecated Use: <b><code>$.driver().useHtmlUnit();</code> */
		public OldBrowserFunctions setDefaultDriverAsHtmlUnit() { browser.driver().useHtmlUnit(); return this; }

		/** @deprecated Use: <b><code>$.driver().useFirefox();</code> */
		public OldBrowserFunctions setDefaultDriverAsFirefox() { browser.driver().useFirefox(); return this; }

		/** @deprecated Use: <b><code>$.driver().useChrome();</code> */
		public OldBrowserFunctions setDefaultDriverAsChrome() { browser.driver().useChrome(); return this; }

		/** @deprecated Use: <b><code>$.driver().useChrome().withPathToChromeDriverExe(path);</code> */
		public OldBrowserFunctions setDefaultDriverAsChrome(String path) { browser.driver().useChrome().withPathToChromeDriverExe(path); return this; }

		/** @deprecated Use: <b><code>$.driver().useInternetExplorer();</code> */
		public OldBrowserFunctions setDefaultDriverAsIE() { browser.driver().useInternetExplorer(); return this; }

		/** @deprecated Use: <b><code>$.driver().useInternetExplorer().withPathToIEDriverServerExe(path);</code> */
		public OldBrowserFunctions setDefaultDriverAsIE(String path) { browser.driver().useInternetExplorer().withPathToIEDriverServerExe(path); return this; }

		/** @deprecated Use: <b><code>$.driver().usePhantomJS();</code> */
		public OldBrowserFunctions setDefaultDriverAsPhantomJS() { browser.driver().usePhantomJS(); return this; }

		/** @deprecated Use: <b><code>$.driver().usePhantomJS().withPathToPhantomJsExe(path);</code> */
		public OldBrowserFunctions setDefaultDriverAsPhantomJS(String path) { browser.driver().usePhantomJS().withPathToPhantomJsExe(path); return this; }

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
	
}