package io.github.seleniumquery;

import io.github.seleniumquery.globalfunctions.SeleniumQueryDefaultBrowser;

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
	public final SeleniumQueryDefaultBrowser browser;
	
	public SeleniumQueryStatic() {
		this.browser = new SeleniumQueryDefaultBrowser();
	}

}