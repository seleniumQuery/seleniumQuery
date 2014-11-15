package io.github.seleniumquery.functions.jquery.attributes;

import io.github.seleniumquery.SeleniumQueryObject;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;

public class HasClassFunction {
	
	public static boolean hasClass(@SuppressWarnings("unused") SeleniumQueryObject seleniumQueryObject,
										List<WebElement> elements, String className) {
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