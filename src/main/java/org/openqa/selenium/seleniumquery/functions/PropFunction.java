package org.openqa.selenium.seleniumquery.functions;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;

public class PropFunction {
	
	public static String prop(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String propertyName) {
		JavascriptExecutor js = (JavascriptExecutor) seleniumQueryObject.getWebDriver();
		if (elements.isEmpty()) {
			return null;
		}
		Object propertyValue = js.executeScript("return arguments[0][arguments[1]]", elements.get(0), propertyName);
		return (propertyValue != null ? propertyValue.toString() : null);
	}
	
	public static SeleniumQueryObject prop(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements,
												String propertyName, Object value) {
		JavascriptExecutor js = (JavascriptExecutor) seleniumQueryObject.getWebDriver();
		for (WebElement webElement : elements) {
			js.executeScript("arguments[0][arguments[1]] = arguments[2]", webElement, propertyName, value.toString());
		}
		return seleniumQueryObject;
	}

}