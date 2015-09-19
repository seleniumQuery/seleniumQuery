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

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.wait.evaluators.*;
import io.github.seleniumquery.wait.getters.Getter;

/**
 * @author acdcjunior
 *
 * @param <T> The type returned by the getter and TYPE OF THE ARGUMENT used in the end function.
 *
 * @since 0.9.0
 */
public class SeleniumQueryEvaluateUntil<T> {
	
	protected SeleniumQueryFluentWait fluentWait;
	protected Getter<T> getter;
	protected SeleniumQueryObject seleniumQueryObject;
	protected boolean negated;
	
	public SeleniumQueryEvaluateUntil(SeleniumQueryFluentWait fluentWait, Getter<T> getter, SeleniumQueryObject seleniumQueryObject) {
		this(fluentWait, getter, seleniumQueryObject, false);
	}

	private SeleniumQueryEvaluateUntil(SeleniumQueryFluentWait fluentWait, Getter<T> getter, SeleniumQueryObject seleniumQueryObject, boolean negated) {
		this.fluentWait = fluentWait;
		this.getter = getter;
		this.seleniumQueryObject = seleniumQueryObject;
		this.negated = negated;
	}

	/**
	 * <p>Tests if the result of the preceding function is equal to the given argument.</p>
	 * <b>If you want to test for inequality, use <code>.not().isEqualTo()</code>.</b>
	 *
	 * @param valueToEqual The value the function call result must be equal to.
	 * @return An element that allows chaining of further waiting conditions or calling regular functions.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryAndOrThen isEqualTo(T valueToEqual) {
		Evaluator<T> equalsEvaluator = new EqualsEvaluator<>(getter);
		SeleniumQueryObject sq = fluentWait.waitUntil(equalsEvaluator, valueToEqual, seleniumQueryObject, this.negated);
		return new SeleniumQueryAndOrThen(sq);
	}

	/**
	 * Tests if the result of the preceding function contains the given argument.
	 *
	 * @param string The string the function call result must contain.
	 * @return An element that allows chaining of further waiting conditions or calling regular functions.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryAndOrThen contains(String string) {
		Evaluator<String> containsEvaluator = new ContainsEvaluator(getter);
		return new SeleniumQueryAndOrThen(fluentWait.waitUntil(containsEvaluator, string, seleniumQueryObject, this.negated));
	}

	/**
	 * Tests if the result of the preceding function matches the given regex.
	 *
	 * @param regex The regex the function call result must match.
	 * @return An element that allows chaining of further waiting conditions or calling regular functions.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryAndOrThen matches(String regex) {
		Evaluator<String> matchesEvaluator = new MatchesEvaluator(getter);
		return new SeleniumQueryAndOrThen(fluentWait.waitUntil(matchesEvaluator, regex, seleniumQueryObject, this.negated));
	}

	/**
	 * <p>Tests if the result of the preceding function is greater than the given argument.</p>
	 *
	 * @param valueToCompare The number the function call result must be greater than.
	 * @return An element that allows chaining of further waiting conditions or calling regular functions.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryAndOrThen isGreaterThan(Number valueToCompare) {
		Evaluator<Number> greaterThanEvaluator = new GreaterThanEvaluator(getter);
		SeleniumQueryObject sq = fluentWait.waitUntil(greaterThanEvaluator, valueToCompare, seleniumQueryObject, this.negated);
		return new SeleniumQueryAndOrThen(sq);
	}

	/**
	 * <p>Tests if the result of the preceding function is less than the given argument.</p>
	 *
	 * @param valueToCompare The number the function call result must be less than.
	 * @return An element that allows chaining of further waiting conditions or calling regular functions.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryAndOrThen isLessThan(Number valueToCompare) {
		Evaluator<Number> lessThanEvaluator = new LessThanEvaluator(getter);
		SeleniumQueryObject sq = fluentWait.waitUntil(lessThanEvaluator, valueToCompare, seleniumQueryObject, this.negated);
		return new SeleniumQueryAndOrThen(sq);
	}

	/**
	 * Negates the criteria that follows.
	 *
	 * @return An object for specifying the criteria which the function call result must <b>NOT</b> meet.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryEvaluateUntil<T> not() {
		return new SeleniumQueryEvaluateUntil<>(fluentWait, getter, seleniumQueryObject, !this.negated);
	}

}