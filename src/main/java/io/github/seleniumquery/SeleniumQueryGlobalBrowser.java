package io.github.seleniumquery;

/**
 * Represents the seleniumQuery global browser instance.
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class SeleniumQueryGlobalBrowser extends SeleniumQueryBrowserFunctions {
	
	/**
	 * Series of functions that set the default browser behavior.<br>
	 *
	 * This field has been deprecated.<br>
	 *     You'll find the functions for this object in <b><code>$.function();</code</b> or <b><code>$.driver().function();</code></b>.
	 *
	 * @since 0.9.0
	 */
	@Deprecated public final SeleniumQueryDeprecatedBrowser browser = new SeleniumQueryDeprecatedBrowser(this);
	
}