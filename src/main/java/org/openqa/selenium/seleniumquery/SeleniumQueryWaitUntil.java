package org.openqa.selenium.seleniumquery;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.wait.waituntil.ContainsText;
import org.openqa.selenium.seleniumquery.wait.waituntil.IsNotPresent;
import org.openqa.selenium.seleniumquery.wait.waituntil.IsNotVisible;
import org.openqa.selenium.seleniumquery.wait.waituntil.IsPresent;
import org.openqa.selenium.seleniumquery.wait.waituntil.IsVisible;
import org.openqa.selenium.seleniumquery.wait.waituntil.IsVisibleAndEnabled;
import org.openqa.selenium.seleniumquery.wait.waituntil.Seconds;
import org.openqa.selenium.seleniumquery.wait.waituntil.ValueIs;
import org.openqa.selenium.seleniumquery.wait.waituntil.ValueIsNot;

public class SeleniumQueryWaitUntil {

	private SeleniumQueryObject seleniumQueryObject;
	
	public SeleniumQueryWaitUntil(SeleniumQueryObject seleniumQueryObject) {
		this.seleniumQueryObject = seleniumQueryObject;
	}

	public SeleniumQueryObject isPresent() {
		List<WebElement> presentElements = IsPresent.waitUntilIsPresent(this.seleniumQueryObject);
		seleniumQueryObject.setElements(presentElements);
		return seleniumQueryObject;
	}

	/**
	 * Waits until every element on the set is no longer attached to the DOM.
	 * 
	 * @return the SeleniumQuery object.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject isNotPresent() {
		IsNotPresent.waitUntilIsNotPresent(this.seleniumQueryObject);
		return seleniumQueryObject;
	}
	
	/**
	 * Waits until every element on the set is visible and enabled.
	 * 
	 * @return the SeleniumQuery object.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject isVisibleAndEnabled() {
		IsVisibleAndEnabled.waitUntilIsVisibleAndEnabled(seleniumQueryObject);
		return seleniumQueryObject;
	}
	
	/**
	 * Waits until every element on the set is visible.
	 * 
	 * @return the SeleniumQuery object.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject isVisible() {
		IsVisible.waitUntilIsVisible(seleniumQueryObject);
		return seleniumQueryObject;
	}
	
	/**
	 * Waits until every element on the set is not visible.
	 * 
	 * @return the SeleniumQuery object.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject isNotVisible() {
		IsNotVisible.isNotVisible(seleniumQueryObject);
		return seleniumQueryObject;
	}
	
	/**
	 * Waits until every element on the set contains <code>text</code>.
	 * 
	 * @return the SeleniumQuery object.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject containsText(String text) {
		seleniumQueryObject.setElements(ContainsText.containsText(seleniumQueryObject, text));
		return seleniumQueryObject;
	}
	
	/**
	 * Waits for <code>timeToWait</code> seconds.
	 * 
	 * @param timeToWait Time to wait in seconds.
	 * @return the SeleniumQuery object.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject seconds(int timeToWait) {
		Seconds.seconds(seleniumQueryObject, timeToWait);
		return seleniumQueryObject;
	}
	
	/**
	 * Waits until every element on the set has the value <code>value</code>.
	 * 
	 * @return the SeleniumQuery object.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject valueIs(String value) {
		ValueIs.waitUntilValueIs(seleniumQueryObject, value);
		return seleniumQueryObject;
	}
	
	/**
	 * Waits until every element on the set do NOT have the value <code>value</code>.
	 * 
	 * @return the SeleniumQuery object.
	 * 
	 * @author acdcjunior
	 * @since 0.3.0
	 */
	public SeleniumQueryObject valueIsNot(String value) {
		ValueIsNot.waitUntilValueIsNot(seleniumQueryObject, value);
		return seleniumQueryObject;
	}
	
}