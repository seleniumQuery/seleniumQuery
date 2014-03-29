package org.openqa.selenium.seleniumquery;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.wait.queryuntil.AtLeastOneIsVisibleAndEnabled;
import org.openqa.selenium.seleniumquery.wait.queryuntil.NoneArePresent;

/**
 * Collection of functions that, instead of acting on the current selected element set of the
 * seleniumQueryObject (such as the waitUntil functions), will requery the DOM at every condition
 * check and in the end will return a brand new seleniumQueryObject.
 * 
 * @author acdcjunior
 * @since 0.3.0
 */
public class SeleniumQueryQueryUntil {
	
	private SeleniumQueryObject seleniumQueryObject;
	
	public SeleniumQueryQueryUntil(SeleniumQueryObject seleniumQueryObject) {
		this.seleniumQueryObject = seleniumQueryObject;
	}
	
	/**
	 * Requeries the DOM until no element is returned by a query to the selector used to construct this seleniumQueryObject.
	 * @return a seleniumQueryObject containing no elements.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject noneArePresent() {
		NoneArePresent.queryUntilNoneArePresent(seleniumQueryObject);
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), seleniumQueryObject.getBy().getSelector());
	}
	
	/**
	 * Requeries the DOM <strong>until at least one element returned</strong> by a query to the selector used to construct this seleniumQueryObject
	 * <strong>is visible and enabled</strong>.
	 * @return a seleniumQueryObject containing no elements.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject atLeastOneIsVisibleAndEnabled() {
		List<WebElement> elements = AtLeastOneIsVisibleAndEnabled.queryUntilAtLeastOneIsVisibleAndEnabled(seleniumQueryObject);
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), elements);
	}

}