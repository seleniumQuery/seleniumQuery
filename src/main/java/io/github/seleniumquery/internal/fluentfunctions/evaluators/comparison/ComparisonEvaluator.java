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

package io.github.seleniumquery.internal.fluentfunctions.evaluators.comparison;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.EvaluationReport;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;
import io.github.seleniumquery.internal.fluentfunctions.getters.Getter;

/**
 * Superclass (template method) for all evaluators that compare elements' contents and arguments as numbers.
 *
 * @author acdcjunior
 * @since 0.13.0
 */
abstract class ComparisonEvaluator<GETTERTYPE> implements Evaluator<Number, GETTERTYPE> {

    private static final Log LOGGER = LogFactory.getLog(ComparisonEvaluator.class);

	private Getter<GETTERTYPE> getter;

	ComparisonEvaluator(Getter<GETTERTYPE> getter) {
		this.getter = getter;
	}

    @Override
    public String getterAsString() {
        return getter.toString();
    }

	@Override
	public EvaluationReport<GETTERTYPE> evaluate(SeleniumQueryObject seleniumQueryObject, Number valueToCompare) {
        LOGGER.debug("Evaluating ." + getFunctionName() + "()...");
        GETTERTYPE actualValue = getter.get(seleniumQueryObject);
        LOGGER.debug("Evaluating ." + getFunctionName() + "()... got " + getter + ": " + quoteValue(actualValue) + ". Wanted: " + quoteArg(valueToCompare) + ".");
        boolean satisfiesConstraints = satisfiesConstraints(valueToCompare, actualValue);
        return new EvaluationReport<>(actualValue, satisfiesConstraints);
	}

    private boolean satisfiesConstraints(Number valueToCompare, GETTERTYPE elementValue) {
	    if (elementValue == null) {
	        return false;
        }
        BigDecimal numberToCompare = ComparisonEvaluatorUtils.parseNumber(valueToCompare);
        try {
            BigDecimal elementValueAsNumber = ComparisonEvaluatorUtils.parseNumber(elementValue);

            return compare(elementValueAsNumber, numberToCompare);
        } catch (IllegalArgumentException e) {
            LOGGER.debug("Invalid value when trying to compare as number.\nElement value: " + quoteValue(elementValue), e);
            return false;
        }
    }

    protected abstract boolean compare(BigDecimal elementValueAsNumber, BigDecimal numberToCompare);

	@Override
    public String describeEvaluatorFunction(Number valueToCompare, FluentBehaviorModifier fluentBehaviorModifier) {
        return String.format("%s.%s(%s)", getter.toString() + fluentBehaviorModifier.asFunctionName(), getFunctionName(), valueToCompare);
    }

	protected abstract String getFunctionName();

}
