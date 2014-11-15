package io.github.seleniumquery.functions.jquery.traversing.treetraversal;

import io.github.seleniumquery.SQLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.selector.SelectorUtils;

import org.openqa.selenium.WebElement;

import java.util.*;

import static io.github.seleniumquery.SeleniumQuery.sQ;

/**
 * $("selector").parent()
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class ParentFunction {

	public static SeleniumQueryObject parent(SeleniumQueryObject caller, List<WebElement> elements) {
		return parent(caller, elements, null);
	}

	public static SeleniumQueryObject parent(SeleniumQueryObject caller, List<WebElement> elements, String selector) {
		Set<WebElement> alreadyInsertedParents = new HashSet<WebElement>();

		List<WebElement> parents = new ArrayList<WebElement>(elements.size());
		for (WebElement element : elements) {
			WebElement parentElement = SelectorUtils.parent(element);
			if (parentElement != null && !alreadyInsertedParents.contains(parentElement) && parentMatchesSelector(selector, parentElement)) {
				parents.add(parentElement);
				alreadyInsertedParents.add(parentElement);
			}
		}
		return SQLocalFactory.createWithInvalidSelector(caller.getWebDriver(), parents, caller);
	}

	private static boolean parentMatchesSelector(String selector, WebElement parentElement) {
		// if selector is null, then it was not provided and everyone matches it
		return selector == null || sQ(parentElement).is(selector);
	}

}