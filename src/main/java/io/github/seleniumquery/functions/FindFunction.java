package io.github.seleniumquery.functions;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebElement;
import io.github.seleniumquery.SeleniumQueryLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SeleniumQueryBy;

public class FindFunction {

	public static SeleniumQueryObject find(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements, String selector) {
		List<WebElement> allElementsBelow = new LinkedList<WebElement>();
		for (WebElement webElement : elements) {
			final List<WebElement> elementsBelowThisElement = webElement.findElements(SeleniumQueryBy.byEnhancedSelector(selector));
			allElementsBelow.addAll(elementsBelowThisElement);
		}
		return SeleniumQueryLocalFactory.getInstance().create(seleniumQueryObject, allElementsBelow);
	}

}