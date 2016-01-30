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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static io.github.seleniumquery.by.firstgen.css.pseudoclasses.PseudoClassOnlySupportedThroughIsOrFilterException.pseudoClassNotSupportedWhenUsedDirectly;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:only-of-type
 * 
 * @author acdcjunior
 * @since 0.9.0
 */
class OnlyOfTypePseudoClass implements PseudoClass<ConditionSimpleComponent> {
	
	private static final String ONLY_OF_TYPE_PSEUDO_CLASS_NO_COLON = "only-of-type";

	private final ElementFilter onlyOfTypePseudoClassFilter = new PseudoClassFilter(this);

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ONLY_OF_TYPE_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String tagName = element.getTagName();
		return driver.findElements(By.tagName(tagName)).size() == 1;
	}

	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		pseudoClassNotSupportedWhenUsedDirectly(ONLY_OF_TYPE_PSEUDO_CLASS_NO_COLON);
		
		// we haven't implemented this using XPath yet...
		return new ConditionSimpleComponent(onlyOfTypePseudoClassFilter);
	}
	
}