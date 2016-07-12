/*
 * Copyright (c) 2015 seleniumQuery authors
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

package io.github.seleniumquery.by.firstgen.css.conditionals;

import com.steadystate.css.parser.selectors.ConditionalSelectorImpl;
import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.firstgen.css.CssConditionalSelector;
import io.github.seleniumquery.by.firstgen.xpath.component.ConditionComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

/**
 * E.firstCondition.secondCondition
 *
 * see {@link Condition#SAC_AND_CONDITION}
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
class AndConditionalCssSelector implements CssConditionalSelector<CombinatorCondition, ConditionComponent> {

	private ConditionalCssSelector conditionalEvaluator;

	AndConditionalCssSelector(ConditionalCssSelector conditionalEvaluator) {
		this.conditionalEvaluator = conditionalEvaluator;
	}

	@Override
	public boolean isCondition(WebDriver driver, WebElement element, ArgumentMap argumentMap, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
																					(SimpleSelector) selectorUpToThisPoint,
																						combinatorCondition.getFirstCondition());

		return conditionalEvaluator.isCondition(driver, element, argumentMap, selectorUpToThisPoint, combinatorCondition.getFirstCondition())
		    && conditionalEvaluator.isCondition(driver, element, argumentMap, selectorUpToThisPointPlusFirstCondition, combinatorCondition.getSecondCondition());
	}

	@Override
	public ConditionComponent conditionToXPath(ArgumentMap argumentMap, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
		    (SimpleSelector) selectorUpToThisPoint,
            combinatorCondition.getFirstCondition()
        );
		ConditionComponent firstCondition = conditionalEvaluator.conditionToXPath(
		    argumentMap, selectorUpToThisPoint, combinatorCondition.getFirstCondition()
        );
		ConditionComponent secondCondition = conditionalEvaluator.conditionToXPath(
		    argumentMap, selectorUpToThisPointPlusFirstCondition, combinatorCondition.getSecondCondition()
        );
		return firstCondition.cloneAndCombineTo(secondCondition);
	}

}
