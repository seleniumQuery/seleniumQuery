package org.openqa.selenium.seleniumquery.wait;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.quantifier.Quantifier;

/**
 * Collection of functions that, instead of acting on the current selected
 * element set of the seleniumQueryObject (such as the waitUntil functions),
 * will requery the DOM at every condition check and in the end will return a
 * brand new seleniumQueryObject.
 * 
 * @author acdcjunior
 * @since 0.3.0
 */
public class SeleniumQueryWaitUntilQuantity {

	private SeleniumQueryObject seleniumQueryObject;

	private Quantifier quantifier;

	public SeleniumQueryWaitUntilQuantity(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
	}

	public SeleniumQueryWaitUntilIs is() {
		return new SeleniumQueryWaitUntilIs(quantifier, seleniumQueryObject);
	}

	/**
	 * An alias to {@link SeleniumQueryWaitUntilQuantity#is()}.
	 * 
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryWaitUntilIs are() {
		return new SeleniumQueryWaitUntilIs(quantifier, seleniumQueryObject);
	}

}