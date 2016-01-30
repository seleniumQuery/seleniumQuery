/*
 * Copyright (c) 2016 seleniumQuery authors
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

import static io.github.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassOnlySupportedThroughIsOrFilterException.pseudoClassNotSupportedWhenUsedDirectly;

/**
 * :tabbable elements are all those :focusable, except those that have a negative tabindex.
 * 
 * From http://api.jqueryui.com/tabbable-selector/: "Elements with a negative tab index are :focusable, but not :tabbable."
 * 
 * @author acdcjunior
 * @since 0.9.0
 */
class TabbablePseudoClass implements PseudoClass<ConditionSimpleComponent> {
	
	private static final String TABBABLE_PSEUDO_CLASS_NO_COLON = "tabbable";

	private final FocusablePseudoClass focusable = new FocusablePseudoClass();
    private final ElementFilter tabbablePseudoClassFilter = new PseudoClassFilter(this);

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return TABBABLE_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		boolean isFocusable = focusable.isPseudoClass(driver, element, pseudoClassSelector);
		if (!isFocusable) {
			return false;
		}
		// at this point, it is focusable!
		String tabindex = element.getAttribute("tabindex");
		if (tabindex == null) {
			return true;
		}
		// at this point, there is a tabindex
		boolean tabindexIsNegativeInteger = tabindex.matches("\\s*-\\d+\\s*");
		return !tabindexIsNegativeInteger;
	}

	// see :focusable. change there before here, this selector is highly dependable on :focusable as it is just a small change to it
	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		pseudoClassNotSupportedWhenUsedDirectly(TABBABLE_PSEUDO_CLASS_NO_COLON);
		
		// :tabbable is not fully XPath supported (if the 'display:none' is in a CSS class, it won't know)!!!
		return new ConditionSimpleComponent("[(" + FocusablePseudoClass.FOCUSABLE_XPATH + " and (not(@tabindex) or @tabindex > -1))]", tabbablePseudoClassFilter);
	}
	
}