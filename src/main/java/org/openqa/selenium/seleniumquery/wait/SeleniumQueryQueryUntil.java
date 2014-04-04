package org.openqa.selenium.seleniumquery.wait;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.quantifier.AtLeast;
import org.openqa.selenium.seleniumquery.wait.quantifier.AtMost;
import org.openqa.selenium.seleniumquery.wait.quantifier.Every;
import org.openqa.selenium.seleniumquery.wait.quantifier.Exactly;
import org.openqa.selenium.seleniumquery.wait.quantifier.None;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public class SeleniumQueryQueryUntil {
	
	private SeleniumQueryObject seleniumQueryObject;
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntil(SeleniumQueryObject seleniumQueryObject) {
		this.seleniumQueryObject = seleniumQueryObject;
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilIs is() {
		return new SeleniumQueryQueryUntilIs(seleniumQueryObject);
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilHas has() {
		return new SeleniumQueryQueryUntilHas(seleniumQueryObject);
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilQuantity atLeast(int minNumberOfElementsExpected) {
		return new SeleniumQueryQueryUntilQuantity(new AtLeast(minNumberOfElementsExpected), seleniumQueryObject);
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilQuantity exactly(int exactNumberOfElementsExpected) {
		return new SeleniumQueryQueryUntilQuantity(new Exactly(exactNumberOfElementsExpected), seleniumQueryObject);
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilQuantity atMost(int maxNumberOfElementsExpected) {
		return new SeleniumQueryQueryUntilQuantity(new AtMost(maxNumberOfElementsExpected), seleniumQueryObject);
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilQuantity everyElement() {
		return new SeleniumQueryQueryUntilQuantity(Every.EVERY, seleniumQueryObject);
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilQuantity noElement() {
		return new SeleniumQueryQueryUntilQuantity(None.NONE, seleniumQueryObject);
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilQuantity atLeastOneElement() {
		return new SeleniumQueryQueryUntilQuantity(AtLeast.ONE, seleniumQueryObject);
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilQuantity exactlyOneElement() {
		return new SeleniumQueryQueryUntilQuantity(Exactly.ONE, seleniumQueryObject);
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilQuantity atMostOneElement() {
		return new SeleniumQueryQueryUntilQuantity(AtMost.ONE, seleniumQueryObject);
	}
	
}