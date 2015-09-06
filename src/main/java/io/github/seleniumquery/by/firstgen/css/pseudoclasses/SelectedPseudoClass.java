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

import io.github.seleniumquery.by.firstgen.filter.ElementFilter;
import io.github.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static io.github.seleniumquery.by.WebElementUtils.isOptionTag;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:selected
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class SelectedPseudoClass implements PseudoClass<ConditionSimpleComponent> {

    public static final ElementFilter SELECTED_FILTER = new PseudoClassFilter(new SelectedPseudoClass());

    // NOTE: This XPath does not work. Sometimes an element is selected WITHOUT having a selected attribute
	public static final String SELECTED_PSEUDO_CONDITION = "self::option and " +
            "(" +
                "@selected or (ancestor::select[not(@multiple) and not(option[@selected])] and position() = 1)" +
            ")";
	private static final String SELECTED_PSEUDO_CONDITIONAL_EXPRESSION = "[" + SELECTED_PSEUDO_CONDITION + "]";

	private static final String SELECTED_PSEUDO_CLASS_NO_COLON = "selected";

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return SELECTED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return isSelected(element);
	}
	
	public boolean isSelected(WebElement element) {
		return isOptionTag(element) && element.isSelected();
	}
	
	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionSimpleComponent(SELECTED_PSEUDO_CONDITIONAL_EXPRESSION);
	}

}