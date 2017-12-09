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

import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.hamcrest.Matcher;

/**
 * Evaluations available to fluent functions.
 *
 * @param <T> The type returned by the getter and TYPE OF THE ARGUMENT used in the end function.
 *
 * @author acdcjunior
 * @since 0.18.0
 */
@SuppressWarnings("deprecation")
public interface SeleniumQueryFluentFunctionEvaluateIf<T> extends SeleniumQueryWaitEvaluateUntil<T> {

    /**
     * <p>Tests if the result of the preceding function is equal to the given argument.</p>
     * <b>If you want to test for inequality, use <code>.not().isEqualTo()</code>.</b>
     *
     * @param valueToEqual The value the function call result must be equal to.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.9.0
     */
    SeleniumQueryFluentAndOrThen isEqualTo(T valueToEqual);

    /**
     * Tests if the result of the preceding function contains the given argument.
     *
     * @param string The string the function call result must contain.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.9.0
     */
    SeleniumQueryFluentAndOrThen contains(String string);

    /**
     * Tests if the result of the preceding function contains the given argument ignoring text case.
     *
     * @param string The string the function call result must contain.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.17.0
     */
    SeleniumQueryFluentAndOrThen containsIgnoreCase(String string);

    /**
     * <p>Tests if the result of the preceding function is greater than the given argument.</p>
     *
     * @param valueToCompare The number the function call result must be greater than.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.9.0
     */
    SeleniumQueryFluentAndOrThen isGreaterThan(Number valueToCompare);

    /**
     * <p>Tests if the result of the preceding function is less than the given argument.</p>
     *
     * @param valueToCompare The number the function call result must be less than.
     * @return An element that allows chaining of further evaluation conditions or calling regular functions.
     *
     * @since 0.9.0
     */
    SeleniumQueryFluentAndOrThen isLessThan(Number valueToCompare);

    /**
     * Negates the criteria that follows.
     *
     * @return An object for specifying the criteria which the function call result must <b>NOT</b> meet.
     *
     * @since 0.9.0
     */
    SeleniumQueryFluentFunctionEvaluateIf<T> not();

    /**
     * Tests if the result of the preceding function matches the given regex.
     *
     * @param regex The regex the function call result must match.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.9.0
     */
    SeleniumQueryFluentAndOrThen matches(String regex);

    /**
     * Tests if the result of the preceding function matches the given regex {@link Pattern}.
     *
     * @param regexPattern The regex {@link Pattern} the function call result must match.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.18.0
     */
    SeleniumQueryFluentAndOrThen matches(Pattern regexPattern);

    /**
     * <p>Tests if the result of the preceding function satisfies the condition specified by
     * <code>hamcrestMatcher</code>. Example:
     *
     * <pre>
     *   $("#myDiv").waitUntil().text().matches(Matchers.containsString("John Smith"));
     *   $("#myDiv").assertThat().text().matches(Matchers.containsString("John Smith"));
     * </pre>
     *
     * @param hamcrestMatcher an expression, built of {@link Matcher}s, specifying allowed
     * values
     * @see org.hamcrest.CoreMatchers
     * @see org.hamcrest.MatcherAssert
     *
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     * @since 0.18.0
     */
    SeleniumQueryFluentAndOrThen matches(Matcher<T> hamcrestMatcher);

    /**
     * Tests if the result of the preceding function matches the given {@link Predicate} function.
     *
     * @param predicate The {@link Predicate} the result must match.
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     *
     * @since 0.18.0
     */
    SeleniumQueryFluentAndOrThen matches(Predicate<T> predicate);

    /**
     * <p>Tests if the result of the preceding function <b>is empty ({@code ""}), {@code null} or whitespace only</b>.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * (null).isBlank()      = true
     * ("").isBlank()        = true
     * (" ").isBlank()       = true
     * ("bob").isBlank()     = false
     * ("  bob  ").isBlank() = false
     * </pre>
     *
     * @return An element that allows chaining of further waiting conditions or calling regular functions.
     * @since 0.18.0
     */
    SeleniumQueryFluentAndOrThen isBlank();

}
