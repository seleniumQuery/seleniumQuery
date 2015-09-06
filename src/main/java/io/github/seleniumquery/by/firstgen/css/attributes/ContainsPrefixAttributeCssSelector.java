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

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.common.AttributeEvaluatorUtils;
import io.github.seleniumquery.by.firstgen.css.CssConditionalSelector;
import io.github.seleniumquery.by.firstgen.preparser.ArgumentMap;
import io.github.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;

/**
 * [languages|="fr"]
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class ContainsPrefixAttributeCssSelector implements CssConditionalSelector<AttributeCondition, ConditionSimpleComponent> {

	/**
	 * see {@link org.w3c.css.sac.Condition#SAC_BEGIN_HYPHEN_ATTRIBUTE_CONDITION}
	 *
	 * This condition checks if the value is in a hypen-separated list of values in a specified attribute. example:
	 * [languages|="fr"]
	 * 
	 * Case INsensitve
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, ArgumentMap argumentMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String wantedValue = attributeCondition.getValue();
		String actualValue = element.getAttribute(attributeCondition.getLocalName());
		return equalsIgnoreCase(actualValue, wantedValue) || startsWithIgnoreCase(actualValue, wantedValue+'-');
	}

	@Override
	public ConditionSimpleComponent conditionToXPath(ArgumentMap argumentMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String attributeName = AttributeEvaluatorUtils.getXPathAttribute(attributeCondition);
		String wantedValue = SelectorUtils.intoEscapedXPathString(attributeCondition.getValue());
		String wantedValueWithSuffix = SelectorUtils.intoEscapedXPathString(attributeCondition.getValue() + "-");
		return new ConditionSimpleComponent("[(" + attributeName + " = " + wantedValue + " or starts-with(" + attributeName + ", " + wantedValueWithSuffix + "))]");
	}
	
}