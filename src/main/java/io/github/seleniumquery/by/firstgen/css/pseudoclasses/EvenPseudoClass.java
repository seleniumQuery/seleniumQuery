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

import io.github.seleniumquery.by.firstgen.xpath.XPathComponentCompilerService;
import io.github.seleniumquery.by.firstgen.xpath.component.ConditionToAllComponent;
import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * :even
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class EvenPseudoClass implements PseudoClass<ConditionToAllComponent> {

	private static final String EVEN_PSEUDO_CLASS_NO_COLON = "even";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return EVEN_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		TagComponent compiledSelector = XPathComponentCompilerService.compileSelector(pseudoClassSelector.getArgumentMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compiledSelector.findWebElements(driver);
		return elements.indexOf(element) % 2 == 0;
	}
	
	@Override
	public ConditionToAllComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		// notice that XPath is 1-based and :even is not.
		return new ConditionToAllComponent("[(position() mod 2) = 1]");
	}

}