package org.openqa.selenium.seleniumquery.functions;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;

public class RemoveAttrFunction {
	
	public static SeleniumQueryObject removeAttr(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements,
												String attributeNames) {
		JavascriptExecutor js = (JavascriptExecutor) seleniumQueryObject.getWebDriver();
		String[] attributes = attributeNames.split(" ");
		for (WebElement webElement : elements) {
			for (String attributeName : attributes) {
				js.executeScript("arguments[0].removeAttribute(arguments[1])", webElement, attributeName);
			}
		}
		return seleniumQueryObject;
	}
	
}