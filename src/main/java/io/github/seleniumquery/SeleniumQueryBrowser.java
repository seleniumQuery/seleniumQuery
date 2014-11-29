package io.github.seleniumquery;

import io.github.seleniumquery.globalfunctions.BrowserFunctions;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * THe seleniumQuery Browser, consisting of seleniumQuery decoration over a specific instance of WebDriver.
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
 */
public class SeleniumQueryBrowser {

    /**
     * <p>The seleniumQuery browser functions object.</p>
     * <p>
     *     Use <code>$.function()</code> for browser-scoped actions and
     *     <code>$("selector").function()</code> for tasks that should operate on groups of elements.
     * </p>
     * <br>
     * Examples:
     * <pre>
     * $.driver().useFirefox();
     * $.url("http://www.google.com");
     * $("div").text();
     * </pre>
     *
     * @since 0.9.0
     */
    public final BrowserFunctions $ = new BrowserFunctions();

    /**
     * <p>The seleniumQuery browser functions object.</p> This works as an alias to <code>$</code>.
     */
    public final BrowserFunctions sQ = $;

    /**
     * <p>The seleniumQuery browser functions object.</p> This works as an alias to <code>$</code>.
     */
    public final BrowserFunctions jQuery = $;

    /**
     * <p>The seleniumQuery browser functions object.</p>
     * <p>
     *     Use <code>$.function()</code> for browser-scoped actions and
     *     <code>$("selector").function()</code> for tasks that should operate on groups of elements.
     * </p>
     * <br>
     * Examples:
     * <pre>
     * $.driver().useFirefox();
     * $.url("http://www.google.com");
     * $("div").text();
     * </pre>
     *
     * @param selector A selector. Can be a CSS3 selector, a jQuery/Sizzle/seleniumQuery extended selector or an
     *                 XPath expression - if the argument starts with <code>(</code>, <code>/</code> or an XPath axis,
     *                 such as <code>descendant-or-self::</code>.
     * @return A {@link SeleniumQueryObject} containing all elements in matched by the selector.
     *
     * @since 0.9.0
     */
    public SeleniumQueryObject $(String selector) {
        return ObjectLocalFactory.createWithValidSelectorAndNoPrevious(this.$.driver().get(), selector);
    }

    /**
     * <p>The seleniumQuery browser functions object.</p>
     * <p>
     *     Use <code>$.function()</code> for browser-scoped actions and
     *     <code>$("selector").function()</code> for tasks that should operate on groups of elements.
     * </p>
     * <br>
     * Examples:
     * <pre>
     * $.driver().useFirefox();
     * $.url("http://www.google.com");
     * $("div").text();
     * </pre>
     *
     * @param elements One or more {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
     * @return A {@link SeleniumQueryObject} containing the given element.
     *
     * @since 0.9.0
     */
    public SeleniumQueryObject $(WebElement... elements) {
        return ObjectLocalFactory.createWithInvalidSelectorAndNoPrevious(this.$.driver().get(), elements);
    }

    /**
     * <p>The seleniumQuery browser functions object.</p>
     * <p>
     *     Use <code>$.function()</code> for browser-scoped actions and
     *     <code>$("selector").function()</code> for tasks that should operate on groups of elements.
     * </p>
     * <br>
     * Examples:
     * <pre>
     * $.driver().useFirefox();
     * $.url("http://www.google.com");
     * $("div").text();
     * </pre>
     *
     * @param elements A list of {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
     * @return A {@link SeleniumQueryObject} containing all given elements.
     *
     * @since 0.9.0
     */
    public SeleniumQueryObject $(List<WebElement> elements) {
        return ObjectLocalFactory.createWithInvalidSelectorAndNoPrevious(this.$.driver().get(), elements);
    }

    /**
     * <p>The seleniumQuery browser functions object.</p> This works as an alias to <code>$</code>.
     *
     * @param selector A selector. Can be a CSS3 selector, a jQuery/Sizzle/seleniumQuery extended selector or an
     *                 XPath expression - if the argument starts with <code>(</code>, <code>/</code> or an XPath axis,
     *                 such as <code>descendant-or-self::</code>.
     * @return A {@link SeleniumQueryObject} containing all elements in matched by the selector.
     *
     * @since 0.9.0
     */
    public SeleniumQueryObject sQ(String selector) {
        return $(selector);
    }

    /**
     * <p>The seleniumQuery browser functions object.</p> This works as an alias to <code>$</code>.
     *
     * @param elements One or more {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
     * @return A {@link SeleniumQueryObject} containing the given element.
     *
     * @since 0.9.0
     */
    public SeleniumQueryObject sQ(WebElement... elements) {
        return $(elements);
    }

    /**
     * <p>The seleniumQuery browser functions object.</p> This works as an alias to <code>$</code>.
     *
     * @param elements A list of {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
     * @return A {@link SeleniumQueryObject} containing all given elements.
     *
     * @since 0.9.0
     */
    public SeleniumQueryObject sQ(List<WebElement> elements) {
        return $(elements);
    }

    /**
     * <p>The seleniumQuery browser functions object.</p> This works as an alias to <code>$</code>.
     *
     * @param selector A selector. Can be a CSS3 selector, a jQuery/Sizzle/seleniumQuery extended selector or an
     *                 XPath expression - if the argument starts with <code>(</code>, <code>/</code> or an XPath axis,
     *                 such as <code>descendant-or-self::</code>.
     * @return A {@link SeleniumQueryObject} containing all elements in matched by the selector.
     *
     * @since 0.9.0
     */
    public SeleniumQueryObject jQuery(String selector) {
        return $(selector);
    }

    /**
     * <p>The seleniumQuery browser functions object.</p> This works as an alias to <code>$</code>.
     *
     * @param elements One or more {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
     * @return A {@link SeleniumQueryObject} containing the given element.
     *
     * @since 0.9.0
     */
    public SeleniumQueryObject jQuery(WebElement... elements) {
        return $(elements);
    }

    /**
     * <p>The seleniumQuery browser functions object.</p> This works as an alias to <code>$</code>.
     *
     * @param elements A list of {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
     * @return A {@link SeleniumQueryObject} containing all given elements.
     *
     * @since 0.9.0
     */
    public SeleniumQueryObject jQuery(List<WebElement> elements) {
        return $(elements);
    }
    
}