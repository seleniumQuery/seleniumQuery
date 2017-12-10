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

package io.github.seleniumquery.internal.fluentfunctions.evaluators.matches;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hamcrest.Matcher;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;
import io.github.seleniumquery.internal.fluentfunctions.getters.Getter;

/**
 * Evaluator that tests the elements' values against a hamcrest matcher.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class MatchesHamcrestMatcherEvaluator<GETTERTYPE> implements Evaluator<Matcher<GETTERTYPE>, GETTERTYPE> {

	private static final Log LOGGER = LogFactory.getLog(MatchesHamcrestMatcherEvaluator.class);

	private Getter<GETTERTYPE> getter;

	public MatchesHamcrestMatcherEvaluator(Getter<GETTERTYPE> getter) {
		this.getter = getter;
	}

 	@Override
	public EvaluationReport<GETTERTYPE> evaluate(SeleniumQueryObject seleniumQueryObject, Matcher<GETTERTYPE> matcher) {
		LOGGER.debug("Evaluating .matches(<Matcher>)...");
        GETTERTYPE actualValue = getter.get(seleniumQueryObject);
        LOGGER.debug("Evaluating .matches(<Matcher>)... got " + getter + ": " + quoteValue(actualValue) + ". Wanted: <" + matcher + ">.");
        boolean satisfiesConstraints = matcher.matches(actualValue);
        return new EvaluationReport<>(actualValue, satisfiesConstraints);
	}

	@Override
	public String describeEvaluatorFunction(Matcher<GETTERTYPE> matcher, FluentBehaviorModifier fluentBehaviorModifier) {
        return getter.toString() + fluentBehaviorModifier.asFunctionName() + ".matches(<" + matcher + ">)";
	}

    @Override
    public String getterAsString() {
        return getter.toString();
    }

    @Override
    public String describeExpectedValue(Matcher<GETTERTYPE> matcher) {
        return String.format("be <%s>", matcher);
    }

}
