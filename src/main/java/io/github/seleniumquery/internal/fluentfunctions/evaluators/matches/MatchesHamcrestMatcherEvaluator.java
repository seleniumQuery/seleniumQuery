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
public class MatchesHamcrestMatcherEvaluator<T> implements Evaluator<Matcher<T>> {

	private static final Log LOGGER = LogFactory.getLog(MatchesHamcrestMatcherEvaluator.class);

	private Getter<T> getter;

	public MatchesHamcrestMatcherEvaluator(Getter<T> getter) {
		this.getter = getter;
	}

 	@Override
	public EvaluationReport evaluate(SeleniumQueryObject seleniumQueryObject, Matcher<T> matcher) {
		LOGGER.debug("Evaluating .matches(<Matcher>)...");
		T lastValue = getter.get(seleniumQueryObject);
		LOGGER.debug("Evaluating .matches(<Matcher>)... got "+getter+": \""+lastValue+"\". Wanted: <"+matcher+">.");
        boolean satisfiesConstraints = matcher.matches(lastValue);
        return new EvaluationReport(lastValue.toString(), satisfiesConstraints);
	}

	@Override
	public String describeEvaluatorFunction(Matcher<T> matcher, FluentBehaviorModifier fluentBehaviorModifier) {
        return getter.toString() + fluentBehaviorModifier.asFunctionName() + ".matches(<" + matcher + ">)";
	}

    @Override
    public String getterAsString() {
        return getter.toString();
    }

    @Override
    public String describeExpectedValue(Matcher<T> matcher) {
        return String.format("be <%s>", matcher);
    }

}
