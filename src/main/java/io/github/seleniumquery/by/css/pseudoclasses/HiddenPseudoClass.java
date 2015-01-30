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

package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :hidden
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class HiddenPseudoClass implements PseudoClass<ConditionSimpleComponent> {
	
	private static final String HIDDEN_PSEUDO_CLASS_NO_COLON = "hidden";

	public static final HiddenPseudoClass HIDDEN_PSEUDO_CLASS = new HiddenPseudoClass();

    public final ElementFilter hiddenPseudoClassFilter = new PseudoClassFilter(this);

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return HIDDEN_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return !SelectorUtils.isVisible(element);
	}

	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		UnsupportedXPathPseudoClassException.xPathFiltersAreNotImplementedYed(":hidden");

		// we can't use XPath because it can't see the styles affecting the element's classes, which can pretty much
		// turn any element, including <html> itself or <head>, visible.
		return new ConditionSimpleComponent(hiddenPseudoClassFilter);
	}
	
}