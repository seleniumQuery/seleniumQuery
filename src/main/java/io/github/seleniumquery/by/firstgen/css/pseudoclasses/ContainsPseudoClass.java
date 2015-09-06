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

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.firstgen.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :contains()
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class ContainsPseudoClass implements PseudoClass<ConditionSimpleComponent> {
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("contains\\(.*\\)");
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String textToContain = pseudoClassSelector.getPseudoClassContent();
		textToContain = SelectorUtils.unescapeString(textToContain);
		return element.getText().contains(textToContain);
	}
	
	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		String textToContain = pseudoClassSelector.getPseudoClassContent();
		textToContain = SelectorUtils.unescapeString(textToContain);
		String wantedTextToContain = SelectorUtils.intoEscapedXPathString(textToContain);
		return new ConditionSimpleComponent("[contains(string(.), " + wantedTextToContain + ")]");
	}

}