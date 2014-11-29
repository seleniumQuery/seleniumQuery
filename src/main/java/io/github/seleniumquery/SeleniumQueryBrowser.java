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

    public final BrowserFunctions $ = new BrowserFunctions();

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
    public SeleniumQueryObject $(String selector) {
        return SQLocalFactory.createWithValidSelectorAndNoPrevious(this.$.driver().get(), selector);
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
    public SeleniumQueryObject $(WebElement element) {
        return SQLocalFactory.createWithInvalidSelectorAndNoPrevious(this.$.driver().get(), element);
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
    public SeleniumQueryObject $(List<WebElement> elements) {
        return SQLocalFactory.createWithInvalidSelectorAndNoPrevious(this.$.driver().get(), elements);
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
    public SeleniumQueryObject sQ(String selector) {
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
    public SeleniumQueryObject sQ(WebElement element) {
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
    public SeleniumQueryObject sQ(List<WebElement> elements) {
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
    public SeleniumQueryObject jQuery(String selector) {
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
    public SeleniumQueryObject jQuery(WebElement element) {
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
    public SeleniumQueryObject jQuery(List<WebElement> elements) {
        return $(elements);
    }
    
}