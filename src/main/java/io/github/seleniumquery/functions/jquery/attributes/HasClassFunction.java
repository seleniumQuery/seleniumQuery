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

package io.github.seleniumquery.functions.jquery.attributes;

import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

/**
 * $("selector").hasClass("a-class-name");
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class HasClassFunction {
	
	private HasClassFunction() {}
	
	public static boolean hasClass(SeleniumQueryObject seleniumQueryObject, String className) {
		List<WebElement> elements = seleniumQueryObject.get();
		if (elements.isEmpty()) {
			return false;
		}
		for (WebElement webElement : elements) {
			boolean hasClass = hasClass(webElement, className);
			if (hasClass) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasClass(WebElement webElement, String className) {
		String classAttributeValue = webElement.getAttribute("class");
		if (classAttributeValue == null) {
			// if the element has no class, only $().hasClass(""); returns true
			return "".equals(className);
		}
		return Arrays.asList(classAttributeValue.split("\\s+")).contains(className);
	}
	
}