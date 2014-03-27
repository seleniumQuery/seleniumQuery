package org.openqa.selenium.seleniumquery;

import org.openqa.selenium.WebDriver;

/**
 * The seleniumQuery factory for objects.<br>
 * <br>
 * Recommended way of use is to import statically the function:<br><br>
 * <strong><code>import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;</code></strong>
 * <br><br>
 * And use it like:</br>
 * <strong><code>$("selector").function()</code></strong>
 *
 * @author acdcjunior
 * @since 0.2.0
 */
public class SeleniumQuery {
	
	/**
	 * <p>The seleniumQuery constructor. Works as <code>$</code> (actually, the dollar sign is an alias to this).</p>
	 * <p>Use <code>sQ("selector").function()</code> or <code>sQ.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>sQ("div").text();</code><br>
	 * <code>sQ.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.2.0
	 */
	public static final SeleniumQueryStatic sQ = new SeleniumQueryStatic();
	
	/**
	 * <p>The seleniumQuery constructor.</p>
	 * <p>Use <code>$("selector").function()</code> or <code>$.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>$("div").text();</code><br>
	 * <code>$.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.2.0
	 */
	public static final SeleniumQueryStatic $ = sQ;
	
	/**
	 * <p>The seleniumQuery constructor. Works as <code>$</code> (actually, the dollar sign is an alias to this).</p>
	 * <p>Use <code>sQ("selector").function()</code> or <code>sQ.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>sQ("div").text();</code><br>
	 * <code>sQ.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.2.0
	 */
	public static SeleniumQueryObject sQ(String selector) {
		return new SeleniumQueryObject(sQ.browser.getDefaultDriver(), selector);
	}
	
	/**
	 * <p>The seleniumQuery constructor. Works as <code>$</code> (actually, the dollar sign is an alias to this).</p>
	 * <p>Use <code>sQ("selector").function()</code> or <code>sQ.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>sQ("div").text();</code><br>
	 * <code>sQ.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.2.0
	 */
	public static SeleniumQueryObject sQ(WebDriver driver, String selector) {
		return new SeleniumQueryObject(driver, selector);
	}
	
	/**
	 * <p>The seleniumQuery constructor.</p>
	 * <p>Use <code>$("selector").function()</code> or <code>$.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>$("div").text();</code><br>
	 * <code>$.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.2.0
	 */
	public static SeleniumQueryObject $(String selector) {
		return sQ(selector);
	}
	
	/**
	 * <p>The seleniumQuery constructor.</p>
	 * <p>Use <code>$("selector").function()</code> or <code>$.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>$("div").text();</code><br>
	 * <code>$.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.2.0
	 */
	public static SeleniumQueryObject $(WebDriver driver, String selector) {
		return sQ(driver, selector);
	}
	
}