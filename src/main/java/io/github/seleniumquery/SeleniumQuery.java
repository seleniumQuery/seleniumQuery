package io.github.seleniumquery;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;

/**
 * The seleniumQuery factory for objects.<br>
 * <br>
 * Recommended way of use is to import statically the function:<br><br>
 * <strong><code>import static io.github.seleniumquery.SeleniumQuery.$;</code></strong>
 * <br>
 * <p>
 * And use it like:</br>
 * <strong><code>$("selector").function()</code></strong>
 * </p>
 * <p>
 * Other uses (aliases) include <code>jQuery()</code> and <code>sQ()</code>:<br>
 * <strong><code>jQuery("selector").function()</code></strong><br>
 * <strong><code>sQ("selector").function()</code></strong><br>
 * </p>
 *
 * @author acdcjunior
 * @since 0.2.0
 */
public class SeleniumQuery {
	
	/**
	 * <p>The seleniumQuery global object. Works as <code>$</code> (actually, the dollar sign is an alias to this).</p>
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
	 * <p>The seleniumQuery global object.</p>
	 * <p>
	 * Use <code>$("selector").function()</code> or <code>$.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>$("div").text();</code><br>
	 * <code>$.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.2.0
	 */
	public static final SeleniumQueryStatic $ = sQ;
	
	/**
	 * <p>The seleniumQuery global object.</p> This works as an alias to <code>$</code>.
	 * 
	 * @since 0.3.0
	 */
	public static final SeleniumQueryStatic jQuery = sQ;
	
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
	 * <p>The seleniumQuery constructor. Works as <code>$</code> (actually, the dollar sign is an alias to this).</p>
	 * <p>Use <code>sQ("selector").function()</code> or <code>sQ.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>sQ("div").text();</code><br>
	 * <code>sQ.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.3.0
	 */
	public static SeleniumQueryObject sQ(WebElement element) {
		return sQ(((WrapsDriver) element).getWrappedDriver(), element);
	}
	
	/**
	 * <p>The seleniumQuery constructor. Works as <code>$</code> (actually, the dollar sign is an alias to this).</p>
	 * <p>Use <code>sQ("selector").function()</code> or <code>sQ.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>sQ("div").text();</code><br>
	 * <code>sQ.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.3.0
	 */
	public static SeleniumQueryObject sQ(WebDriver driver, WebElement element) {
		return SQLocalFactory.getInstance().createWithInvalidSelectorAndNoPrevious(driver, element);
	}
	
	/**
	 * <p>The seleniumQuery constructor. Works as <code>$</code> (actually, the dollar sign is an alias to this).</p>
	 * <p>Use <code>sQ("selector").function()</code> or <code>sQ.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>sQ("div").text();</code><br>
	 * <code>sQ.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.3.0
	 */
	public static SeleniumQueryObject sQ(SeleniumQueryObject seleniumQueryObject) {
		return seleniumQueryObject;
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
	
	/**
	 * <p>The seleniumQuery object constructor.</p>
	 * <p>Use <code>$("selector").function()</code> or <code>$.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>$("div").text();</code><br>
	 * <code>$.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.3.0
	 */
	public static SeleniumQueryObject $(WebElement element) {
		return sQ(element);
	}
	
	/**
	 * @since 0.5.0
	 */
	public static SeleniumQueryObject $(WebDriver driver, WebElement element) {
		return sQ(driver, element);
	}
	
	/**
	 * <p>The seleniumQuery object constructor.</p>
	 * <p>Use <code>$("selector").function()</code> or <code>$.property.function()</code></p>
	 * <br>
	 * Example:<br>
	 * <code>$("div").text();</code><br>
	 * <code>$.location.href("http://www.google.com");</code><br>
	 * 
	 * @since 0.3.0
	 */
	public static SeleniumQueryObject $(SeleniumQueryObject seleniumQueryObject) {
		return sQ(seleniumQueryObject);
	}
	
	/**
	 * <p>The seleniumQuery constructor.</p> This function is an alias to <code>$()</code>.
	 * 
	 * @since 0.3.0
	 */
	public static SeleniumQueryObject jQuery(String selector) {
		return sQ(selector);
	}
	
	/**
	 * <p>The seleniumQuery constructor.</p> This function is an alias to <code>$()</code>.
	 * 
	 * @since 0.3.0
	 */
	public static SeleniumQueryObject jQuery(WebDriver driver, String selector) {
		return sQ(driver, selector);
	}
	
	/**
	 * <p>The seleniumQuery constructor.</p> This function is an alias to <code>$()</code>.
	 * 
	 * @since 0.3.0
	 */
	public static SeleniumQueryObject jQuery(WebElement element) {
		return sQ(element);
	}
	
	/**
	 * <p>The seleniumQuery constructor.</p> This function is an alias to <code>$()</code>.
	 * 
	 * @since 0.3.0
	 */
	public static SeleniumQueryObject jQuery(SeleniumQueryObject seleniumQueryObject) {
		return sQ(seleniumQueryObject);
	}
	
}