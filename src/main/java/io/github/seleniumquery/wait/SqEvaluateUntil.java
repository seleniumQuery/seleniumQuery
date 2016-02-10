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

package io.github.seleniumquery.wait;

import io.github.seleniumquery.SeleniumQueryEvaluateUntil;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.SeleniumQueryWaitAndOrThen;
import io.github.seleniumquery.wait.evaluators.ContainsEvaluator;
import io.github.seleniumquery.wait.evaluators.EqualsEvaluator;
import io.github.seleniumquery.wait.evaluators.Evaluator;
import io.github.seleniumquery.wait.evaluators.MatchesEvaluator;
import io.github.seleniumquery.wait.evaluators.comparison.GreaterThanEvaluator;
import io.github.seleniumquery.wait.evaluators.comparison.LessThanEvaluator;
import io.github.seleniumquery.wait.getters.Getter;

/**
 * Functions used in the waitUntil().
 *
 * @param <T> The type returned by the getter and TYPE OF THE ARGUMENT used in the end function.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
class SqEvaluateUntil<T> implements io.github.seleniumquery.SeleniumQueryEvaluateUntil<T> {

	private SeleniumQueryFluentWait fluentWait;
	protected Getter<T> getter;
	protected SeleniumQueryObject seleniumQueryObject;
	private boolean negated;

	SqEvaluateUntil(SeleniumQueryFluentWait fluentWait, Getter<T> getter, SeleniumQueryObject seleniumQueryObject) {
		this(fluentWait, getter, seleniumQueryObject, false);
	}

	private SqEvaluateUntil(SeleniumQueryFluentWait fluentWait, Getter<T> getter, SeleniumQueryObject seleniumQueryObject, boolean negated) {
		this.fluentWait = fluentWait;
		this.getter = getter;
		this.seleniumQueryObject = seleniumQueryObject;
		this.negated = negated;
	}

	@Override
	public SeleniumQueryWaitAndOrThen isEqualTo(T valueToEqual) {
		Evaluator<T> equalsEvaluator = new EqualsEvaluator<>(getter);
		SeleniumQueryObject sq = fluentWait.waitUntil(equalsEvaluator, valueToEqual, seleniumQueryObject, this.negated);
		return new SqAndOrThen(sq);
	}

	@Override
	public SeleniumQueryWaitAndOrThen contains(String string) {
		Evaluator<String> containsEvaluator = new ContainsEvaluator(getter);
		return new SqAndOrThen(fluentWait.waitUntil(containsEvaluator, string, seleniumQueryObject, this.negated));
	}

	@Override
	public SeleniumQueryWaitAndOrThen matches(String regex) {
		Evaluator<String> matchesEvaluator = new MatchesEvaluator(getter);
		return new SqAndOrThen(fluentWait.waitUntil(matchesEvaluator, regex, seleniumQueryObject, this.negated));
	}

	@Override
	public SeleniumQueryWaitAndOrThen isGreaterThan(Number valueToCompare) {
		Evaluator<Number> greaterThanEvaluator = new GreaterThanEvaluator(getter);
		SeleniumQueryObject sq = fluentWait.waitUntil(greaterThanEvaluator, valueToCompare, seleniumQueryObject, this.negated);
		return new SqAndOrThen(sq);
	}

	@Override
	public SeleniumQueryWaitAndOrThen isLessThan(Number valueToCompare) {
		Evaluator<Number> lessThanEvaluator = new LessThanEvaluator(getter);
		SeleniumQueryObject sq = fluentWait.waitUntil(lessThanEvaluator, valueToCompare, seleniumQueryObject, this.negated);
		return new SqAndOrThen(sq);
	}

	@Override
	public SeleniumQueryEvaluateUntil<T> not() {
		return new SqEvaluateUntil<>(fluentWait, getter, seleniumQueryObject, !this.negated);
	}

}