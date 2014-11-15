package io.github.seleniumquery.functions.jquery.attributes;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.SeleniumQueryObject;

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