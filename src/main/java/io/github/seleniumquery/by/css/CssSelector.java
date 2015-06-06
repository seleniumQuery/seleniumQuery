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
import io.github.seleniumquery.by.xpath.component.XPathComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * A selector, capable of being transformed into {@link XPathComponent},
 * or testing if a given {@link WebElement} matched it.
 */
public interface CssSelector<T, C extends XPathComponent> {

	/**
	 * Tests if the given element, under the given driver, matches the selector.
	 * 
	 * @param stringMap map of strings that were extracted from the selector 
	 */
	boolean is(WebDriver driver, WebElement element, ArgumentMap stringMap, T selector);

	C toXPath(ArgumentMap stringMap, T selector);
	
}