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

package io.github.seleniumquery.by.css.conditionals;

import io.github.seleniumquery.by.css.CssConditionalSelector;
import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorMatcherService;
import io.github.seleniumquery.by.preparser.ArgumentMap;
import io.github.seleniumquery.by.xpath.XPathComponentCompilerService;
import io.github.seleniumquery.by.xpath.component.ConditionComponent;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;


public class ConditionalCssSelector implements CssSelector<ConditionalSelector, TagComponent> {

    private final ConditionalCssSelectorFactory conditionalCssSelectorFactory = new ConditionalCssSelectorFactory(this);

    @Override
	public boolean is(WebDriver driver, WebElement element, ArgumentMap argumentMap, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();
		return CssSelectorMatcherService.elementMatchesSelector(driver, element, argumentMap, simpleSelector)
				&& isCondition(driver, element, argumentMap, simpleSelector, condition);
	}
	
	@Override
	public TagComponent toXPath(ArgumentMap argumentMap, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();
		TagComponent tagComponent = XPathComponentCompilerService.compileSelector(argumentMap, simpleSelector);
		ConditionComponent compiledCondition = conditionToXPath(argumentMap, simpleSelector, condition);
		return tagComponent.cloneAndCombineTo(compiledCondition);
	}
	
	/**
	 * Gets the given condition's CssSelector and tests if the element matches it. 
	 */
	boolean isCondition(WebDriver driver, WebElement element, ArgumentMap argumentMap, Selector simpleSelector, Condition condition) {
		@SuppressWarnings("unchecked")
		CssConditionalSelector<Condition, ConditionComponent> evaluator = (CssConditionalSelector<Condition, ConditionComponent>) conditionalCssSelectorFactory.getSelector(condition);
		return evaluator.isCondition(driver, element, argumentMap, simpleSelector, condition);
	}

	ConditionComponent conditionToXPath(ArgumentMap argumentMap, Selector simpleSelector, Condition condition) {
		@SuppressWarnings("unchecked")
		CssConditionalSelector<Condition, ConditionComponent> evaluator = (CssConditionalSelector<Condition, ConditionComponent>) conditionalCssSelectorFactory.getSelector(condition);
		return evaluator.conditionToXPath(argumentMap, simpleSelector, condition);
	}

}