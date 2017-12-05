/*
 * Copyright (c) 2017 seleniumQuery authors
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

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Predicate;
import io.github.seleniumquery.functions.SeleniumQueryFunctions;
import io.github.seleniumquery.functions.as.SeleniumQueryPlugin;
import io.github.seleniumquery.functions.as.StandardPlugins;

/**
 * Represents the <strong>SeleniumQuery Object</strong>: a list of {@link WebElement}s with special methods.
 * <p>
 * A seleniumQuery object (similar to the regular JavaScript jQuery object) contains a collection of
 * Selenium {@link WebElement}s (JavaScript jQuery is a collection of DOM elements) that have been <b>selected
 * from a document</b>. Since seleniumQuery methods often use CSS selectors to match elements from a document,
 * the set of elements in a seleniumQuery object is often called a set of <i>"matched elements"</i> or <i>"selected elements"</i>.
 * </p>
 * <p>
 * The seleniumQuery object itself behaves much like a {@link List}; it has a {@link SeleniumQueryObject#size()} method, the
 * elements in the list can be accessed by their numeric indices through {@link SeleniumQueryObject#get()} and can also
 * be iterated through.
 * </p>
 * <p>
 * Just like jQuery, many seleniumQuery methods return the seleniumQuery object itself, so that method calls can be chained.
 * </p>
 * <p>
 * In API calls that return a seleniumQuery object, the value returned will be the original seleniumQuery object unless
 * otherwise documented by that API. API methods such as {@link SeleniumQueryObject#first()} or {@link SeleniumQueryObject#not(String)}
 * modify their incoming set and thus return a <b>new</b> seleniumQuery object.
 * </p>
 * <p>
 * Whenever you use a "destructive" seleniumQuery method that potentially changes the set of elements in the seleniumQuery object,
 * such as {@link SeleniumQueryObject#first()} or {@link SeleniumQueryObject#not(String)}, that method actually returns a new seleniumQuery
 * object with the resulting elements. To return to the previous seleniumQuery object, you use the {@link SeleniumQueryObject#end()} method.
 * </p>
 * <p>
 * A seleniumQuery object may be empty, containing no elements. It is not an error; any further methods called on that
 * seleniumQuery object simply have no effect since they have no elements to act upon.
 * </p>
 *
 * @author acdcjunior
 * @author ricardo-sc
 * @since 0.9.0
 */
public interface SeleniumQueryObject extends Iterable<WebElement> {

	/**
	 * <p>List of functions that will halt the execution and requery the selector until the specified condition is met, returning
	 * a new seleniumQuery object at the end.</p>
	 * <p>
	 * This method will wait as specified in the properties file. <b>By default, it waits up to 10 seconds, polling (requerying) every 900 ms.</b>
	 * </p>
	 *
	 * If a different amout of time is needed, either create and/or modify the <code>seleniumQuery.properties</code> file or use this method's
	 * version with a time parameter.
	 *
	 * @return a {@link SeleniumQueryFluentFunction} object for specifying the wait conditions.
	 * @since 0.9.0
	 */
	SeleniumQueryFluentFunction waitUntil();

    /**
     * <p>Asserts that some property or attribute of the elements in this collection
     * match some criteria.</p>
     *
     * @return a {@link SeleniumQueryFluentFunction} object for specifying the assert conditions.
     * @since 0.18.0
     */
    SeleniumQueryFluentFunction assertThat();

	/**
	 * List of functions that will halt the execution and requery the selector until the specified condition is met, returning
	 * a new seleniumQuery object at the end.
	 *
	 * @param waitUntilTimeout Time in milliseconds to wait before a timeout is thrown.
	 * @return a {@link SeleniumQueryFluentFunction} object for specifying the wait conditions.
	 *
	 * @since 0.9.0
	 */
    SeleniumQueryFluentFunction waitUntil(long waitUntilTimeout);

	/**
	 * List of functions that will halt the execution and requery the selector until the specified condition is met, returning
	 * a new seleniumQuery object at the end.
	 *
	 * @param waitUntilTimeout Time in milliseconds to wait for the condition.
	 * @param waitUntilPollingInterval Interval in milliseconds between every requery/check.
	 * @return a {@link SeleniumQueryFluentFunction} object for specifying the wait conditions.
	 *
	 * @since 0.9.0
	 */
    SeleniumQueryFluentFunction waitUntil(long waitUntilTimeout, long waitUntilPollingInterval);

	/**
	 * Enables several functions (plugins) that allow executing specific tasks, from specific points of view, on
	 * the elements of this {@link io.github.seleniumquery.SeleniumQueryObject}.
	 *
	 * @return A list of functions showing the available tasks that can be performed.
	 *
	 * @since 0.9.0
	 */
	StandardPlugins as();

	/**
	 * Enables the execution of functions defined by the plugin sent as argument.
	 *
	 * @param pluginFunction The plugin instance this object will be handed to.
     * @param <PLUGIN> The plugin class.
	 * @return An object provided by the plugin.
	 *
	 * @since 0.9.0
	 */
	<PLUGIN> PLUGIN as(SeleniumQueryPlugin<PLUGIN> pluginFunction);

	/**
	 * Returns an iterator over the matched elements of this seleniumQuery object.
	 *
	 * @return an iterator over the matched elements of this seleniumQuery object.
	 */
	@Override
	Iterator<WebElement> iterator();

	/**
	 * Returns the driver used to create this seleniumQuery object.
	 * @return the driver used to create this seleniumQuery object.
	 */
	WebDriver getWebDriver();

	/**
	 * Returns the By used to search the matched elements of this seleniumQuery object.
	 * @return the By used to search the matched elements of this seleniumQuery object.
	 */
	By getBy();

	/**
     * Returns the seleniumQueryFunctions service used by this object.
     * @return the seleniumQueryFunctions service used by this object.
     */
	SeleniumQueryFunctions getSeleniumQueryFunctions();

	/**
	 * Returns the number of elements in the seleniumQuery object.
	 *
	 * @return the number of elements in the seleniumQuery object.
	 *
	 * @since 0.9.0
	 */
	int size();

	/**
	 * Removes elements from the set of matched elements.
	 *
	 * @param selector	A string containing a selector expression to match elements against.
	 * @return A seleniumQuery object containing all elements not removed.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject not(String selector);

	/**
	 * <p>Reduces the set of matched elements to the first in the set.</p>
	 *
	 * <p>Given a seleniumQuery object that represents a set of DOM elements, the <code>.first()</code>
	 * method constructs a new seleniumQuery object from the first element in that set.</p>
	 *
	 * @return A seleniumQuery object containing a single matched element.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject first();

	/**
	 * <p>Reduces the set of matched elements to the final one in the set.</p>
	 *
	 * <p>Given a seleniumQuery object that represents a set of DOM elements, the <code>.last()</code>
	 * method constructs a new seleniumQuery object from the last element in that set.</p>
	 *
	 * @return A seleniumQuery object containing a single matched element.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject last();

	/**
	 * Reduce the set of matched elements to the one at the specified index.
	 *
	 * @param index If <i>positive</i>: An integer indicating the 0-based position of the element.
	 * 				If <i>negative</i>: An integer indicating the position of the element, counting backwards
	 * 			from the last element in the set.
	 * @return A seleniumQuery object containing a single matched element.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject eq(int index);

	/**
	 * <p>Gets the combined text contents of each element in the set of matched elements, including
	 * their descendants.</p>
	 *
	 * <p><b>Note:</b> This functions uses Selenium's <code>{@link WebElement#getText()}</code>, and, as
	 * jQuery, <i>"due to variations in the HTML parsers in different browsers, the text returned may vary
	 * in newlines and other white space."</i></p>
     *
     * <p><b>IMPORTANT:</b>
     * It is impossible to read text from hidden elements in Selenium:<br>
     * Since a user cannot read text in a hidden element, WebDriver will not allow access to
     * it as well.<br>
     * More in <a href="https://code.google.com/p/selenium/wiki/FrequentlyAskedQuestions#Q:_Why_is_it_not_possible_to_interact_with_hidden_elements?">WebDriver FAQs</a>.
     * </p>
	 *
	 * @return the text from all elements in the matched set.
	 *
	 * @since 0.9.0
	 */
	String text();

	/**
	 * Clicks <strong>all</strong> elements in the set of matched elements, in the
	 * order they were matched.
     * <br><br>
     * If any element throws exceptions while being clicked, the others will still be clicked.
	 *
	 * @return The same seleniumQuery object.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject click();

	/**
	 * Clicks <strong>all</strong> elements in the set of matched elements, in the
	 * order they were matched, but following some steps first:
     * - Wait for the element to become ":visible"
     * - Scroll the screen until the element
     * - Finally click it
     *
     * Those steps avoid some errors using ChromeHeadless and simplify some tests.
     * <br><br>
     * If any element throws exceptions while being clicked, the others will still be clicked.
	 *
	 * @return The same seleniumQuery object.
	 *
	 * @since 0.17.0
	 */
	SeleniumQueryObject waitViewClick();

    /**
     * Double clicks <strong>all</strong> elements in the set of matched elements, in the
     * order they were matched.
     * <br><br>
     * If any element throws exceptions while being clicked, the others will still be clicked.
     *
     * @return The same seleniumQuery object.
     * @since 0.15.0
     */
    SeleniumQueryObject dblclick();

	/**
	 * Sets the value of <strong>all</strong> elements in the set of matched elements.
	 *
	 * @param value The (string) value to be set.
	 * @return The same seleniumQuery object.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject val(String value);

    /**
	 * Sets the value of <strong>all</strong> elements in the set of matched elements.
	 *
	 * @param value The (number) value to be set.
	 * @return The same seleniumQuery object.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject val(Number value);

    /**
	 * Gets the current value of the first element in the set of matched elements.
	 *
	 * @return The value of the first element.
	 *
	 * @since 0.9.0
	 */
	String val();

    /**
	 * Ends the most recent filtering operation in the current chain and returns the set of matched
	 * elements to its previous state.
	 *
	 * @return the seleniumQuery object that originated the current instance.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject end();

	/**
	 * Gets the descendants of each element in the current set of matched elements, filtered by a selector.
	 *
	 * @param selector Selector the descendants must match.
	 * @return a {@link SeleniumQueryObject} containing the (filtered) descendants.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject find(String selector);

	/**
	 * Gets the value of an attribute for the first element in the set of matched elements.
	 *
	 * @param attributeName The name of the attribute to retrieve the value of.
	 * @return The value of the attribute.
	 *
	 * @since 0.9.0
	 */
	String attr(String attributeName);

	/**
	 * Sets one or more attributes for <strong>every</strong> matched element.
	 *
	 * <p><b>Note:</b> If interacting with an <code>&lt;input&gt;</code> element while in a UI test,
	 * it is preferable to use {@link #click()} instead of setting the attributes
	 * through this function, as selenium tests should verify the pages from the user point of
	 * view.</p>
     *
     * @param attributeName Name of the attribute to set.
     * @param value Value to set.
     * @return The same seleniumQuery object.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject attr(String attributeName, Object value);

	/**
	 * Gets the value of a property for the first element in the set of matched elements.
	 *
     * @param propertyName Name of the property to read.
     * @param <T> Property expected value's type.
     * @return Property value.
     *
	 * @since 0.9.0
	 */
	<T> T prop(String propertyName);

    /**
	 * Set one or more properties for <strong>every</strong> matched element
	 *
	 * <p><b>Note:</b> If interacting with an <code>&lt;input&gt;</code> element while in a UI test,
	 * it is preferable to use {@link #click()} instead of setting the attributes
	 * through this function, as selenium tests should verify the pages from the user point of
	 * view.</p>
	 *
     * @param propertyName Name of the property to write.
     * @param value The value to set for the property.
     * @return The same seleniumQueryObject.
     *
	 * @since 0.9.0
     */
	SeleniumQueryObject prop(String propertyName, Object value);

    /**
	 * Retrieves one of the {@link WebElement} matched by the seleniumQuery object.
	 *
	 * @param index A zero-based integer indicating which element to retrieve.
	 * @return The element at the specified index.
	 *
	 * @since 0.9.0
	 */
	WebElement get(int index);

	/**
	 * Retrieves the {@link WebElement}s matched by the seleniumQuery object.
	 *
	 * @return The (immutable) list of matched elements.
	 *
	 * @since 0.9.0
	 */
	List<WebElement> get();

	/**
	 * Removes an attribute from each element in the set of matched elements.
	 * It can be a space-separated list of attributes.
	 *
     * @param attributeNames An attribute to remove; it can be a space-separated list of attributes.
	 * @return A self reference.
	 *
	 * @since 0.9.0]
	 */
	SeleniumQueryObject removeAttr(String attributeNames);

	/**
	 * Gets the HTML contents of the first element in the set of matched elements.
	 *
	 * @return The HTML of the first element in the set of matched elements.
	 *
	 * @since 0.9.0
	 */
	String html();

	/**
	 * Checks the current matched set of elements against a selector and returns <code>true</code>
	 * if at least one of these elements matches the given arguments.
	 *
	 * @param selector	A string containing a selector expression to match elements against.
     * @return If AT LEAS ONE of the elements matches the given selector.
     *
	 * @since 0.9.0
	 */
	boolean is(String selector);

	/**
	 * Determines whether any of the matched elements are assigned the given class.
	 *
     * @param className The class name to search for.
     * @return If any of the matched elements is assigned to the given class.
     *
	 * @since 0.9.0
	 */
	boolean hasClass(String className);

	/**
	 * Retrieves all the elements contained in the seleniumQuery set, as an array.
	 *
	 * @return The set of matched elements as an array.
	 *
	 * @since 0.9.0
	 */
	WebElement[] toArray();

	/**
	 * For each element in the set, gets the first element that matches the selector by
	 * testing the element itself and traversing up through its ancestors in the DOM tree.
	 *
	 * @param selector A selector expression to match elements against.
	 *
	 * @return A self reference.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject closest(String selector);

	/**
	 * <p>Triggers the <code>focus</code> event on <b>every</b> element of the matched set.</p>
	 *
	 * <p><b>Note:</b> The order of the triggering is the order of the elements in the
	 * matched list. The last one will end up with the focus, though all of them
	 * will have it at some point.</p>
     *
     * @return A self reference.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject focus();

	/**
	 * Gets the children of each element in the set of matched elements.
	 *
	 * @return A <b>new</b> SeleniumQueryObject, containing the children of each element in the set of matched elements.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject children();

	/**
	 * Gets the children of each element in the set of matched elements, filtered by a selector.
	 *
	 * @param selector Selector to filter the children.
	 * @return A <b>new</b> SeleniumQueryObject, containing the children of each element in the set of matched elements.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject children(String selector);

	/**
	 * Get the parent of each element in the current set of matched elements, optionally filtered by a selector.
	 * <br><br>
	 * Note that <code>$("html").parent()</code> returns an empty matched set, as &lt;html&gt; has no parent elements.
	 *
	 * @return A <b>new</b> SeleniumQueryObject, containing the parent of each element in the set of matched elements.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject parent();

	/**
	 * Get the parent of each element in the current set of matched elements, filtered by a selector.
	 *
	 * <br><br>
	 * Note that <code>$("html").parent("selector-Matching-HTML-Element")</code> returns an empty matched set,
	 * as &lt;html&gt; has no parent elements.
	 *
	 * @param selector Selector to filter the parents.
	 * @return A <b>new</b> SeleniumQueryObject, containing the parent of each element in the set of matched elements.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject parent(String selector);

	/**
	 * <p>Attempts to submits, in the current order, every element in the matched set.</p>
	 * <p>If a matched element is a form, or an element within a form, then it will be submitted.</p>
	 * <p><b>If submitting an element causes the current page to change, then this method will block until
	 * the new page is loaded; as consequence, not all elements may get to be submitted - and will be ignored.</b></p>
	 *
	 * @throws org.openqa.selenium.NoSuchElementException If a submitted element is not a, or within a, form.
	 *
	 * @return A self reference.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryObject submit();

	/**
	 * This method will be removed in {@code 1.0.0}.
	 * @deprecated Use: <code>$("selector").as().select().selectByVisibleText(text);</code>
	 * @param text The visible text to match against.
	 * @return A self reference.
	 * @since 0.9.0
	 */
	@SuppressWarnings("unused")
	@Deprecated
	SeleniumQueryObject selectOptionByVisibleText(String text);

	/**
	 * This method will be removed in {@code 1.0.0}.
	 * @deprecated Use: <code>$("selector").as().select().selectByValue(value);</code>
	 * @param value The value to match against.
	 * @return A self reference.
	 * @since 0.9.0
	 */
	@SuppressWarnings("unused")
	@Deprecated
	SeleniumQueryObject selectOptionByValue(String value);

	/**
     * Reduce the set of matched elements to those that pass the predicate's test.
     *
     * @param filterFunction A predicate used as a test for each element in the set.
     * @return An object with the elements that passed the predicate's test.
     * @since 0.11.0
     */
	SeleniumQueryObject filter(Predicate<WebElement> filterFunction);

    /**
     * Reduce the set of matched elements to those that match the given selector.
     *
     * @param selector A string containing a selector expression to match the current set of elements against.
     * @return An object with the elements that matched the selector.
     * @since 0.11.0
     */
	SeleniumQueryObject filter(String selector);

    /**
     * Interface for the functions used in $().each().
     * @since 0.13.0
     */
	interface EachFunction {
        /**
         * A function that will be executed for each matched element.
         * @param index The position of the element in the matched set.
         * @param element The element from the matched set.
         * @return false if the iteration should stop. true if it should continue.
         * @since 0.13.0
         */
		boolean apply(int index, WebElement element);
	}

	/**
	 * Iterate over a seleniumQuery object, executing a function for each matched element.
	 * <br><br>
	 * You can stop the loop from within the callback function by returning false.<br>
	 * Returning true will continue the iteration as usual.
	 *
	 * @param function A function to execute for each matched element.
	 * @return The object that was iterated.
	 * @since 0.13.0
     */
	SeleniumQueryObject each(EachFunction function);

    /**
     * Returns a sequential {@code Stream} with the set of matched elements as its source.
     *
     * @return a sequential {@code Stream} over the elements in this object.
     * @since 0.18.0
     */
	Stream<WebElement> stream();

	/**
	 * Pass each element in the current matched set through a function, producing a list object containing the return values.
	 *
	 * @param <T> The returning type of the mapper function.
	 * @param mapper The function to process each item against.
	 * @return A list containing all translated objects.
	 * @since 0.18.0
     */
	default <T> List<T> map(Function<? super WebElement, ? extends T> mapper) {
	    return this.stream().map(mapper).collect(Collectors.toList());
    }

}
