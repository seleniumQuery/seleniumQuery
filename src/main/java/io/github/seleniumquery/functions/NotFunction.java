package io.github.seleniumquery.functions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SeleniumQueryBy;

public class NotFunction {
	
	public static List<WebElement> not(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String selector) {
		List<WebElement> staging = new ArrayList<WebElement>(elements);
		List<WebElement> elementsToExclude = seleniumQueryObject.getWebDriver().findElements(SeleniumQueryBy.byEnhancedSelector(selector));
		staging.removeAll(elementsToExclude);
		return staging;
	}

}