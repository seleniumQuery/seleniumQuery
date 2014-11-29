package io.github.seleniumquery;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This factory builds {@link io.github.seleniumquery.SeleniumQueryObject}s. Necessary because all
 * constructors in that class have protected visibility (we don't want them public).
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class ObjectLocalFactory {
	
	private static final SeleniumQueryObject NO_PREVIOUS = null;
	
	public static SeleniumQueryObject create(WebDriver driver, String selector, List<WebElement> elements, SeleniumQueryObject seleniumQueryObject) {
		return new SeleniumQueryObject(driver, selector, elements, seleniumQueryObject);
	}

	public static SeleniumQueryObject createWithInvalidSelector(WebDriver driver, WebElement element, SeleniumQueryObject previous) {
		List<WebElement> elements = toArrayList(element);
		return createWithInvalidSelector(driver, elements, previous);
	}

	public static SeleniumQueryObject createWithInvalidSelector(WebDriver driver, List<WebElement> elements, SeleniumQueryObject previous) {
		return new SeleniumQueryObject(driver, elements, previous);
	}

	public static SeleniumQueryObject createWithInvalidSelectorAndNoPrevious(WebDriver driver, List<WebElement> elements) {
		return createWithInvalidSelector(driver, elements, NO_PREVIOUS);
	}
	
	public static SeleniumQueryObject createWithInvalidSelectorAndNoPrevious(WebDriver driver, WebElement... elements) {
		return createWithInvalidSelectorAndNoPrevious(driver, toArrayList(elements));
	}

	static SeleniumQueryObject createWithValidSelectorAndNoPrevious(WebDriver driver, String selector) {
		return new SeleniumQueryObject(driver, selector);
	}

	private static List<WebElement> toArrayList(WebElement... elements) {
		return new ArrayList<WebElement>(Arrays.asList(elements));
	}

}