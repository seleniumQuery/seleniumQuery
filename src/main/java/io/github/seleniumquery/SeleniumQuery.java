package io.github.seleniumquery;

import io.github.seleniumquery.globalfunctions.BrowserFunctionsWithDeprecatedFunctions;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * The seleniumQuery objects factory.<br>
 * <br>
 * Recommended way of use is to import statically the function:
 * <pre>
 * import static io.github.seleniumquery.SeleniumQuery.$;
 * </pre>
 * And use it like:
 * <pre>
 * $.url("http://example.com");
 * $("selector").function();
 * </pre>
 * The default browser/driver is employed when a seleniumQuery object is built using <code>$(".selector");</code>.<br>
 * A different browser can be used by using the {@link io.github.seleniumquery.SeleniumQueryBrowser} class:
 * <pre>
 * SeleniumQueryBrowser <b>chrome</b> = new SeleniumQueryBrowser();
 * <b>chrome</b>.driver().useChrome();
 * <b>chrome</b>.$(".selector").val("123");
 * </pre>
 * Other uses (aliases) include <code>jQuery()</code> and <code>sQ()</code>:
 * <pre>
 * jQuery("selector").function();
 * sQ("selector").function();
 * </pre>
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class SeleniumQuery {
	
	/**
	 * <p>The seleniumQuery global browser and functions object.</p>
	 * <p>
	 *     Use <code>$.function()</code> for browser-scoped actions and
	 *     <code>$("selector").function()</code> for tasks that should work on groups of elements.
	 * </p>
	 * <br>
	 * Examples:<br>
	 * <code>$.driver().useFirefox();</code><br>
	 * <code>$.url("http://www.google.com");</code><br>
	 * <code>$("div").text();</code>
	 */
	public static final BrowserFunctionsWithDeprecatedFunctions $ = new BrowserFunctionsWithDeprecatedFunctions();

	/**
	 * <p>The seleniumQuery global browser and functions object.</p> This works as an alias to <code>$</code>.
	 */
	public static final BrowserFunctionsWithDeprecatedFunctions sQ = $;
	
	/**
	 * <p>The seleniumQuery global browser and functions object.</p> This works as an alias to <code>$</code>.
	 */
	public static final BrowserFunctionsWithDeprecatedFunctions jQuery = $;

	/**
	 * <p>The seleniumQuery global browser and functions object.</p>
	 * <p>
	 *     Use <code>$.function()</code> for browser-scoped actions and
	 *     <code>$("selector").function()</code> for tasks that should work on groups of elements.
	 * </p>
	 * <br>
	 * Examples:<br>
	 * <code>$.driver().useFirefox();</code><br>
	 * <code>$.url("http://www.google.com");</code><br>
	 * <code>$("div").text();</code>
	 *
	 * @param selector A selector. Can be a CSS3 selector, a jQuery/Sizzle/seleniumQuery extended selector or an
	 *                 XPath expression - if the argument starts with <code>(</code>, <code>/</code> or an XPath axis,
	 *                 such as <code>descendant-or-self::</code>.
	 * @return a {@link SeleniumQueryObject} containing all elements in matched by the selector.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject $(String selector) {
		return SQLocalFactory.createWithValidSelectorAndNoPrevious($.driver().get(), selector);
	}

	/**
	 * <p>The seleniumQuery global browser and functions object.</p>
	 * <p>
	 *     Use <code>$.function()</code> for browser-scoped actions and
	 *     <code>$("selector").function()</code> for tasks that should work on groups of elements.
	 * </p>
	 * <br>
	 * Examples:<br>
	 * <code>$.driver().useFirefox();</code><br>
	 * <code>$.url("http://www.google.com");</code><br>
	 * <code>$("div").text();</code>
	 *
	 * @param element A {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
	 * @return a {@link SeleniumQueryObject} containing the given element.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject $(WebElement element) {
		return SQLocalFactory.createWithInvalidSelectorAndNoPrevious($.driver().get(), element);
	}

	/**
	 * <p>The seleniumQuery global browser and functions object.</p>
	 * <p>
	 *     Use <code>$.function()</code> for browser-scoped actions and
	 *     <code>$("selector").function()</code> for tasks that should work on groups of elements.
	 * </p>
	 * <br>
	 * Examples:<br>
	 * <code>$.driver().useFirefox();</code><br>
	 * <code>$.url("http://www.google.com");</code><br>
	 * <code>$("div").text();</code>
	 *
	 * @param elements A list of {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
	 * @return a {@link SeleniumQueryObject} containing all given elements.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject $(List<WebElement> elements) {
		return SQLocalFactory.createWithInvalidSelectorAndNoPrevious($.driver().get(), elements);
	}

	/**
	 * <p>The seleniumQuery global browser and functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param selector A selector. Can be a CSS3 selector, a jQuery/Sizzle/seleniumQuery extended selector or an
	 *                 XPath expression - if the argument starts with <code>(</code>, <code>/</code> or an XPath axis,
	 *                 such as <code>descendant-or-self::</code>.
	 * @return a {@link SeleniumQueryObject} containing all elements in matched by the selector.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject sQ(String selector) {
		return $(selector);
	}

	/**
	 * <p>The seleniumQuery global browser and functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param element A {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
	 * @return a {@link SeleniumQueryObject} containing the given element.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject sQ(WebElement element) {
		return $(element);
	}

	/**
	 * <p>The seleniumQuery global browser and functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param elements A list of {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
	 * @return a {@link SeleniumQueryObject} containing all given elements.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject sQ(List<WebElement> elements) {
		return $(elements);
	}

	/**
	 * <p>The seleniumQuery global browser and functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param selector A selector. Can be a CSS3 selector, a jQuery/Sizzle/seleniumQuery extended selector or an
	 *                 XPath expression - if the argument starts with <code>(</code>, <code>/</code> or an XPath axis,
	 *                 such as <code>descendant-or-self::</code>.
	 * @return a {@link SeleniumQueryObject} containing all elements in matched by the selector.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject jQuery(String selector) {
		return $(selector);
	}

	/**
	 * <p>The seleniumQuery global browser and functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param element A {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
	 * @return a {@link SeleniumQueryObject} containing the given element.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject jQuery(WebElement element) {
		return $(element);
	}

	/**
	 * <p>The seleniumQuery global browser and functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param elements A list of {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
	 * @return a {@link SeleniumQueryObject} containing all given elements.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject jQuery(List<WebElement> elements) {
		return $(elements);
	}
	
}