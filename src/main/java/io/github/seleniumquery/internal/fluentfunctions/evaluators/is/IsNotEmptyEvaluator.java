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

package io.github.seleniumquery.internal.fluentfunctions.evaluators.is;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;

public class IsNotEmptyEvaluator implements Evaluator<Void, Object> {

	public static IsNotEmptyEvaluator IS_NOT_EMPTY_EVALUATOR = new IsNotEmptyEvaluator();

	private IsNotEmptyEvaluator() {	}

	@Override
	public EvaluationReport<Object> evaluate(SeleniumQueryObject seleniumQueryObject, Void selector) {
        boolean satisfiesConstraints = seleniumQueryObject.size() > 0;
        return new EvaluationReport<>(createObtainedValue(), satisfiesConstraints);
	}

    private Object createObtainedValue() {
	    // we return this instead of "empty" because "empty" will be rendered between quotes, and we don't want that
        return new Object() {
            @Override
            public String toString() {
                return "empty";
            }
        };
    }

    @Override
	public String describeEvaluatorFunction(Void selector, FluentBehaviorModifier fluentBehaviorModifier) {
        return "isNotEmpty()";
	}

    @Override
    public String getterAsString() {
        return "matched element set";
    }

    @Override
    public String describeExpectedValue(Void selector) {
        return "be not empty";
    }

}
