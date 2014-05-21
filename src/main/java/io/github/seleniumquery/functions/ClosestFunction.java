package io.github.seleniumquery.functions;

import io.github.seleniumquery.SQLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.selector.CssSelectorMatcherService;
import io.github.seleniumquery.selector.SelectorUtils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
		
		return SQLocalFactory.getInstance().createWithInvalidSelector(caller.getWebDriver(), closests, caller);
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