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

package io.github.seleniumquery.by.firstgen.css.pseudoclasses;

import io.github.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This represents the pseudoclasses that check for the type attribute, such as
 * <code>:password</code>, that is equivalent to <code>[type="password"]</code>.
 * 
 * @author acdcjunior
 * @since 0.9.0
 */
abstract class InputTypeAttributePseudoClass implements PseudoClass<ConditionSimpleComponent> {
	
	private String typeAttributeValue;
	
	protected InputTypeAttributePseudoClass(String typeAttributeValue) {
		this.typeAttributeValue = typeAttributeValue;
	}
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return typeAttributeValue.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return "input".equals(element.getTagName()) && typeAttributeValue.equalsIgnoreCase(element.getAttribute("type"));
	}
	
	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionSimpleComponent("[(self::input and @type = '" + typeAttributeValue + "')]");
	}
	
}