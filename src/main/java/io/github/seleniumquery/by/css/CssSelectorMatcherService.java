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

package io.github.seleniumquery.by.css;

import io.github.seleniumquery.by.preparser.ArgumentMap;
import io.github.seleniumquery.by.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.preparser.CSSSelectorParser;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

public class CssSelectorMatcherService {
	
	public static boolean elementMatchesStringSelector(WebDriver driver, WebElement element, String selector) {
		CSSParsedSelectorList CSSParsedSelectorList = CSSSelectorParser.parseSelector(selector);
		SelectorList selectorList = CSSParsedSelectorList.getSelectorList();
        for (int i = 0; i < selectorList.getLength(); i++) {
			if (CssSelectorMatcherService.elementMatchesSelector(driver, element, CSSParsedSelectorList.getStringMap(), selectorList.item(i))) {
				return true;
			}
		}
        return false;
	}

	public static boolean elementMatchesSelector(WebDriver driver, WebElement element, ArgumentMap stringMap, Selector selector) {
		CssSelector<Selector, TagComponent> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
		return cssSelector.is(driver, element, stringMap, selector);	
	}

}