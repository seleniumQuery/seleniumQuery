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

package io.github.seleniumquery.internal.fluentfunctions.evaluators;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.utils.DriverVersionUtils;

public class IsEvaluator implements Evaluator<String> {

	public static IsEvaluator IS_EVALUATOR = new IsEvaluator();

	private IsEvaluator() {	}

	@Override
	public EvaluationReport evaluate(SeleniumQueryObject seleniumQueryObject, String selector) {
        boolean satisfiesConstraints = seleniumQueryObject.is(selector);
        return new EvaluationReport(outerHTML(seleniumQueryObject), satisfiesConstraints);
	}

    private String outerHTML(SeleniumQueryObject seleniumQueryObject) {
        if (DriverVersionUtils.isHtmlUnitWithDisabledJavaScript(seleniumQueryObject)) {
            return "[JavaScript Disabled]";
        }
        return seleniumQueryObject.prop("outerHTML");
    }

    @Override
	public String describeEvaluatorFunction(String selector, FluentBehaviorModifier fluentBehaviorModifier) {
        return "is(\"" + selector + "\")";
	}

    @Override
    public String getterAsString() {
        return "matched element set";
    }

    @Override
    public String describeExpectedValue(String value) {
        return "be \"" + value + "\"";
    }

}
