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
 * $("selector").attr("attribute-name");
 * $("selector").attr("attribute-name", "new-attribute-value");
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class AttrFunction {
	
	private static final String SELECTED = "selected";
	private static final String CHECKED = "checked";

	public static String attr(SeleniumQueryObject seleniumQueryObject, String attributeName) {
        WebDriver webDriver = seleniumQueryObject.getWebDriver();
        List<WebElement> elements = seleniumQueryObject.get();
        return attr(webDriver, elements, attributeName);
	}
	
	public static String attr(WebDriver driver, List<WebElement> elements, String attributeName) {
		if (elements.isEmpty()) {
			return null;
		}
		WebElement firstElement = elements.get(0);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object attributeValue = js.executeScript("return arguments[0].getAttribute(arguments[1])", firstElement, attributeName);
		
		if (CHECKED.equals(attributeName) || SELECTED.equals(attributeName)) {
			if (attributeValue != null) {
				return attributeName; // returns checked or selected
			}
		}
		return (attributeValue != null ? attributeValue.toString() : null);
	}
	
	public static SeleniumQueryObject attr(SeleniumQueryObject seleniumQueryObject, String attributeName, Object value) {
		List<WebElement> elements = seleniumQueryObject.get();
		if (value == null || !(value instanceof Boolean || value instanceof String || value instanceof Number ||
				value instanceof WebElement)) {
			// calling $().attr("attributeName", undefined); in jQuery has no effect
			// calling $().attr("attributeName", null); in jQuery is the same as .removeAttr("attributeName")
			// therefore, due to this diverse behavior, we forbid the use of Java's null here, as we don't feel there is loss.
			throw new IllegalArgumentException("The value in $().attr(\"attributeName\", value) must not be null and can only " +
					"be a String, Number, Boolean or WebElement. If you want to remove an attribute, use $().removeAttr().");
		}
		Object valueToSet = value;
		if (CHECKED.equals(attributeName) || SELECTED.equals(attributeName)) {
			// we know that 'value' is not null here (as it was verified in the first IF), we set
			// it to checked/selected, as jQuery does
			valueToSet = attributeName; // sets checked or selected
		}
		JavascriptExecutor js = (JavascriptExecutor) seleniumQueryObject.getWebDriver();
		for (WebElement webElement : elements) {
			js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", webElement, attributeName, valueToSet);
		}
		return seleniumQueryObject;
	}
	
}