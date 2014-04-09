package org.openqa.selenium.seleniumquery.functions;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;

public class AttrFunction {
	
	public static String attr(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String attributeName) {
		if (elements.isEmpty()) {
			return null;
		}
		JavascriptExecutor js = (JavascriptExecutor) seleniumQueryObject.getWebDriver();
		Object propertyValue = js.executeScript("return arguments[0].getAttribute(arguments[1])", elements.get(0), attributeName);
		return (propertyValue != null ? propertyValue.toString() : null);
	}
	
	public static SeleniumQueryObject attr(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements,
												String attributeName, Object value) {
		if (value == null || !(value instanceof Boolean || value instanceof String || value instanceof Number ||
				value instanceof WebElement)) {
			throw new IllegalArgumentException("The value in $().attr(\"attributeName\", value) must not be null and can only " +
					"be a String, Number, Boolean or WebElement.");
		}
		JavascriptExecutor js = (JavascriptExecutor) seleniumQueryObject.getWebDriver();
		for (WebElement webElement : elements) {
			js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", webElement, attributeName, value);
		}
		return seleniumQueryObject;
	}
	
}