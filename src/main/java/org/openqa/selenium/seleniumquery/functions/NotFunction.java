package org.openqa.selenium.seleniumquery.functions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;

public class NotFunction {
	
	public static SeleniumQueryObject not(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String selector) {
		List<WebElement> thisElements = new ArrayList<WebElement>(elements);
		List<WebElement> elementsToExclude = seleniumQueryObject.getWebDriver().findElements(SeleniumQueryBy.byEnhancedSelector(selector));
		thisElements.removeAll(elementsToExclude);
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), thisElements);
	}

}