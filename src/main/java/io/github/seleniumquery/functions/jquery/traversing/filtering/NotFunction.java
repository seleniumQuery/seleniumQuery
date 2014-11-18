package io.github.seleniumquery.functions.jquery.traversing.filtering;

import io.github.seleniumquery.SQLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SeleniumQueryBy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * $("selector").not("selector")
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class NotFunction {
	
	public static SeleniumQueryObject not(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String selector) {
		List<WebElement> filteredElements = new ArrayList<WebElement>(elements);
		
		List<WebElement> elementsToExclude = seleniumQueryObject.getWebDriver().findElements(SeleniumQueryBy.byEnhancedSelector(selector));
		filteredElements.removeAll(elementsToExclude);
		
		return SQLocalFactory.createWithInvalidSelector(seleniumQueryObject.getWebDriver(), filteredElements, seleniumQueryObject);
	}

}