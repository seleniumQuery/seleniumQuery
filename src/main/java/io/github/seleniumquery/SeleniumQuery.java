/*
 * Copyright (c) 2015 seleniumQuery authors
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

import io.github.seleniumquery.browser.BrowserFunctionsWithDeprecatedFunctions;
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
 * @author ricardo-sc
 * @since 0.9.0
 */
public class SeleniumQuery {
	
	/**
	 * <p>The seleniumQuery global browser functions object.</p>
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
	public static final BrowserFunctionsWithDeprecatedFunctions $ = new BrowserFunctionsWithDeprecatedFunctions();

	/**
	 * <p>The seleniumQuery global browser functions object.</p> This works as an alias to <code>$</code>.
	 */
	public static final BrowserFunctionsWithDeprecatedFunctions sQ = $;
	
	/**
	 * <p>The seleniumQuery global browser functions object.</p> This works as an alias to <code>$</code>.
	 */
	public static final BrowserFunctionsWithDeprecatedFunctions jQuery = $;

	/**
	 * <p>The seleniumQuery global browser functions object.</p>
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
	public static SeleniumQueryObject $(String selector) {
		return InternalSeleniumQueryObjectFactory.instance().createWithValidSelectorAndNoPrevious($.driver().get(), selector);
	}

	/**
	 * <p>The seleniumQuery global browser functions object.</p>
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
	public static SeleniumQueryObject $(WebElement... elements) {
		return InternalSeleniumQueryObjectFactory.instance().createWithInvalidSelectorAndNoPrevious($.driver().get(), elements);
	}

	/**
	 * <p>The seleniumQuery global browser functions object.</p>
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
	public static SeleniumQueryObject $(List<WebElement> elements) {
		return InternalSeleniumQueryObjectFactory.instance().createWithInvalidSelectorAndNoPrevious($.driver().get(), elements);
	}

	/**
	 * <p>The seleniumQuery global browser functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param selector A selector. Can be a CSS3 selector, a jQuery/Sizzle/seleniumQuery extended selector or an
	 *                 XPath expression - if the argument starts with <code>(</code>, <code>/</code> or an XPath axis,
	 *                 such as <code>descendant-or-self::</code>.
	 * @return A {@link SeleniumQueryObject} containing all elements in matched by the selector.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject sQ(String selector) {
		return $(selector);
	}

	/**
	 * <p>The seleniumQuery global browser functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param elements One or more {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
	 * @return A {@link SeleniumQueryObject} containing the given element.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject sQ(WebElement... elements) {
		return $(elements);
	}

	/**
	 * <p>The seleniumQuery global browser functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param elements A list of {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
	 * @return A {@link SeleniumQueryObject} containing all given elements.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject sQ(List<WebElement> elements) {
		return $(elements);
	}

	/**
	 * <p>The seleniumQuery global browser functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param selector A selector. Can be a CSS3 selector, a jQuery/Sizzle/seleniumQuery extended selector or an
	 *                 XPath expression - if the argument starts with <code>(</code>, <code>/</code> or an XPath axis,
	 *                 such as <code>descendant-or-self::</code>.
	 * @return A {@link SeleniumQueryObject} containing all elements in matched by the selector.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject jQuery(String selector) {
		return $(selector);
	}

	/**
	 * <p>The seleniumQuery global browser functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param elements One or more {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
	 * @return A {@link SeleniumQueryObject} containing the given element.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject jQuery(WebElement... elements) {
		return $(elements);
	}

	/**
	 * <p>The seleniumQuery global browser functions object.</p> This works as an alias to <code>$</code>.
	 *
	 * @param elements A list of {@link WebElement}s to initialize a {@link SeleniumQueryObject} with.
	 * @return A {@link SeleniumQueryObject} containing all given elements.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryObject jQuery(List<WebElement> elements) {
		return $(elements);
	}
	
}
