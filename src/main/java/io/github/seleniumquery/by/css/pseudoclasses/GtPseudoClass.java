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

import io.github.seleniumquery.by.xpath.XPathComponentCompilerService;
import io.github.seleniumquery.by.xpath.component.ConditionToAllComponent;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * :gt(#)
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class GtPseudoClass implements PseudoClass<ConditionToAllComponent> {

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("gt\\(.*\\)");
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String gtIndex = pseudoClassSelector.getPseudoClassContent();
		if (!gtIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :gt() pseudo-class requires an integer but got: " + gtIndex);
		}
		if (gtIndex.charAt(0) == '+') {
			gtIndex = gtIndex.substring(1);
		}
		int index = Integer.valueOf(gtIndex);
		
		return GtPseudoClass.isGt(driver, element, pseudoClassSelector, index);
	}
	
	private static boolean isGt(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector, int wantedIndex) {
		TagComponent compiledSelector = XPathComponentCompilerService.compileSelector(pseudoClassSelector.getArgumentMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compiledSelector.findWebElements(driver);
		if (elements.isEmpty()) {
			return false;
		}
		int actuallyWantedIndex = wantedIndex;
		if (wantedIndex < 0) {
			actuallyWantedIndex = elements.size() + wantedIndex;
		}
		
		if (elements.size() <= actuallyWantedIndex) {
			return false;
		}
		int indexFound = elements.indexOf(element);
		//noinspection SimplifiableIfStatement
		if (indexFound == -1) {
			return false;
		}
		return indexFound > actuallyWantedIndex;
	}
	
	@Override
	public ConditionToAllComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		String eqIndex = pseudoClassSelector.getPseudoClassContent();
		if (!eqIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :gt() pseudo-class requires an integer but got: " + eqIndex);
		}
		if (eqIndex.charAt(0) == '+') {
			eqIndex = eqIndex.substring(1);
		}
		int index = Integer.valueOf(eqIndex);
		
		if (index >= 0) {
			return new ConditionToAllComponent("[position() > " + (index + 1) + "]");
		}
		String xPathExpression = "[position() > (last()-" + (-index - 1) + ")]";
		return new ConditionToAllComponent(xPathExpression);
	}

}