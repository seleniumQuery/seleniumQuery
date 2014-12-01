package io.github.seleniumquery.wait;

import io.github.seleniumquery.SeleniumQueryObject;

/**
 * A builder class that allows to specify the next step after waiting for some condition: to wait for more
 * (<code>.and()</code>) or to do something else (<code>.then()</code>).
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class SeleniumQueryAndOrThen {
	
	private SeleniumQueryObject seleniumQueryObject;
	
	public SeleniumQueryAndOrThen(SeleniumQueryObject seleniumQueryObject) {
		this.seleniumQueryObject = seleniumQueryObject;
	}

	/**
	 * Allows the chaining of additional waiting conditions.
	 *
	 * @return An object where it is possible to set more waiting conditions.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryWaitUntil and() {
		return new SeleniumQueryWaitUntil(this.seleniumQueryObject);
	}

	/**
	 * Allows the execution of a regular function (such as <code>.click()</code>) on the elements matched after
	 * the waiting condition is met.
	 *
 	 * @return The {@link SeleniumQueryObject} after the waiting conditions were met.
	 */
	public SeleniumQueryObject then() {
		return this.seleniumQueryObject;
	}

}