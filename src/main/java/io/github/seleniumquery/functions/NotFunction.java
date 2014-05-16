package io.github.seleniumquery.functions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.github.seleniumquery.SQLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SeleniumQueryBy;

public class NotFunction {
	
	public static SeleniumQueryObject not(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String selector) {
		List<WebElement> filteredElements = new ArrayList<WebElement>(elements);
		List<WebElement> elementsToExclude = seleniumQueryObject.getWebDriver().findElements(SeleniumQueryBy.byEnhancedSelector(selector));
		filteredElements.removeAll(elementsToExclude);
		
		return SQLocalFactory.getInstance().createWithInvalidSelector(seleniumQueryObject.getWebDriver(),
				filteredElements, seleniumQueryObject);
	}

}