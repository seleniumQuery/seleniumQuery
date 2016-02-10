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
 * Functions used in the waitUntil().
 *
 * @param <T> The type returned by the getter and TYPE OF THE ARGUMENT used in the end function.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public interface SeleniumQueryEvaluateUntil<T> {

    /**
     * <p>Tests if the result of the preceding function is equal to the given argument.</p>
     * <b>If you want to test for inequality, use <code>.not().isEqualTo()</code>.</b>
     *
     * @param valueToEqual The value the function call result must be equal to.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.9.0
     */
    SeleniumQueryAndOrThen isEqualTo(T valueToEqual);

    /**
     * Tests if the result of the preceding function contains the given argument.
     *
     * @param string The string the function call result must contain.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.9.0
     */
    SeleniumQueryAndOrThen contains(String string);

    /**
     * Tests if the result of the preceding function matches the given regex.
     *
     * @param regex The regex the function call result must match.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.9.0
     */
    SeleniumQueryAndOrThen matches(String regex);

    /**
     * <p>Tests if the result of the preceding function is greater than the given argument.</p>
     *
     * @param valueToCompare The number the function call result must be greater than.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.9.0
     */
    SeleniumQueryAndOrThen isGreaterThan(Number valueToCompare);

    /**
     * <p>Tests if the result of the preceding function is less than the given argument.</p>
     *
     * @param valueToCompare The number the function call result must be less than.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.9.0
     */
    SeleniumQueryAndOrThen isLessThan(Number valueToCompare);

    /**
     * Negates the criteria that follows.
     *
     * @return An object for specifying the criteria which the function call result must <b>NOT</b> meet.
     *
     * @since 0.9.0
     */
    SeleniumQueryEvaluateUntil<T> not();

}
