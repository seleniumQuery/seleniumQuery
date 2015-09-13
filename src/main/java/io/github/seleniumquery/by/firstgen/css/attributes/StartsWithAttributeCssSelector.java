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

package io.github.seleniumquery.by.firstgen.css.attributes;

import io.github.seleniumquery.by.common.AttributeEvaluatorUtils;
import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.firstgen.css.CssConditionalSelector;
import io.github.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import io.github.seleniumquery.utils.SelectorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;

/**
 * [attribute^=stringToStart]
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class StartsWithAttributeCssSelector implements CssConditionalSelector<AttributeCondition, ConditionSimpleComponent> {

	public static final String STARTS_WITH_ATTRIBUTE_SELECTOR_SYMBOL = "^=";

	/**
	 * Currently it is (mistakenly?) mapped to the type {@link org.w3c.css.sac.Condition#SAC_ATTRIBUTE_CONDITION}.
	 * The factory then inspects the actual type and redirects here.
	 * 
	 * This selector is:
	 * [attribute^=stringToStart]
	 * 
	 * CASE INsensitive!
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, ArgumentMap argumentMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String wantedValue = attributeCondition.getValue();
		String actualValue = element.getAttribute(attributeCondition.getLocalName());
		return startsWithIgnoreCase(actualValue, wantedValue);
	}

	@Override
	public ConditionSimpleComponent conditionToXPath(ArgumentMap argumentMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		String wantedValue = SelectorUtils.intoEscapedXPathString(attributeCondition.getValue());
		return new ConditionSimpleComponent("[starts-with(" + attributeName + ", " + wantedValue + ")]");
	}

}