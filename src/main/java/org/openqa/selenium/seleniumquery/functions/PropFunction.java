package org.openqa.selenium.seleniumquery.functions;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;

public class PropFunction {
	
	@SuppressWarnings("unchecked")
	public static <T> T prop(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String propertyName) {
		if (elements.isEmpty()) {
			return null;
		}
		JavascriptExecutor js = (JavascriptExecutor) seleniumQueryObject.getWebDriver();
		return (T) js.executeScript("return arguments[0][arguments[1]]", elements.get(0), propertyName);
	}
	
	public static SeleniumQueryObject prop(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements,
												String propertyName, Object value) {
		
		if (value == null || !(value instanceof Boolean || value instanceof String || value instanceof Number ||
				value instanceof WebElement)) {
			throw new IllegalArgumentException("The value in $().prop(\"propertyName\", value) must not be null and can only " +
					"be a String, Number, Boolean or WebElement.");
		}
		
		JavascriptExecutor js = (JavascriptExecutor) seleniumQueryObject.getWebDriver();
		// "false" is truthy and "0" is truthy, so we keep them false and 0.
		if (value instanceof Boolean || value instanceof Number || value instanceof WebElement) {
			for (WebElement webElement : elements) {
				js.executeScript("arguments[0][arguments[1]] = arguments[2]", webElement, propertyName, value);
			}
		} else {
			for (WebElement webElement : elements) {
				js.executeScript("arguments[0][arguments[1]] = arguments[2]", webElement, propertyName, value.toString());
			}
		}
		return seleniumQueryObject;
	}

}