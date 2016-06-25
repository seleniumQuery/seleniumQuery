/*
 * Copyright (c) 2016 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery;

import io.github.seleniumquery.browser.BrowserFunctions;
import io.github.seleniumquery.internal.SqObjectFactory;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * THe seleniumQuery Browser, consisting of seleniumQuery decoration-ish over a specific instance of WebDriver.
 *
 * @author acdcjunior
 * @author ricardo-sc
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
    public static final BrowserFunctions $ = new BrowserFunctions();

    /**
     * <p>The seleniumQuery browser functions object.</p> This works as an alias to <code>$</code>.
     */
    public static final BrowserFunctions sQ = $;

    /**
     * <p>The seleniumQuery browser functions object.</p> This works as an alias to <code>$</code>.
     */
    public static final BrowserFunctions jQuery = $;

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
        return getSeleniumQueryObjectFactory().createWithValidSelectorAndNoPrevious(this.$.driver().get(), selector);
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
        return getSeleniumQueryObjectFactory().createWithInvalidSelectorAndNoPrevious(this.$.driver().get(), elements);
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
        return getSeleniumQueryObjectFactory().createWithInvalidSelectorAndNoPrevious(this.$.driver().get(), elements);
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

    private SqObjectFactory getSeleniumQueryObjectFactory() {
        return SqObjectFactory.instance();
    }

}
