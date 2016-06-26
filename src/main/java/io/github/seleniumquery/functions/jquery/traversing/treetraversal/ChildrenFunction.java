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

package io.github.seleniumquery.functions.jquery.traversing.treetraversal;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.firstgen.css.CssSelectorMatcherService;
import io.github.seleniumquery.internal.SqObjectFactory;
import io.github.seleniumquery.utils.SelectorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * $("selector").children()
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class ChildrenFunction {
	
	private ChildrenFunction() {}

	public static SeleniumQueryObject children(SeleniumQueryObject caller) {
		List<WebElement> elements = caller.get();
		List<WebElement> children = getChildren(elements);
		return SqObjectFactory.instance().createWithInvalidSelector(caller.getWebDriver(), children, caller);
	}
	
	public static SeleniumQueryObject children(SeleniumQueryObject caller, String selector) {
		List<WebElement> elements = caller.get();
		WebDriver webDriver = caller.getWebDriver();
		List<WebElement> children = getChildren(elements);
		for (Iterator<WebElement> iterator = children.iterator(); iterator.hasNext();) {
			WebElement child = iterator.next();
			if (!CssSelectorMatcherService.elementMatchesStringSelector(webDriver, child, selector)) {
				iterator.remove();
			}
		}
		return SqObjectFactory.instance().createWithInvalidSelector(webDriver, children, caller);
	}
	
	private static List<WebElement> getChildren(List<WebElement> elements) {
		List<WebElement> children = new LinkedList<>();
		for (WebElement element : elements) {
			children.addAll(SelectorUtils.getDirectChildren(element));
		}
		return new ArrayList<>(children);
	}

}