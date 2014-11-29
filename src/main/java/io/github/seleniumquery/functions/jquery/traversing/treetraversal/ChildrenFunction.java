package io.github.seleniumquery.functions.jquery.traversing.treetraversal;

import io.github.seleniumquery.ObjectLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.CssSelectorMatcherService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * $("selector").children()
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class ChildrenFunction {

	public static SeleniumQueryObject children(SeleniumQueryObject caller, List<WebElement> elements) {
		List<WebElement> children = getChildren(elements);
		return ObjectLocalFactory.createWithInvalidSelector(caller.getWebDriver(), children, caller);
	}
	
	public static SeleniumQueryObject children(SeleniumQueryObject caller, List<WebElement> elements, String selector) {
		WebDriver webDriver = caller.getWebDriver();
		List<WebElement> children = getChildren(elements);
		for (Iterator<WebElement> iterator = children.iterator(); iterator.hasNext();) {
			WebElement child = iterator.next();
			if (!CssSelectorMatcherService.elementMatchesStringSelector(webDriver, child, selector)) {
				iterator.remove();
			}
		}
		return ObjectLocalFactory.createWithInvalidSelector(webDriver, children, caller);
	}
	
	private static List<WebElement> getChildren(List<WebElement> elements) {
		List<WebElement> children = new LinkedList<WebElement>();
		for (WebElement element : elements) {
			children.addAll(SelectorUtils.getDirectChildren(element));
		}
		return new ArrayList<WebElement>(children);
	}

}