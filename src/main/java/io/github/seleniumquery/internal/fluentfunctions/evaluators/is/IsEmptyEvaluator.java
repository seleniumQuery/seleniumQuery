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

public class IsEmptyEvaluator implements Evaluator<Void, Object> {

	public static IsEmptyEvaluator IS_EMPTY_EVALUATOR = new IsEmptyEvaluator();

	private IsEmptyEvaluator() {	}

	@Override
	public EvaluationReport<Object> evaluate(SeleniumQueryObject seleniumQueryObject, Void selector) {
        boolean satisfiesConstraints = seleniumQueryObject.size() == 0;
        return new EvaluationReport<>(createObtainedValue(seleniumQueryObject.size()), satisfiesConstraints);
	}

    private Object createObtainedValue(int size) {
	    // we return an Object instead of a String because a String would be rendered between quotes, and we don't want that
        return new Object() {
            @Override
            public String toString() {
                if (size == 1) {
                    return "not empty (had one element)";
                } else {
                    return "not empty (had " + size + " elements)";
                }
            }
        };
    }

    @Override
	public String describeEvaluatorFunction(Void selector, FluentBehaviorModifier fluentBehaviorModifier) {
        return "isEmpty()";
	}

    @Override
    public String getterAsString() {
        return "matched element set";
    }

    @Override
    public String describeExpectedValue(Void selector) {
        return "be empty";
    }

}
