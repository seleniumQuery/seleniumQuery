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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * $("selector").prop("property-name");
 * $("selector").prop("property-name", new-property-value);
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class PropFunction {
	
	private PropFunction() {}
	
	public static <T> T prop(SeleniumQueryObject seleniumQueryObject, String propertyName) {
		List<WebElement> elements = seleniumQueryObject.get();
		return prop(seleniumQueryObject.getWebDriver(), elements, propertyName);
	}
	
	public static <T> T prop(WebDriver driver, List<WebElement> elements, String propertyName) {
		if (elements.isEmpty()) {
			return null;
		}
		return prop(driver, elements.get(0), propertyName);
	}
	
	public static <T> T prop(WebDriver driver, WebElement element, String propertyName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		@SuppressWarnings("unchecked")
		T propertyValue = (T) js.executeScript("return arguments[0][arguments[1]]", element, propertyName);
		return propertyValue;
	}
	
	public static SeleniumQueryObject prop(SeleniumQueryObject seleniumQueryObject,
                                           String propertyName, Object value) {
        assertValueIsNotNullAndOfAcceptableType(value);

        JavascriptExecutor js = (JavascriptExecutor) seleniumQueryObject.getWebDriver();
		// "false" is truthy and "0" is truthy, so we dont convert them to string: we keep them false and 0.
		if (value instanceof Boolean || value instanceof Number || value instanceof WebElement) {
			for (WebElement webElement : seleniumQueryObject) {
				js.executeScript("arguments[0][arguments[1]] = arguments[2]", webElement, propertyName, value);
			}
		} else {
			for (WebElement webElement : seleniumQueryObject) {
				js.executeScript("arguments[0][arguments[1]] = arguments[2]", webElement, propertyName, value.toString());
			}
		}
		return seleniumQueryObject;
	}

    private static void assertValueIsNotNullAndOfAcceptableType(Object value) {
        if (value == null || !isValueOfAcceptableType(value)) {
			throw new IllegalArgumentException("The value in $().prop(\"propertyName\", value) must not be null and can only " +
					"be a String, Number, Boolean or WebElement.");
		}
    }

    private static boolean isValueOfAcceptableType(Object value) {
        return value instanceof Boolean || value instanceof String || value instanceof Number ||
				value instanceof WebElement;
    }

}