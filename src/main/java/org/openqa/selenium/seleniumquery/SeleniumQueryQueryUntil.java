package org.openqa.selenium.seleniumquery;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.wait.queryuntil.AllAreNotVisibile;
import org.openqa.selenium.seleniumquery.wait.queryuntil.AtLeastOneIsPresent;
import org.openqa.selenium.seleniumquery.wait.queryuntil.AtLeastOneIsVisible;
import org.openqa.selenium.seleniumquery.wait.queryuntil.AtLeastOneIsVisibleAndEnabled;
import org.openqa.selenium.seleniumquery.wait.queryuntil.AllAreNotPresent;
import org.openqa.selenium.seleniumquery.wait.queryuntil.ValuesAreOrAreNot;

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
	 * Requeries the DOM <strong>until at least one element returned</strong> by a query to the selector used to construct this seleniumQuery object
	 * <strong>is visible</strong>.
	 * @return a seleniumQuery object containing the visible elements.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject allAreNotVisible() {
		List<WebElement> elements = AllAreNotVisibile.queryUntilAllAreNotVisible(seleniumQueryObject);
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), elements, seleniumQueryObject);
	}
	
	/**
	 * Requeries the DOM <strong>until at least one element returned</strong> by a query to the selector used to construct this seleniumQuery object
	 * <strong>is present</strong>.
	 * @return a seleniumQuery object containing the present elements.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject atLeastOneIsPresent() {
		List<WebElement> elements = AtLeastOneIsPresent.queryUntilAtLeastOneIsPresent(seleniumQueryObject);
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), elements, seleniumQueryObject);
	}
	
	/**
	 * Requeries the DOM <strong>until at least one element returned</strong> by a query to the selector used to construct this seleniumQuery object
	 * <strong>is visible</strong>.
	 * @return a seleniumQuery object containing the visible elements.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject atLeastOneIsVisible() {
		List<WebElement> elements = AtLeastOneIsVisible.queryUntilAtLeastOneIsVisible(seleniumQueryObject);
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), elements, seleniumQueryObject);
	}
	
	/**
	 * Requeries the DOM <strong>until at least one element returned</strong> by a query to the selector used to construct this seleniumQuery object
	 * <strong>is visible and enabled</strong>.
	 * @return a seleniumQuery object containing the visible and enabled elements.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject atLeastOneIsVisibleAndEnabled() {
		List<WebElement> elements = AtLeastOneIsVisibleAndEnabled.queryUntilAtLeastOneIsVisibleAndEnabled(seleniumQueryObject);
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), elements, seleniumQueryObject);
	}
	
	/**
	 * Requeries the DOM until no element is returned by a query to the selector used to construct this seleniumQueryObject.
	 * @return a seleniumQueryObject containing no elements.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject allAreNotPresent() {
		AllAreNotPresent.queryUntilAllAreNotPresent(seleniumQueryObject);
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), seleniumQueryObject.getBy().getSelector(), new ArrayList<WebElement>(), seleniumQueryObject);
	}
	
	/**
	 * Requeries the DOM <strong>until every element returned</strong> by a query to the selector used to construct this seleniumQuery object
	 * <strong>have the value of the provided <code>value</code> argument</strong>.
	 * @return a seleniumQuery object containing the elements.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject elementsValuesAre(String value) {
		List<WebElement> elements = ValuesAreOrAreNot.queryUntilElementsValuesAre(seleniumQueryObject, value);
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), elements, seleniumQueryObject);
	}
	
	/**
	 * Requeries the DOM <strong>until every element returned</strong> by a query to the selector used to construct this seleniumQuery object
	 * <strong>do NOT have the value of the provided <code>value</code> argument</strong>.
	 * @return a seleniumQuery object containing the elements.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject elementsValuesAreNot(String value) {
		List<WebElement> elements = ValuesAreOrAreNot.queryUntilElementsValuesAreNot(seleniumQueryObject, value);
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), elements, seleniumQueryObject);
	}

}