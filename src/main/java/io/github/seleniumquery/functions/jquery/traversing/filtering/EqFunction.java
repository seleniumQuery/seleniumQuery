package io.github.seleniumquery.functions.jquery.traversing.filtering;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.github.seleniumquery.ObjectLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;

/**
 * http://api.jquery.com/eq/
 *
 * $("selector").eq(#)
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class EqFunction {
	
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
		
		return ObjectLocalFactory.create(seleniumQueryObject.getWebDriver(), selectorAtIndex,
				eqElementList, seleniumQueryObject);
	}

}