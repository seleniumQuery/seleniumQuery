package io.github.seleniumquery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SQLocalFactory {
	
	private static final SeleniumQueryObject NO_PREVIOUS = null;
	
	public static SeleniumQueryObject create(WebDriver driver, String selector, List<WebElement> elements, SeleniumQueryObject seleniumQueryObject) {
		return new SeleniumQueryObject(driver, selector, elements, seleniumQueryObject);
	}

	public static SeleniumQueryObject createWithInvalidSelector(WebDriver driver, WebElement element, SeleniumQueryObject previous) {
		List<WebElement> elements = new ArrayList<WebElement>(Arrays.asList(element));
		return createWithInvalidSelector(driver, elements, previous);
	}

	public static SeleniumQueryObject createWithInvalidSelector(WebDriver driver, List<WebElement> elements, SeleniumQueryObject previous) {
		return new SeleniumQueryObject(driver, elements, previous);
	}
	
	public static SeleniumQueryObject createWithInvalidSelectorAndNoPrevious(WebDriver driver, WebElement element) {
		List<WebElement> elements = new ArrayList<WebElement>(Arrays.asList(element));
		return new SeleniumQueryObject(driver, elements, NO_PREVIOUS);
	}
	
}