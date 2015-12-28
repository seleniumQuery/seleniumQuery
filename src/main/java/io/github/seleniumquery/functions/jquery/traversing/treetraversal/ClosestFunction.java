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

package io.github.seleniumquery.functions.jquery.traversing.treetraversal;

import io.github.seleniumquery.InternalSeleniumQueryObjectFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.firstgen.css.CssSelectorMatcherService;
import io.github.seleniumquery.utils.SelectorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * $("selector").closest("selector")
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class ClosestFunction {

	public static SeleniumQueryObject closest(SeleniumQueryObject caller, String selector) {
		List<WebElement> elements = caller.get();
		WebDriver driver = caller.getWebDriver();
		
		List<WebElement> closests = new ArrayList<>(elements.size());
		for (WebElement element : elements) {
			WebElement closestElement = closest(driver, element, selector);
			if (closestElement != null) {
				closests.add(closestElement);
			}
		}

		return InternalSeleniumQueryObjectFactory.instance().createWithInvalidSelector(caller.getWebDriver(), closests, caller);
	}

	public static WebElement closest(WebDriver driver, WebElement element, String selector) {
		WebElement ancestorOrSelf = element; // begins by evaluating the element itself
		while (ancestorOrSelf != null) {
			if (CssSelectorMatcherService.elementMatchesStringSelector(driver, ancestorOrSelf, selector)) {
				return ancestorOrSelf;
			}
			ancestorOrSelf = SelectorUtils.parent(ancestorOrSelf);
		}
		// if ancestorOrSelf is null, it reached document root,
		// so no ancestor matching the selector was found
		return null;
	}

}