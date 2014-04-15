package org.openqa.selenium.seleniumquery.functions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;

public class FirstFunction {
	
	public static SeleniumQueryObject first(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
		ArrayList<WebElement> firstElementList = new ArrayList<WebElement>();
		if (!elements.isEmpty()) {
			firstElementList.add(elements.get(0));
		}
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), seleniumQueryObject.getBy().getSelectorForElementAtPosition(0), firstElementList);
	}

}