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

/**
 * SeleniumQuery fluent functions.
 *
 * @author acdcjunior
 * @since 0.18.0
 */
@SuppressWarnings("deprecation")
public interface SeleniumQueryFluentFunction extends SeleniumQueryWaitUntil {

	/**
	 * Evaluates if <b>at least one element returned</b> - by a query to the selector used
	 * to construct this seleniumQuery object - <b>is matched by the selector given</b>.
	 *
     * @param selector The selector to test.
	 * @return An object for specifying other waiting conditions (<code>.and()</code>) or to execute usual functions (<code>.then()</code>).
	 *
	 * @since 0.9.0
	 */
	SeleniumQueryFluentAndOrThen is(String selector);

	/**
	 * Evaluates if the value of this seleniumQuery object meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the values of the elements must meet.
	 * @since 0.9.0
	 */
	SeleniumQueryFluentFunctionEvaluateIf<String> val();

	/**
	 * Evaluates if the text of this seleniumQuery object meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the texts of the elements must meet.
	 * @since 0.9.0
	 */
	SeleniumQueryFluentFunctionEvaluateIf<String> text();

	/**
	 * Evaluates if the given attribute of this seleniumQuery object meets the specified criteria.
	 *
     * @param attributeName The name of the attribute to evaluate.
	 * @return An object for specifying the criteria which the attributes of the elements must meet.
	 * @since 0.9.0
	 */
	SeleniumQueryFluentFunctionEvaluateIf<String> attr(String attributeName);

	/**
     * Evaluates if the given property of this seleniumQuery object meets the specified criteria.
	 *
     * @param propertyName The name of the property to evaluate.
     * @param <T>  The expected type of the property.
	 * @return An object for specifying the criteria which the properties of the elements must meet.
	 * @since 0.9.0
	 */
	<T> SeleniumQueryFluentFunctionEvaluateIf<T> prop(String propertyName);

	/**
     * Evaluates if the innerHTML of this seleniumQuery object meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the htmls of the elements must meet.
	 * @since 0.9.0
	 */
	SeleniumQueryFluentFunctionEvaluateIf<String> html();

	/**
     * Evaluates if the size of this seleniumQuery object meets the specified criteria.
	 *
	 * @return An object for specifying the criteria which the quantity of the elements must meet.
	 * @since 0.9.0
	 */
	SeleniumQueryFluentFunctionEvaluateIf<Integer> size();

    /**
     * Evaluates if the size of this seleniumQuery is greated than zero.
     *
     * @return An object for specifying other waiting conditions (<code>.and()</code>) or to execute usual functions (<code>.then()</code>).
     * @since 0.18.0
     */
    SeleniumQueryFluentAndOrThen isNotEmpty();

    /**
     * Evaluates if the size of this seleniumQuery is equal to zero.
     *
     * @return An object for specifying other waiting conditions (<code>.and()</code>) or to execute usual functions (<code>.then()</code>).
     * @since 0.18.0
     */
    SeleniumQueryFluentAndOrThen isEmpty();

    /**
     * Evaluates if this seleniumQuery object has elements (is not empty).
     *
     * <br><br>
     *     Note: this is an alias to {@code .isNotEmpty()}.
     *
     * @return An object for specifying other waiting conditions (<code>.and()</code>) or to execute usual functions (<code>.then()</code>).
     * @since 0.18.0
     */
    SeleniumQueryFluentAndOrThen isPresent();

    /**
     * Evaluates if this seleniumQuery object has <b>only visible</b> elements.
     *
     * <br><br>
     *     Note: this is different from {@code .is(":visible")} because {@code .is()} requires only one
     *     element to match the selector (to be visible), whereas this {@code .isVisible()} method
     *     requires <b>all</b> matched elements to be visible.
     *
     * @return An object for specifying other waiting conditions (<code>.and()</code>) or to execute usual functions (<code>.then()</code>).
     * @since 0.18.0
     */
    SeleniumQueryFluentAndOrThen isVisible();

    /**
     * Evaluates if this seleniumQuery object has <b>only visible</b> elements.
     *
     * <br><br>
     *     Note: this is different from {@code .is(":visible")} because {@code .is()} requires only one
     *     element to match the selector (to be visible), whereas this {@code .isVisible()} method
     *     requires <b>all</b> matched elements to be visible.
     * <br><br>
     *     This is an alias to {@code .isVisible()}.
     *
     * @return An object for specifying other waiting conditions (<code>.and()</code>) or to execute usual functions (<code>.then()</code>).
     * @since 0.18.0
     */
    SeleniumQueryFluentAndOrThen isDisplayed();

}
