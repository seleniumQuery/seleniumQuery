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

package io.github.seleniumquery.wait;

import io.github.seleniumquery.SeleniumQueryConfig;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.wait.evaluators.IsEvaluator;
import io.github.seleniumquery.wait.getters.*;

/**
 * .waitUntil() functions.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class SeleniumQueryWaitUntil {
	
	private SeleniumQueryObject seleniumQueryObject;
	
	private SeleniumQueryFluentWait fluentWait;
	
	/**
	 * Creates a waitUntil object for the given seleniumQueryObject, with timeout and polling interval
	 * as defined in the config files.
	 * @param seleniumQueryObject The object to wait for.
	 * @since 0.9.0
	 */
	public SeleniumQueryWaitUntil(SeleniumQueryObject seleniumQueryObject) {
		this(seleniumQueryObject, SeleniumQueryConfig.getWaitUntilTimeout(), SeleniumQueryConfig.getWaitUntilPollingInterval());
	}
	
	/**
	 * Creates a waitUntil object for the given seleniumQueryObject, with the given timeout and polling interval
	 * as defined in the config files.
	 * @param seleniumQueryObject The object to wait for.
	 * @param waitUntilTimeout Time, in ms, to wait.
	 * @since 0.9.0
     */
	public SeleniumQueryWaitUntil(SeleniumQueryObject seleniumQueryObject, long waitUntilTimeout) {
		this(seleniumQueryObject, waitUntilTimeout, SeleniumQueryConfig.getWaitUntilPollingInterval());
	}
	
	/**
	 * Creates a waitUntil object for the given seleniumQueryObject, with the given timeout and polling interval.
	 *
	 * @param seleniumQueryObject The object to wait for.
	 * @param waitUntilTimeout Time, in ms, to wait.
	 * @param waitUntilPollingInterval Interval, in ms, to poll the object.
	 * @since 0.9.0
     */
	public SeleniumQueryWaitUntil(SeleniumQueryObject seleniumQueryObject, long waitUntilTimeout, long waitUntilPollingInterval) {
		this.seleniumQueryObject = seleniumQueryObject;
		this.fluentWait = new SeleniumQueryFluentWait(waitUntilTimeout, waitUntilPollingInterval);
	}
	
	/**
	 * Waits until <b>at least one element returned</b> - by a query to the selector used
	 * to construct this seleniumQuery object - <b>is matched by the selector given</b>.
	 *
     * @param selector The selector to test.
	 * @return An object for specifying other waiting conditions (<code>.and()</code>) or to execute usual functions (<code>.then()</code>).
	 * 
	 * @since 0.9.0
	 */
	public SeleniumQueryAndOrThen is(String selector) {
		SeleniumQueryObject seleniumQueryObjectAfterWait = this.fluentWait.waitUntil(IsEvaluator.IS_EVALUATOR, selector, seleniumQueryObject, false);
		return new SeleniumQueryAndOrThen(seleniumQueryObjectAfterWait);
	}

	/**
	 * Waits until every matched element's value meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the values of the elements must meet.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryEvaluateUntil<String> val() {
		return new SeleniumQueryEvaluateUntil<>(this.fluentWait, ValGetter.VAL_GETTER, seleniumQueryObject);
	}

	/**
	 * Waits until every matched element's text meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the texts of the elements must meet.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryEvaluateUntil<String> text() {
		return new SeleniumQueryEvaluateUntil<>(this.fluentWait, TextGetter.TEXT_GETTER, seleniumQueryObject);
	}

	/**
	 * Waits until every matched element has the given attribute meeting the specified criteria.
	 *
     * @param attributeName The name of the attribute to evaluate.
	 * @return An object for specifying the criteria which the attributes of the elements must meet.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryEvaluateUntil<String> attr(String attributeName) {
		return new SeleniumQueryEvaluateUntil<>(this.fluentWait, new AttrGetter(attributeName), seleniumQueryObject);
	}

	/**
	 * Waits until every matched element has the given attribute property the specified criteria.
	 *
     * @param propertyName The name of the property to evaluate.
     * @param <T>  The expected type of the property.
	 * @return An object for specifying the criteria which the properties of the elements must meet.
	 *
	 * @since 0.9.0
	 */
    public <T> SeleniumQueryEvaluateUntil<T> prop(String propertyName) {
		return new SeleniumQueryEvaluateUntil<>(this.fluentWait, new PropGetter<T>(propertyName), seleniumQueryObject);
	}

	/**
	 * Waits until every matched element's html meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the htmls of the elements must meet.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryEvaluateUntil<String> html() {
		return new SeleniumQueryEvaluateUntil<>(this.fluentWait, HtmlGetter.HTML_GETTER, seleniumQueryObject);
	}

	/**
	 * Waits until the quantity of matched elements meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the quantity of the elements must meet.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryEvaluateUntil<Integer> size() {
		return new SeleniumQueryEvaluateUntil<>(this.fluentWait, SizeGetter.SIZE_GETTER, seleniumQueryObject);
	}
	
}