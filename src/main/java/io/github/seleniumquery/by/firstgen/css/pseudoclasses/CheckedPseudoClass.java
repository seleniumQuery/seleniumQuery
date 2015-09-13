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

import static io.github.seleniumquery.by.firstgen.css.pseudoclasses.SelectedPseudoClass.SELECTED_PSEUDO_CONDITION;
import static io.github.seleniumquery.utils.WebElementUtils.*;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:checked
 * 
 * #Cross-Driver
 * In HtmlUnitDriver, document.querySelectorAll(":checked") is not consistent, so we should consider it as
 * not supported;
 * In PhantomJSDriver, document.querySelectorAll(":checked") does not work for <option> tags, so we should
 * consider it as not supported as well!
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
 */
public class CheckedPseudoClass implements PseudoClass<ConditionSimpleComponent> {

	private static final String CHECKED_PSEUDO_CLASS_NO_COLON = "checked";

    public static final ElementFilter CHECKED_FILTER = new PseudoClassFilter(new CheckedPseudoClass());

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return CHECKED_PSEUDO_CLASS_NO_COLON.equalsIgnoreCase(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return isChecked(element);
	}

	public boolean isChecked(WebElement element) {
		// #Cross-Driver
		// PhantomJS: When we call element.isSelected() on an element that is not selectable,
		// PhantomJS throws an exception, so we must check the element type before calling isSelected().
		return isCheckableTag(element) && element.isSelected();
	}

	private boolean isCheckableTag(WebElement element) {
		return isOptionTag(element) || isInputRadioTag(element) || isInputCheckboxTag(element);
	}

	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
        // NOTE: This XPath does not work. Sometimes an element is checked WITHOUT having a checked attribute
		return new ConditionSimpleComponent("[" +
				"(" +
				"(self::input and (@type = 'radio' or @type = 'checkbox') and @checked) " +
				"or " +
				"(" + SELECTED_PSEUDO_CONDITION + ")" +
				")" +
				"]");
	}

}