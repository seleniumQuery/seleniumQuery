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

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;
import io.github.seleniumquery.internal.fluentfunctions.getters.Getter;

/**
 * Evaluator that tests the elements' values against a regex pattern.
 *
 * @author acdcjunior
 * @since 0.18.0
 */
public class MatchesPatternEvaluator implements Evaluator<Pattern> {

    private static final Log LOGGER = LogFactory.getLog(MatchesPatternEvaluator.class);

	private Getter<?> getter;

	public MatchesPatternEvaluator(Getter<?> getter) {
		this.getter = getter;
	}

	@Override
	public EvaluationReport evaluate(SeleniumQueryObject seleniumQueryObject, Pattern regexPattern) {
        LOGGER.debug("Evaluating .matches(<Pattern>)...");
        String lastValue = getter.get(seleniumQueryObject).toString();
        LOGGER.debug("Evaluating .matches(<Pattern>)... got "+getter+": \""+lastValue+"\". Wanted: <\"" + regexPattern + "\">.");
        boolean satisfiesConstraints = regexPattern.matcher(lastValue).matches();
        return new EvaluationReport(lastValue, satisfiesConstraints);
	}

	@Override
	public String stringFor(Pattern regexPattern, FluentBehaviorModifier fluentBehaviorModifier) {
        return getter.toString() + fluentBehaviorModifier.asFunctionName() + ".matches(\"" + regexPattern + "\")";
	}

	@Override
	public String expectedVsActualMessage(FluentBehaviorModifier fluentBehaviorModifier, Pattern regexPattern, String lastValue,
                                          String actualPrefix) {
        return String.format("expected: <%s %sto match Pattern \"%s\">\nbut: <%s%s was \"%s\">", getter.toString(),
            fluentBehaviorModifier.asString(), regexPattern, actualPrefix, getter.toString(), lastValue);
	}

    @Override
    public String getterAsString() {
        return getter.toString();
    }

    @Override
    public String miolo(Pattern regexPattern) {
        return String.format("match Pattern \"%s\"", regexPattern);
    }

}
