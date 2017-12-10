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

package io.github.seleniumquery.internal.fluentfunctions.evaluators;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;

public interface Evaluator<EVALUATORARG, GETTERTYPE> {

    int MAX_LENGTH_DISPLAY = 100;

    EvaluationReport<GETTERTYPE> evaluate(SeleniumQueryObject seleniumQueryObject, EVALUATORARG evaluatorArgument);

	String describeEvaluatorFunction(EVALUATORARG evaluatorArgument, FluentBehaviorModifier fluentBehaviorModifier);

    default String expectedVsActualMessage(FluentBehaviorModifier fluentBehaviorModifier, EVALUATORARG evaluatorArgument, GETTERTYPE lastValue, String actualPrefix) {
        return String.format(
            "expected: <%s %sto " + describeExpectedValue(evaluatorArgument) + ">\nbut: <%s%s was %s>",
            getterAsString(),
            fluentBehaviorModifier.asString(),
            actualPrefix,
            getterAsString(),
            quoteValue(lastValue)
        );
    }

    String getterAsString();

    String describeExpectedValue(EVALUATORARG evaluatorArgument);

    default String quoteArg(EVALUATORARG valueToBeQuoted) {
        return quoteAny(valueToBeQuoted);
    }

    default String quoteValue(GETTERTYPE valueToBeQuoted) {
        return quoteAny(valueToBeQuoted);
    }

    default String quoteAny(Object valueToBeQuoted) {
        if (valueToBeQuoted == null) {
            return null;
        }
        String valueToBeQuotedAsString = valueToBeQuoted.toString();
        String valueToBeQuotedTrimmed = valueToBeQuotedAsString.length() > MAX_LENGTH_DISPLAY ?
            valueToBeQuotedAsString.substring(0, MAX_LENGTH_DISPLAY - 3) + "..." : valueToBeQuotedAsString;
        if (valueToBeQuoted instanceof String) {
            return '"' + valueToBeQuotedTrimmed + '"';
        } else {
            return  valueToBeQuotedTrimmed;
        }
    }

}
