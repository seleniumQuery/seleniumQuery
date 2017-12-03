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

package io.github.seleniumquery.wait;

import org.hamcrest.Matcher;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.SeleniumQueryWaitAndOrThen;
import io.github.seleniumquery.SeleniumQueryWaitEvaluateUntil;
import io.github.seleniumquery.wait.evaluators.ContainsEvaluator;
import io.github.seleniumquery.wait.evaluators.ContainsIgnoreCaseEvaluator;
import io.github.seleniumquery.wait.evaluators.EqualsEvaluator;
import io.github.seleniumquery.wait.evaluators.Evaluator;
import io.github.seleniumquery.wait.evaluators.MatchesEvaluator;
import io.github.seleniumquery.wait.evaluators.ThatEvaluator;
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
class EvaluateUntil<T> implements SeleniumQueryWaitEvaluateUntil<T> {

	private FluentSqWait fluentWait;
	protected Getter<T> getter;
	protected SeleniumQueryObject seleniumQueryObject;
	private boolean negated;

	EvaluateUntil(FluentSqWait fluentWait, Getter<T> getter, SeleniumQueryObject seleniumQueryObject) {
		this(fluentWait, getter, seleniumQueryObject, false);
	}

	private EvaluateUntil(FluentSqWait fluentWait, Getter<T> getter, SeleniumQueryObject seleniumQueryObject, boolean negated) {
		this.fluentWait = fluentWait;
		this.getter = getter;
		this.seleniumQueryObject = seleniumQueryObject;
		this.negated = negated;
	}

	@Override
	public SeleniumQueryWaitAndOrThen isEqualTo(T valueToEqual) {
        return andOrThen(new EqualsEvaluator<>(getter), valueToEqual);
	}

    private <V> AndOrThen andOrThen(Evaluator<V> evaluator, V value) {
        return new AndOrThen(fluentWait.waitUntil(evaluator, value, seleniumQueryObject, this.negated));
    }

	@Override
	public SeleniumQueryWaitAndOrThen contains(String string) {
        return andOrThen(new ContainsEvaluator(getter), string);
	}

    @Override
    public SeleniumQueryWaitAndOrThen containsIgnoreCase(String string) {
        return andOrThen(new ContainsIgnoreCaseEvaluator(getter), string);
    }

	@Override
	public SeleniumQueryWaitAndOrThen matches(String regex) {
        return andOrThen(new MatchesEvaluator(getter), regex);
	}

	@Override
	public SeleniumQueryWaitAndOrThen isGreaterThan(Number valueToCompare) {
        return andOrThen(new GreaterThanEvaluator(getter), valueToCompare);
	}

    @Override
	public SeleniumQueryWaitAndOrThen isLessThan(Number valueToCompare) {
        return andOrThen(new LessThanEvaluator(getter), valueToCompare);
	}

	@Override
	public SeleniumQueryWaitEvaluateUntil<T> not() {
		return new EvaluateUntil<>(fluentWait, getter, seleniumQueryObject, !this.negated);
	}

    @Override
    public SeleniumQueryWaitAndOrThen that(Matcher<T> hamcrestMatcher) {
        return andOrThen(new ThatEvaluator<>(getter), hamcrestMatcher);
    }

}
