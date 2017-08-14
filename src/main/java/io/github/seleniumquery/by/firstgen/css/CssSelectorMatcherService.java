/*
 * Copyright (c) 2017 seleniumQuery authors
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

package io.github.seleniumquery.by.firstgen.css;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.common.preparser.CssSelectorParser;
import io.github.seleniumquery.by.common.preparser.w3cwithmap.W3cCssSelectorListWithMap;
import io.github.seleniumquery.by.common.preparser.w3cwithmap.W3cCssSelectorWithMap;
import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;

public class CssSelectorMatcherService {

	private CssSelectorMatcherService() {}

	public static boolean elementMatchesStringSelector(WebDriver driver, WebElement element, String selector) {
		W3cCssSelectorListWithMap cssParsedSelectors = CssSelectorParser.parseSelector(selector);
		for (W3cCssSelectorWithMap w3cCssSelectorWithMap : cssParsedSelectors) {
            if (elementMatchesSelector(driver, element, w3cCssSelectorWithMap)) {
                return true;
            }
		}
        return false;
	}

    public static boolean elementMatchesSelector(WebDriver driver, WebElement element, W3cCssSelectorWithMap w3cCssSelectorWithMap) {
        return CssSelectorMatcherService.elementMatchesSelector(driver, element, w3cCssSelectorWithMap.getArgumentMap(), w3cCssSelectorWithMap.getSelector());
    }

    public static boolean elementMatchesSelector(WebDriver driver, WebElement element, ArgumentMap argumentMap, Selector selector) {
		CssSelector<Selector, TagComponent> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
		return cssSelector.is(driver, element, argumentMap, selector);
	}

}
