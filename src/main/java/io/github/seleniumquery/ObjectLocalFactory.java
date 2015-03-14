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

package io.github.seleniumquery;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * This factory builds {@link io.github.seleniumquery.SeleniumQueryObject}s. Necessary because all
 * constructors in that class have protected visibility (we don't want them public).
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class ObjectLocalFactory {
	
	private static final SeleniumQueryObject NO_PREVIOUS = null;
	
	public static SeleniumQueryObject create(WebDriver driver, String selector, List<WebElement> elements, SeleniumQueryObject previous) {
		return new SeleniumQueryObject(driver, selector, elements, previous);
	}

	public static SeleniumQueryObject createWithInvalidSelector(WebDriver driver, WebElement element, SeleniumQueryObject previous) {
		return createWithInvalidSelector(driver, asList(element), previous);
	}

	public static SeleniumQueryObject createWithInvalidSelector(WebDriver driver, List<WebElement> elements, SeleniumQueryObject previous) {
		return new SeleniumQueryObject(driver, elements, previous);
	}

	public static SeleniumQueryObject createWithInvalidSelectorAndNoPrevious(WebDriver driver, List<WebElement> elements) {
		return createWithInvalidSelector(driver, elements, NO_PREVIOUS);
	}
	
	public static SeleniumQueryObject createWithInvalidSelectorAndNoPrevious(WebDriver driver, WebElement... elements) {
		return createWithInvalidSelectorAndNoPrevious(driver, asList(elements));
	}

	static SeleniumQueryObject createWithValidSelectorAndNoPrevious(WebDriver driver, String selector) {
		return new SeleniumQueryObject(driver, selector);
	}

}