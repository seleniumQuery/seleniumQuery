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

public class IsEqualToEvaluator<T> implements Evaluator<T> {

	private static final Log LOGGER = LogFactory.getLog(IsEqualToEvaluator.class);

	private Getter<T> getter;

	public IsEqualToEvaluator(Getter<T> getter) {
		this.getter = getter;
	}

	@Override
	public EvaluationReport evaluate(SeleniumQueryObject seleniumQueryObject, T valueToEqual) {
		LOGGER.debug("Evaluating isEqualTo()...");
		T gotValue = getter.get(seleniumQueryObject);
		LOGGER.debug("Evaluating isEqualTo()... got "+getter+": \""+gotValue+"\". Wanted: \""+valueToEqual+"\".");
        boolean satisfiesConstraints = gotValue.equals(valueToEqual);
        return new EvaluationReport(gotValue.toString(), satisfiesConstraints);
	}

	@Override
	public String describeEvaluatorFunction(T valueToEqual, FluentBehaviorModifier fluentBehaviorModifier) {
        String valueAsString = "\"" + valueToEqual + "\"";
        if (valueToEqual instanceof Number) {
            valueAsString = valueToEqual.toString();
        }
        return getter.toString() + fluentBehaviorModifier.asFunctionName() + ".isEqualTo(" + valueAsString + ")";
	}

    @Override
    public String getterAsString() {
        return getter.toString();
    }

    @Override
    public String describeExpectedValue(T valueToEqual) {
        return "equal \"" + valueToEqual + "\"";
    }

}
