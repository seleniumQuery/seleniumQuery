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

import org.apache.commons.lang3.NotImplementedException;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;

public interface Evaluator<T> {

    EvaluationReport evaluate(SeleniumQueryObject seleniumQueryObject, T valueArgument);

	String stringFor(T valueArgument, FluentBehaviorModifier fluentBehaviorModifier);

    default String expectedVsActualMessage(FluentBehaviorModifier fluentBehaviorModifier, T value, String lastValue, String actualPrefix) {
        return String.format(
            "expected: <%s %sto " + miolo(value) + ">\nbut: <%s%s was \"%s\">",
            getterAsString(),
            fluentBehaviorModifier.asString(),
            actualPrefix,
            getterAsString(),
            lastValue
        );
    }

    String getterAsString();

    default String miolo(T value) {
        throw new NotImplementedException("implement it!");
    }

}
