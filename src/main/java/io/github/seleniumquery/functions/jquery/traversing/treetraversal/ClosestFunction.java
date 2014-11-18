package io.github.seleniumquery.functions.jquery.traversing.treetraversal;

import io.github.seleniumquery.SQLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.CssSelectorMatcherService;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * $("selector").closest("selector")
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class ClosestFunction {

	public static SeleniumQueryObject closest(SeleniumQueryObject caller, List<WebElement> elements, String selector) {
		WebDriver driver = caller.getWebDriver();
		
		List<WebElement> closests = new ArrayList<WebElement>(elements.size());
		for (WebElement element : elements) {
			WebElement closestElement = closest(driver, element, selector);
			if (closestElement != null) {
				closests.add(closestElement);
			}
		}
		
		return SQLocalFactory.createWithInvalidSelector(caller.getWebDriver(), closests, caller);
	}

	public static WebElement closest(WebDriver driver, WebElement element, String selector) {
		WebElement ancestorOrSelf = element; // begins by evaluating the element itself
		while (ancestorOrSelf != null) {
			if (CssSelectorMatcherService.elementMatchesStringSelector(driver, ancestorOrSelf, selector)) {
				return ancestorOrSelf;
			}
			ancestorOrSelf = SelectorUtils.parent(ancestorOrSelf);
		}
		// if ancestorOrSelf is null, it reached document root,
		// so no ancestor matching the selector was found
		return null;
	}

}