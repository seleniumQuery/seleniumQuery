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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

class PseudoClassFilter implements ElementFilter {
	
	public static final Map<String, String> STRING_MAP_NOT_USED = null;
	public static final Selector SELECTOR_NOT_USED = null;
	public static final String PSEUDO_CLASS_VALUE_NOT_USED = null;
	
	private PseudoClass pseudoClass;
	
	private PseudoClassSelector pseudoClassSelector;
	
	public PseudoClassFilter(PseudoClass pseudoClass) {
		this.pseudoClass = pseudoClass;
	}
	
	public PseudoClassFilter(PseudoClass pseudoClass, PseudoClassSelector pseudoClassSelector) {
		this.pseudoClass = pseudoClass;
		this.pseudoClassSelector = pseudoClassSelector;
	}
	
	@Override
	public List<WebElement> filterElements(WebDriver driver, List<WebElement> elements) {
		for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
			WebElement webElement = iterator.next();
			if (!pseudoClass.isPseudoClass(driver, webElement, this.pseudoClassSelector)) {
				iterator.remove();
			}
		}
		return elements;
	}

}