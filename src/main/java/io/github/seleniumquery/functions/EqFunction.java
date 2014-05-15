package io.github.seleniumquery.functions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.github.seleniumquery.SQLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;

public class EqFunction {
	
	// http://api.jquery.com/eq/
	public static SeleniumQueryObject eq(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, int index) {
		ArrayList<WebElement> eqElementList = new ArrayList<WebElement>();
		
		String selectorAtIndex = seleniumQueryObject.getBy().getSelectorForElementAtPosition(index);
		if (index < 0) {
			if (elements.size() >= -index) {
				eqElementList.add(elements.get(elements.size() + index));
				selectorAtIndex = seleniumQueryObject.getBy().getSelectorForElementAtPosition(elements.size() + index);
			}
		} else {
			if (elements.size() > index) {
				eqElementList.add(elements.get(index));
			}
		}
		
		return SQLocalFactory.getInstance().create(seleniumQueryObject.getWebDriver(), selectorAtIndex,
				eqElementList, seleniumQueryObject);
	}

}