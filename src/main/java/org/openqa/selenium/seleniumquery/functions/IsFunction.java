package org.openqa.selenium.seleniumquery.functions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;

public class IsFunction {
	
	public static boolean is(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String selector) {
		List<WebElement> selectorElements = seleniumQueryObject.getWebDriver().findElements(SeleniumQueryBy.byEnhancedSelector(selector));
		return selectorElements.containsAll(elements);
	}

}