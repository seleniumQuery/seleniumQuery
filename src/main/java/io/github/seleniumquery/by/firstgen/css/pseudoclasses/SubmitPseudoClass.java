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
 * <p>
 * http://api.jquery.com/submit-selector/
 * </p>
 * <b>
 *     Notice that <code>:submit</code> is not consistent accross browsers when the type attribute is not defined.
 *     IE, HtmlUnit acting as IE and even FirefoxDriver (not Firefox itself) "implicitly create" a type attribute
 *     when it doesn't exist! (The jQuery docs also talks about this issue!)
 * </b>
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
class SubmitPseudoClass implements PseudoClass<ConditionSimpleComponent> {

	private static final String SUBMIT = "submit";
	private static final String INPUT = "input";
	private static final String BUTTON = "button";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return SUBMIT.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return inputWithTypeSubmit(element) || buttonWithTypeSubmitOrWithoutType(driver, element);
	}

	private boolean inputWithTypeSubmit(WebElement element) {
		return INPUT.equals(element.getTagName()) && SUBMIT.equalsIgnoreCase(element.getAttribute("type"));
	}

	private boolean buttonWithTypeSubmitOrWithoutType(WebDriver driver, WebElement element) {
		boolean isButtonTag = BUTTON.equals(element.getTagName());
		if (!isButtonTag) {
			return false;
		}

        boolean isTypeSubmit = SUBMIT.equalsIgnoreCase(element.getAttribute("type"));
        boolean isTypeNull = element.getAttribute("type") == null;

        return isTypeSubmit || isTypeNull;
	}

	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionSimpleComponent("[("
				+ "( local-name() = 'input' and @type = 'submit' ) or "
				+ "( local-name() = 'button' and (@type = 'submit' or not(@type)) )"
				+ ")]");
	}
	
}