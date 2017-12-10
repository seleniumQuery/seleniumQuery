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

package io.github.seleniumquery.internal.fluentfunctions.evaluators.matches;

import java.util.function.Predicate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;
import io.github.seleniumquery.internal.fluentfunctions.getters.Getter;

/**
 * Evaluator that tests the elements' values against a predicate function.
 *
 * @author acdcjunior
 * @since 0.18.0
 */
public class MatchesPredicateEvaluator<GETTERTYPE> implements Evaluator<Predicate<GETTERTYPE>, GETTERTYPE> {

    private static final Log LOGGER = LogFactory.getLog(MatchesPredicateEvaluator.class);

	private Getter<GETTERTYPE> getter;

	public MatchesPredicateEvaluator(Getter<GETTERTYPE> getter) {
		this.getter = getter;
	}

    @Override
    public EvaluationReport<GETTERTYPE> evaluate(SeleniumQueryObject seleniumQueryObject, Predicate<GETTERTYPE> predicateLambda) {
        LOGGER.debug("Evaluating .matches(<predicate function>)...");
        GETTERTYPE actualValue = getter.get(seleniumQueryObject);
        LOGGER.debug("Evaluating .matches(<predicate function>)... got " + getter + ": " + quoteValue(actualValue) + ". Wanted: <predicate function>.");
        boolean satisfiesConstraints = predicateLambda.test(actualValue);
        return new EvaluationReport<>(actualValue, satisfiesConstraints);
    }

	@Override
	public String describeEvaluatorFunction(Predicate<GETTERTYPE> predicateLambda, FluentBehaviorModifier fluentBehaviorModifier) {
		return getter.toString() + fluentBehaviorModifier.asFunctionName() + ".matches(<predicate function>)";
	}

    @Override
    public String getterAsString() {
        return getter.toString();
    }

    @Override
    public String describeExpectedValue(Predicate<GETTERTYPE> predicateLambda) {
        return "satisfy predicate function";
    }

}
