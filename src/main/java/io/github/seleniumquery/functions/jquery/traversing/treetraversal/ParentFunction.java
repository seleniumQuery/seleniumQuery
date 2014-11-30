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
 * <pre>
 * $("selector").parent()
 * $("selector").parent(selector)
 * </pre>
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class ParentFunction {

	private static final String NO_FILTER_SELECTOR_PROVIDED = null;

	public static SeleniumQueryObject parent(SeleniumQueryObject caller) {
		return parent(caller, NO_FILTER_SELECTOR_PROVIDED);
	}

	public static SeleniumQueryObject parent(SeleniumQueryObject caller, String selector) {
		Set<WebElement> alreadyInsertedParents = new HashSet<WebElement>();
		WebDriver callerWebDriver = caller.getWebDriver();

		List<WebElement> elements = caller.get();
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
		//noinspection StringEquality
		return selector == NO_FILTER_SELECTOR_PROVIDED || createParentElement(callerWebDriver, parentElement).is(selector);
	}

	private static SeleniumQueryObject createParentElement(WebDriver callerWebDriver, WebElement parentElement) {
		return ObjectLocalFactory.createWithInvalidSelectorAndNoPrevious(callerWebDriver, parentElement);
	}

}