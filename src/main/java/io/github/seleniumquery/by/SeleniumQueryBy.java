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

package io.github.seleniumquery.by;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.by.firstgen.FirstGenEnhancedElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Pattern;

/**
 * This By is a combination of the By.xpath and By.css, where the CSS3, XPath, jQuery/Sizzle and others
 * selectors are supported.
 * 
 * @author acdcjunior
 * @since 0.9.0
 */
public class SeleniumQueryBy extends By {

    private static final EnhancedElementFinder ELEMENT_FINDER = new FirstGenEnhancedElementFinder();
	
	/**
	 * A {@link By} to be used in an element created with no By. Attempting to filter elements through this
	 * will throw an exception.
	 * @since 0.9.0
	 */
	public static final SeleniumQueryBy NO_SELECTOR_INVALID_BY = new SeleniumQueryBy("NO_SELECTOR_INVALID_BY") {
		@Override public List<WebElement> findElements(SearchContext context) {
			throw new SeleniumQueryException("This object was instantiated without a selector, you cannot search " +
					"elements based on it as the string used to match it is unavailable.\n" +
					"Try not using more than one .waitUntil() in a single line.");
		}
	};

	private static final String STARTING_BRACES = "(\\s*\\(\\s*)*";
	private static final String XPATH_AXES = "ancestor|ancestor-or-self|attribute|child|descendant|descendant-or-self|following|following-sibling|parent|preceding|preceding-sibling|self";
	private static final Pattern XPATH_EXPRESSION_PATTERN = Pattern.compile(STARTING_BRACES + "(/|(" + XPATH_AXES + ")::).*");

	/**
	 * Enhanced selector is not just the CSS selector, it also supports XPath expressions and some
	 * Sizzle enhancements.
	 *
	 * @return a By which locates elements via seleniumQuery's enhanced selector.
	 *
	 * @since 0.9.0
	 */
	public static SeleniumQueryBy byEnhancedSelector(String selector) {
		return new SeleniumQueryBy(selector);
	}

	private final String selector;

	
	/**
	 * Constructs a SeleniumQueryBy for the given selector.
	 * @param selector the selector you need the elements to match.
	 */
	public SeleniumQueryBy(String selector) {
        if (selector == null) {
            throw new IllegalArgumentException("Selector string cannot be null.");
        }
		this.selector = selector;
	}
	
	
	/**
	 * Returns the list of elements that match this By in the given context.
	 * @return the list of elements that match this By in the given context.
	 * @since 0.9.0
	 */
	@Override
	public List<WebElement> findElements(SearchContext context) {
		if (this.isXPathExpression()) {
			return new By.ByXPath(this.selector).findElements(context);
		}
		return ELEMENT_FINDER.findElements(context, this.selector);
	}

	/**
	 * Returns the string representation of this By.
	 * @return the string representation of this By.
	 * @since 0.9.0
	 */
	@Override
	public String toString() {
		return "$(\"" + selector + "\")";
	}

	/**
	 * If it begins with "/" or "(/" or "(((((/" or an XPath Axis, we assume the selector given is a XPath expression.
	 */
	public boolean isXPathExpression() {
		return XPATH_EXPRESSION_PATTERN.matcher(this.selector).matches();
	}

	/**
	 * Returns the selector used in the creation of this By.
	 * @return the selector used in the creation of this By.
	 * @since 0.9.0
	 */
	public String getSelector() {
		return this.selector;
	}
	
}