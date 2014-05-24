package io.github.seleniumquery.functions;

import io.github.seleniumquery.SQLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.selector.SelectorUtils;

import org.openqa.selenium.WebElement;

public class ParentFunction {

	public static SeleniumQueryObject parent(SeleniumQueryObject caller, WebElement element) {
		WebElement parentElement = SelectorUtils.parent(element);
		return SQLocalFactory.getInstance().createWithInvalidSelector(caller.getWebDriver(), parentElement, caller);
	}

}