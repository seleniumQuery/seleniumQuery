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

package io.github.seleniumquery.wait.evaluators.comparison;

import io.github.seleniumquery.wait.FluentBehaviorModifier;
import io.github.seleniumquery.wait.evaluators.Evaluator;
import io.github.seleniumquery.wait.getters.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.List;

/**
 * Superclass (template method) for all evaluators that compare elements' contents and arguments as numbers.
 *
 * @author acdcjunior
 * @since 0.13.0
 */
abstract class ComparisonEvaluator implements Evaluator<Number> {

	private Getter<?> getter;

    private static final Log LOGGER = LogFactory.getLog(ComparisonEvaluator.class);

	ComparisonEvaluator(Getter<?> getter) {
		this.getter = getter;
	}

	@Override
	public boolean evaluate(WebDriver driver, List<WebElement> elements, Number valueToCompare) {
        BigDecimal numberToCompare = ComparisonEvaluatorUtils.parseNumber(valueToCompare);

        Object elementValue = getter.get(driver, elements);
		try {
			BigDecimal elementValueAsNumber = ComparisonEvaluatorUtils.parseNumber(elementValue);

			return compare(elementValueAsNumber, numberToCompare);
		} catch (IllegalArgumentException e) {
            LOGGER.debug("Invalid value when trying to compare as number.\nElement value: "+elementValue, e);
			return false;
		}
	}

	protected abstract boolean compare(BigDecimal elementValueAsNumber, BigDecimal numberToCompare);

	@Override
    public String stringFor(Number valueToCompare, FluentBehaviorModifier fluentBehaviorModifier) {
        return String.format("%s.%s(%s)", getter.toString() + fluentBehaviorModifier, getFunctionName(), valueToCompare);
    }

	protected abstract String getFunctionName();

}
