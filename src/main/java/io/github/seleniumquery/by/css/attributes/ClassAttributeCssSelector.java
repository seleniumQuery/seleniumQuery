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

package io.github.seleniumquery.by.css.attributes;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.CssConditionalSelector;
import io.github.seleniumquery.by.preparser.ArgumentMap;
import io.github.seleniumquery.by.xpath.component.ConditionSimpleComponent;
import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

import java.util.Arrays;

/**
 * .class
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class ClassAttributeCssSelector implements CssConditionalSelector<AttributeCondition, ConditionSimpleComponent> {

	private static final String CLASS_ATTRIBUTE = "class";
	
	/**
	 * see {@link org.w3c.css.sac.Condition#SAC_CLASS_CONDITION}
	 *
	 * This condition checks for a specified class. Example: .example
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, ArgumentMap stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		if (!SelectorUtils.hasAttribute(element, CLASS_ATTRIBUTE)) {
			return false;
		}
		String wantedClassName = attributeCondition.getValue();
		String classAttributeValue = element.getAttribute(CLASS_ATTRIBUTE);
		return Arrays.asList(classAttributeValue.split("\\s+")).contains(wantedClassName);
	}

	@Override
	public ConditionSimpleComponent conditionToXPath(ArgumentMap stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		String wantedClassName = attributeCondition.getValue();
		String unescapedClassName = StringEscapeUtils.unescapeJava(wantedClassName);
		// nothing to do, everyone supports filtering by class
		return new ConditionSimpleComponent("[contains(concat(' ', normalize-space(@class), ' '), ' " + unescapedClassName + " ')]");
	}
	
}