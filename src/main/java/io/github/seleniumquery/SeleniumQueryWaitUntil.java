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

import io.github.seleniumquery.wait.SeleniumQueryEvaluateUntil;

/**
 * .waitUntil() functions.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public interface SeleniumQueryWaitUntil {

	/**
	 * Waits until <b>at least one element returned</b> - by a query to the selector used
	 * to construct this seleniumQuery object - <b>is matched by the selector given</b>.
	 *
     * @param selector The selector to test.
	 * @return An object for specifying other waiting conditions (<code>.and()</code>) or to execute usual functions (<code>.then()</code>).
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryAndOrThen is(String selector);

	/**
	 * Waits until every matched element's value meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the values of the elements must meet.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryEvaluateUntil<String> val();

	/**
	 * Waits until every matched element's text meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the texts of the elements must meet.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryEvaluateUntil<String> text();

	/**
	 * Waits until every matched element has the given attribute meeting the specified criteria.
	 *
     * @param attributeName The name of the attribute to evaluate.
	 * @return An object for specifying the criteria which the attributes of the elements must meet.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryEvaluateUntil<String> attr(String attributeName);

	/**
	 * Waits until every matched element has the given attribute property the specified criteria.
	 *
     * @param propertyName The name of the property to evaluate.
     * @param <T>  The expected type of the property.
	 * @return An object for specifying the criteria which the properties of the elements must meet.
	 *
	 * @since 0.9.0
	 */
	<T> SeleniumQueryEvaluateUntil<T> prop(String propertyName);

	/**
	 * Waits until every matched element's html meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the htmls of the elements must meet.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryEvaluateUntil<String> html();

	/**
	 * Waits until the quantity of matched elements meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the quantity of the elements must meet.
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryEvaluateUntil<Integer> size();

}
