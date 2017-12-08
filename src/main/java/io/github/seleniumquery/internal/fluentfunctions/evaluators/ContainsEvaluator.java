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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.getters.Getter;

public class ContainsEvaluator implements Evaluator<String> {

    private static final Log LOGGER = LogFactory.getLog(ContainsEvaluator.class);

	private Getter<?> getter;

	public ContainsEvaluator(Getter<?> getter) {
		this.getter = getter;
	}

	@Override
	public EvaluationReport evaluate(SeleniumQueryObject seleniumQueryObject, String valueToContain) {
        LOGGER.debug("Evaluating .contains()...");
		Object propertyGot = getter.get(seleniumQueryObject);
        boolean satisfiesConstraints = propertyGot != null && propertyGot.toString().contains(valueToContain);
        LOGGER.debug("Evaluating .contains()... got " + getter + ": \"" + propertyGot + "\". Wanted: \"" + valueToContain + "\".");
        return new EvaluationReport(propertyGot + "", satisfiesConstraints);
	}

	@Override
	public String describeEvaluatorFunction(String valueToContain, FluentBehaviorModifier fluentBehaviorModifier) {
		return getter.toString() + fluentBehaviorModifier.asFunctionName() + ".contains(\"" + valueToContain + "\")";
	}

    @Override
    public String getterAsString() {
        return getter.toString();
    }

    @Override
    public String describeExpectedValue(String valueToContain) {
        return "contain \"" + valueToContain + "\"";
    }

}
