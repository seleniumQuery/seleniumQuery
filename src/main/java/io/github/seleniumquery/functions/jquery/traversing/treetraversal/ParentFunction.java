package io.github.seleniumquery.functions.jquery.traversing.treetraversal;

import io.github.seleniumquery.SQLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.selector.SelectorUtils;

import org.openqa.selenium.WebElement;

/**
 * $("selector").parent()
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class ParentFunction {

	public static SeleniumQueryObject parent(SeleniumQueryObject caller, WebElement element) {
		WebElement parentElement = SelectorUtils.parent(element);
		return SQLocalFactory.createWithInvalidSelector(caller.getWebDriver(), parentElement, caller);
	}

}