package io.github.seleniumquery.functions;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.SeleniumQueryObject;

public class PropFunction {
	
	public static <T> T prop(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String propertyName) {
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
	
	public static SeleniumQueryObject prop(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements,
												String propertyName, Object value) {
		
		if (value == null || !(value instanceof Boolean || value instanceof String || value instanceof Number ||
				value instanceof WebElement)) {
			throw new IllegalArgumentException("The value in $().prop(\"propertyName\", value) must not be null and can only " +
					"be a String, Number, Boolean or WebElement.");
		}
		
		JavascriptExecutor js = (JavascriptExecutor) seleniumQueryObject.getWebDriver();
		// "false" is truthy and "0" is truthy, so we dont convert them to string: we keep them false and 0.
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