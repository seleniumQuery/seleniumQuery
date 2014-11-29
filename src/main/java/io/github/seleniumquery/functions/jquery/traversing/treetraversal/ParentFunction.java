package io.github.seleniumquery.functions.jquery.traversing.treetraversal;

import io.github.seleniumquery.ObjectLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SelectorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * $("selector").parent()
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class ParentFunction {

	public static SeleniumQueryObject parent(SeleniumQueryObject caller, List<WebElement> elements) {
		return parent(caller, elements, null);
	}

	public static SeleniumQueryObject parent(SeleniumQueryObject caller, List<WebElement> elements, String selector) {
		Set<WebElement> alreadyInsertedParents = new HashSet<WebElement>();
		WebDriver callerWebDriver = caller.getWebDriver();

		List<WebElement> parents = new ArrayList<WebElement>(elements.size());
		for (WebElement element : elements) {
			WebElement parentElement = SelectorUtils.parent(element);
			if (parentElement != null && !alreadyInsertedParents.contains(parentElement) && parentMatchesSelector(callerWebDriver, selector, parentElement)) {
				parents.add(parentElement);
				alreadyInsertedParents.add(parentElement);
			}
		}
		return ObjectLocalFactory.createWithInvalidSelector(callerWebDriver, parents, caller);
	}

	private static boolean parentMatchesSelector(WebDriver callerWebDriver, String selector, WebElement parentElement) {
		// if selector is null, then it was not provided and everyone matches it
		return selector == null || createParentElement(callerWebDriver, parentElement).is(selector);
	}

	private static SeleniumQueryObject createParentElement(WebDriver callerWebDriver, WebElement parentElement) {
		return ObjectLocalFactory.createWithInvalidSelectorAndNoPrevious(callerWebDriver, parentElement);
	}

}