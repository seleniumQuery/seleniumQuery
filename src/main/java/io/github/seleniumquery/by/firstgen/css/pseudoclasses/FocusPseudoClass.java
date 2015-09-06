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

import io.github.seleniumquery.by.common.elementfilter.ElementFilter;
import io.github.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:focus
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class FocusPseudoClass implements PseudoClass<ConditionSimpleComponent> {

	private static final String FOCUS_PSEUDO_CLASS_NO_COLON = "focus";

    public static final ElementFilter FOCUS_FILTER = new PseudoClassFilter(new FocusPseudoClass());

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FOCUS_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		WebElement currentlyActiveElement = driver.switchTo().activeElement();
		return element.equals(currentlyActiveElement);
	}

	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		UnsupportedXPathPseudoClassException.xPathFiltersAreNotImplementedYed(":focus");
		
		// #no-xpath
		return new ConditionSimpleComponent(FOCUS_FILTER);
	}
	
}