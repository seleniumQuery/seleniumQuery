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

import org.openqa.selenium.WebElement;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;

public class IsVisibleEvaluator implements Evaluator<Void, Object> {

	public static IsVisibleEvaluator IS_VISIBLE_EVALUATOR = new IsVisibleEvaluator();

	IsVisibleEvaluator() {	}

	@Override
	public EvaluationReport<Object> evaluate(SeleniumQueryObject seleniumQueryObject, Void selector) {
        int originalSize = seleniumQueryObject.size();
        int visibleSize = seleniumQueryObject.filter(WebElement::isDisplayed).size();
        boolean satisfiesConstraints = originalSize > 0 && originalSize == visibleSize;
        return new EvaluationReport<>(createObtainedValue(originalSize, visibleSize), satisfiesConstraints);
	}

    private Object createObtainedValue(int originalSize, int visibleSize) {
	    // we return this instead of "empty" because "empty" will be rendered between quotes, and we don't want that
        return new Object() {
            @Override
            public String toString() {
                if (originalSize == 0) {
                    return "an empty element set";
                }
                if (visibleSize == 0) {
                    return "a " + originalSize + " element set, with no visible elements";
                }
                int notVisibleSize = originalSize - visibleSize;
                if (notVisibleSize == 1) {
                    return "a " + originalSize + " element set, of which 1 was not visible";
                }
                return "a " + originalSize + " element set, of which " + notVisibleSize + " were not visible";
            }
        };
    }

    @Override
	public String describeEvaluatorFunction(Void selector, FluentBehaviorModifier fluentBehaviorModifier) {
        return "isVisible()";
	}

    @Override
    public String getterAsString() {
        return "matched element set";
    }

    @Override
    public String describeExpectedValue(Void selector) {
        return "be not empty and have only visible elements";
    }

}
