package org.openqa.selenium.seleniumquery;

import org.openqa.selenium.seleniumquery.globalfunctions.SeleniumQueryBrowser;
import org.openqa.selenium.seleniumquery.globalfunctions.SeleniumQueryLocation;
import org.openqa.selenium.seleniumquery.globalfunctions.SeleniumQueryGlobalWait;

/**
 * Represents the seleniumQuery global object.
 * 
 * @author acdcjunior
 * @since 0.2.0
 */
public class SeleniumQueryStatic {
	
	/**
	 * Series of functions that set the default browser behavior.<br>
	 * <br><br>
	 * The default browser is emplyed when a seleniumQuery object is build using <code>$(".selector");</code>.<br>
	 * A different browser can be used by using <code>$(anotherDriver, ".selector");</code>
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	public final SeleniumQueryBrowser browser;
	
	/**
	 * Series of functions that get or change the current URL/page displayed by the browser.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	public final SeleniumQueryLocation location;

	/**
	 * Series of functions that instruct the default browser of current thread to wait for a given condition
	 * to be satisfied.
	 * 
	 * @author acdcjunior
	 * @since 0.2.0
	 */
	public final SeleniumQueryGlobalWait waitUntil;
	
	public SeleniumQueryStatic() {
		this.browser = new SeleniumQueryBrowser();
		this.location = new SeleniumQueryLocation(this.browser);
		this.waitUntil = new SeleniumQueryGlobalWait(this.browser);
	}

}