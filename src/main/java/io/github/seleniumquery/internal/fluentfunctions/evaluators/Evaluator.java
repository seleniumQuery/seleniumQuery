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

public interface Evaluator<T> {

    int MAX_LENGTH_DISPLAY = 100;

    EvaluationReport evaluate(SeleniumQueryObject seleniumQueryObject, T valueArgument);

	String describeEvaluatorFunction(T valueArgument, FluentBehaviorModifier fluentBehaviorModifier);

    default String expectedVsActualMessage(FluentBehaviorModifier fluentBehaviorModifier, T value, String lastValue, String actualPrefix) {
        return String.format(
            "expected: <%s %sto " + describeExpectedValue(value) + ">\nbut: <%s%s was %s>",
            getterAsString(),
            fluentBehaviorModifier.asString(),
            actualPrefix,
            getterAsString(),
            quoteValue(value, lastValue)
        );
    }

    String getterAsString();

    String describeExpectedValue(T value);

    default String quoteValue(T typeReferenceValue) {
        return quoteValue(typeReferenceValue, typeReferenceValue != null ? typeReferenceValue.toString() : null);
    }

    default String quoteValue(T typeReferenceValue, String valueToBeQuoted) {
        String valueToBeQuotedTrimmed = valueToBeQuoted == null ? "<null>" :
        valueToBeQuoted.length() > MAX_LENGTH_DISPLAY ? valueToBeQuoted.substring(0, MAX_LENGTH_DISPLAY - 3) + "..." : valueToBeQuoted;
        if (typeReferenceValue instanceof String) {
            return '"' + valueToBeQuotedTrimmed + '"';
        } else {
            return  valueToBeQuotedTrimmed;
        }
    }

}
