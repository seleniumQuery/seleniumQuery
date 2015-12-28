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

import io.github.seleniumquery.functions.SeleniumQueryFunctions;
import io.github.seleniumquery.functions.as.SeleniumQueryPlugin;
import io.github.seleniumquery.functions.as.StandardPlugins;
import io.github.seleniumquery.functions.jquery.attributes.AttrFunction;
import io.github.seleniumquery.functions.jquery.attributes.HasClassFunction;
import io.github.seleniumquery.functions.jquery.attributes.RemoveAttrFunction;
import io.github.seleniumquery.functions.jquery.events.ClickFunction;
import io.github.seleniumquery.functions.jquery.forms.FocusFunction;
import io.github.seleniumquery.functions.jquery.forms.SubmitFunction;
import io.github.seleniumquery.functions.jquery.manipulation.HtmlFunction;
import io.github.seleniumquery.functions.jquery.manipulation.TextFunction;
import io.github.seleniumquery.functions.jquery.miscellaneous.GetFunction;
import io.github.seleniumquery.functions.jquery.miscellaneous.ToArrayFunction;
import io.github.seleniumquery.functions.jquery.traversing.filtering.*;
import io.github.seleniumquery.functions.jquery.traversing.treetraversal.ChildrenFunction;
import io.github.seleniumquery.functions.jquery.traversing.treetraversal.ClosestFunction;
import io.github.seleniumquery.functions.jquery.traversing.treetraversal.FindFunction;
import io.github.seleniumquery.functions.jquery.traversing.treetraversal.ParentFunction;
import io.github.seleniumquery.utils.ListUtils;
import io.github.seleniumquery.wait.SeleniumQueryWaitUntil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

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
public class SeleniumQueryObject implements Iterable<WebElement> {
	
	public static final Log LOGGER = LogFactory.getLog(SeleniumQueryObject.class);

    public static final SeleniumQueryObject NOT_BUILT_BASED_ON_A_PREVIOUS_OBJECT = null;

	private SeleniumQueryFunctions seleniumQueryFunctions;

	private WebDriver driver;

    private By by;
	private List<WebElement> elements;
	
	/**
	 * The previous (or "parent") element, meaning this SeleniumQueryObject was created as result
	 * of calling a "destructive" function (such as {@link #not(String)}) on that element.<br>
	 * This property is retrieved by a call to {@link #end()}.
	 * 
	 * @since 0.9.0
	 */
	private SeleniumQueryObject previous;
	

	protected SeleniumQueryObject(SeleniumQueryFunctions seleniumQueryFunctions, WebDriver driver, By by) {
        this(seleniumQueryFunctions, driver, by, driver.findElements(by), NOT_BUILT_BASED_ON_A_PREVIOUS_OBJECT);
	}

	protected SeleniumQueryObject(SeleniumQueryFunctions seleniumQueryFunctions, WebDriver driver, By by, List<WebElement> webElements, SeleniumQueryObject previous) {
        this.seleniumQueryFunctions = seleniumQueryFunctions;
		this.driver = driver;
		this.by = by;
		this.elements = ListUtils.toImmutableRandomAccessList(webElements);
		this.previous = previous;
	}


	/**************************************************************************************************************************************
	 * Java SeleniumQueryObject waitUntil() and as() functions
	 **************************************************************************************************************************************/
	
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
	 * @return a {@link SeleniumQueryWaitUntil} object for specifying the wait conditions.
	 *
	 * @since 0.9.0
	 */
	public final SeleniumQueryWaitUntil waitUntil() {
		return new SeleniumQueryWaitUntil(this);
	}

	/**
	 * List of functions that will halt the execution and requery the selector until the specified condition is met, returning
	 * a new seleniumQuery object at the end.
	 *
	 * @param waitUntilTimeout Time in milliseconds to wait before a timeout is thrown.
	 * @return a {@link SeleniumQueryWaitUntil} object for specifying the wait conditions.
	 *
	 * @since 0.9.0
	 */
	public final SeleniumQueryWaitUntil waitUntil(long waitUntilTimeout) {
		return new SeleniumQueryWaitUntil(this, waitUntilTimeout);
	}

	/**
	 * List of functions that will halt the execution and requery the selector until the specified condition is met, returning
	 * a new seleniumQuery object at the end.
	 *
	 * @param waitUntilTimeout Time in milliseconds to wait for the condition.
	 * @param waitUntilPollingInterval Interval in milliseconds between every requery/check.
	 * @return a {@link SeleniumQueryWaitUntil} object for specifying the wait conditions.
	 *
	 * @since 0.9.0
	 */
	public final SeleniumQueryWaitUntil waitUntil(long waitUntilTimeout, long waitUntilPollingInterval) {
		return new SeleniumQueryWaitUntil(this, waitUntilTimeout, waitUntilPollingInterval);
	}

	/**
	 * Enables several functions (plugins) that allow executing specific tasks, from specific points of view, on
	 * the elements of this {@link io.github.seleniumquery.SeleniumQueryObject}.
	 *
	 * @return A list of functions showing the available tasks that can be performed.
	 *
	 * @since 0.9.0
	 */
	public StandardPlugins as() {
		return new StandardPlugins(this);
	}

	/**
	 * Enables the execution of functions defined by the plugin sent as argument.
	 *
	 * @param pluginFunction The plugin instance this object will be handed to.
	 * @return An object provided by the plugin.
	 *
	 * @since 0.9.0
	 */
	public <PLUGIN> PLUGIN as(SeleniumQueryPlugin<PLUGIN> pluginFunction) {
		return pluginFunction.as(this);
	}


	/**************************************************************************************************************************************
	 * Java SeleniumQueryObject specific functions
	 **************************************************************************************************************************************/

	/**
	 * Returns an iterator over the matched elements of this seleniumQuery object.
	 *
	 * @return an iterator over the matched elements of this seleniumQuery object.
	 */
	@Override
	public Iterator<WebElement> iterator() {
		return this.elements.iterator();
	}

	/**
	 * Returns the driver used to create this seleniumQuery object.
	 * @return the driver used to create this seleniumQuery object.
	 */
	public WebDriver getWebDriver() {
		return this.driver;
	}

	/**
	 * Returns the By used to search the matched elements of this seleniumQuery object.
	 * @return the By used to search the matched elements of this seleniumQuery object.
	 */
	public By getBy() {
		return this.by;
	}


	/**************************************************************************************************************************************
	 * jQuery-emulating functions
	 **************************************************************************************************************************************/

	/**
	 * Returns the number of elements in the seleniumQuery object.
	 *
	 * @return the number of elements in the seleniumQuery object.
	 *
	 * @since 0.9.0
	 */
	public int size() {
		return this.elements.size();
	}

	/**
	 * Removes elements from the set of matched elements.
	 *
	 * @param selector	A string containing a selector expression to match elements against.
	 * @return A seleniumQuery object containing all elements not removed.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject not(String selector) {
		return NotFunction.not(this, this.get(), selector);
	}

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
	public SeleniumQueryObject first() {
		return FirstFunction.first(this, this.get());
	}

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
	public SeleniumQueryObject last() {
		return LastFunction.last(this, this.get());
	}

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
	public SeleniumQueryObject eq(int index) {
		return EqFunction.eq(this, this.get(), index);
	}

	/**
	 * <p>Gets the combined text contents of each element in the set of matched elements, including
	 * their descendants.</p>
	 *
	 * <p><b>Note:</b> This functions uses Selenium's <code>{@link WebElement#getText()}</code>, and, as
	 * jQuery, <i>"due to variations in the HTML parsers in different browsers, the text returned may vary
	 * in newlines and other white space."</i></p>
	 *
	 * @return the text from all elements in the matched set.
	 *
	 * @since 0.9.0
	 */
	public String text() {
		// Warning!
		// It is impossible to read text from hidden elements in Selenium:
		// Since a user cannot read text in a hidden element, WebDriver will not allow access to it as well.
		// More in WebDriver FAQs: https://code.google.com/p/selenium/wiki/FrequentlyAskedQuestions#Q:_Why_is_it_not_possible_to_interact_with_hidden_elements?
		return TextFunction.text(this.get());
	}

	/**
	 * Clicks <strong>all</strong> elements in the set of matched elements, in the
	 * order they were matched.
	 *
	 * @return The same seleniumQuery object.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject click() {
		LOGGER.debug("Clicking "+this+".");
		return ClickFunction.click(this, this.get());
	}

	/**
	 * Sets the value of <strong>all</strong> elements in the set of matched elements.
	 *
	 * @param value The (string) value to be set.
	 * @return The same seleniumQuery object.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject val(String value) {
        return seleniumQueryFunctions.valWrite(this, value);
	}

    /**
	 * Sets the value of <strong>all</strong> elements in the set of matched elements.
	 *
	 * @param value The (number) value to be set.
	 * @return The same seleniumQuery object.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject val(Number value) {
        return seleniumQueryFunctions.valWrite(this, value);
	}

    /**
	 * Gets the current value of the first element in the set of matched elements.
	 *
	 * @return The value of the first element.
	 *
	 * @since 0.9.0
	 */
	public String val() {
		return seleniumQueryFunctions.valRead(this);
	}

    /**
	 * Ends the most recent filtering operation in the current chain and returns the set of matched
	 * elements to its previous state.
	 *
	 * @return the seleniumQuery object that originated the current instance.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject end() {
		return this.previous;
	}

	/**
	 * Gets the descendants of each element in the current set of matched elements, filtered by a selector.
	 *
	 * @param selector Selector the descendants must match.
	 * @return a {@link SeleniumQueryObject} containing the (filtered) descendants.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject find(String selector) {
		return FindFunction.find(this, this.get(), selector);
	}

	/**
	 * Gets the value of an attribute for the first element in the set of matched elements.
	 *
	 * @param attributeName The name of the attribute to retrieve the value of.
	 * @return The value of the attribute.
	 *
	 * @since 0.9.0
	 */
	public String attr(String attributeName) {
		return AttrFunction.attr(this, this.get(), attributeName);
	}

	/**
	 * Sets one or more attributes for <strong>every</strong> matched element.
	 *
	 * <p><b>Note:</b> If interacting with an <code>&lt;input&gt;</code> element while in a UI test,
	 * it is preferable to use {@link #click()} instead of setting the attributes
	 * through this function, as selenium tests should verify the pages from the user point of
	 * view.</p>
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject attr(String attributeName, Object value) {
		return AttrFunction.attr(this, this.get(), attributeName, value);
	}

	/**
	 * Gets the value of a property for the first element in the set of matched elements.
	 *
	 * @since 0.9.0
	 */
	public <T> T prop(String propertyName) {
		return seleniumQueryFunctions.propRead(this, propertyName);
	}

    /**
	 * Set one or more properties for <strong>every</strong> matched element
	 *
	 * <p><b>Note:</b> If interacting with an <code>&lt;input&gt;</code> element while in a UI test,
	 * it is preferable to use {@link #click()} instead of setting the attributes
	 * through this function, as selenium tests should verify the pages from the user point of
	 * view.</p>
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject prop(String propertyName, Object value) {
		return seleniumQueryFunctions.propWrite(this, propertyName, value);
	}

    /**
	 * Retrieves one of the {@link WebElement} matched by the seleniumQuery object.
	 *
	 * @param index A zero-based integer indicating which element to retrieve.
	 * @return The element at the specified index.
	 *
	 * @since 0.9.0
	 */
	public WebElement get(int index) {
		return GetFunction.get(this, index);
	}

	/**
	 * Retrieves the {@link WebElement}s matched by the seleniumQuery object.
	 *
	 * @return The (immutable) list of matched elements.
	 *
	 * @since 0.9.0
	 */
	public List<WebElement> get() {
		return this.elements;
	}

	/**
	 * Removes an attribute from each element in the set of matched elements.
	 * It can be a space-separated list of attributes.
	 *
	 * @return A self reference.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject removeAttr(String attributeNames) {
		return RemoveAttrFunction.removeAttr(this, this.get(), attributeNames);
	}

	/**
	 * Gets the HTML contents of the first element in the set of matched elements.
	 *
	 * @return The HTML of the first element in the set of matched elements.
	 *
	 * @since 0.9.0
	 */
	public String html() {
		return HtmlFunction.html(this);
	}

	/**
	 * Checks the current matched set of elements against a selector and returns <code>true</code>
	 * if at least one of these elements matches the given arguments.
	 *
	 * @param selector	A string containing a selector expression to match elements against.
	 * @since 0.9.0
	 */
	public boolean is(String selector) {
		return IsFunction.is(this, this.get(), selector);
	}

	/**
	 * Determines whether any of the matched elements are assigned the given class.
	 *
	 * @since 0.9.0
	 */
	public boolean hasClass(String className) {
		return HasClassFunction.hasClass(this, this.get(), className);
	}

	/**
	 * Retrieves all the elements contained in the seleniumQuery set, as an array.
	 *
	 * @return The set of matched elements as an array.
	 *
	 * @since 0.9.0
	 */
	public WebElement[] toArray() {
		return ToArrayFunction.toArray(this, this.get());
	}

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
	public SeleniumQueryObject closest(String selector) {
		return ClosestFunction.closest(this, this.get(), selector);
	}

	/**
	 * <p>Triggers the <code>focus</code> event on <b>every</b> element of the matched set.</p>
	 *
	 * <p><b>Note:</b> The order of the triggering is the order of the elements in the
	 * matched list. The last one will end up with the focus, though all of them
	 * will have it at some point.</p>
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject focus() {
		return FocusFunction.focus(this);
	}

	/**
	 * Gets the children of each element in the set of matched elements.
	 *
	 * @return A <b>new</b> SeleniumQueryObject, containing the children of each element in the set of matched elements.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject children() {
		return ChildrenFunction.children(this);
	}

	/**
	 * Gets the children of each element in the set of matched elements, filtered by a selector.
	 *
	 * @param selector Selector to filter the children.
	 * @return A <b>new</b> SeleniumQueryObject, containing the children of each element in the set of matched elements.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject children(String selector) {
		return ChildrenFunction.children(this, selector);
	}

	/**
	 * Get the parent of each element in the current set of matched elements, optionally filtered by a selector.
	 * <br><br>
	 * Note that <code>$("html").parent()</code> returns an empty matched set, as &lt;html&gt; has no parent elements.
	 *
	 * @return A <b>new</b> SeleniumQueryObject, containing the parent of each element in the set of matched elements.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject parent() {
		return ParentFunction.parent(this);
	}

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
	public SeleniumQueryObject parent(String selector) {
		return ParentFunction.parent(this, selector);
	}

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
	public SeleniumQueryObject submit() {
		SubmitFunction.submit(this);
		return this;
	}

	/**
	 * This method will be removed in {@code 0.10.0}.
	 * @deprecated Use: <code>$("selector").as().select().selectByVisibleText(text);</code>
	 * @param text The visible text to match against.
	 * @return A self reference.
	 * @since 0.9.0
	 */
	@SuppressWarnings("unused")
	public SeleniumQueryObject selectOptionByVisibleText(String text) {
		return as().select().selectByVisibleText(text);
	}

	/**
	 * This method will be removed in {@code 0.10.0}.
	 * @deprecated Use: <code>$("selector").as().select().selectByValue(value);</code>
	 * @param value The value to match against.
	 * @return A self reference.
	 * @since 0.9.0
	 */
	@SuppressWarnings("unused")
	public SeleniumQueryObject selectOptionByValue(String value) {
		return as().select().selectByValue(value);
	}

	@Override
	public String toString() {
		return this.by.toString();
	}

}