package org.openqa.selenium.seleniumquery.wait;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.quantifier.Quantifier;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public class SeleniumQueryQueryUntilQuantity {

	private SeleniumQueryObject seleniumQueryObject;

	private Quantifier quantifier;

	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilQuantity(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
	}

	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilIs is() {
		return new SeleniumQueryQueryUntilIs(quantifier, seleniumQueryObject);
	}

	/**
	 * An alias to {@link SeleniumQueryQueryUntilQuantity#is()}.
	 * 
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilIs are() {
		return new SeleniumQueryQueryUntilIs(quantifier, seleniumQueryObject);
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilHas has() {
		return new SeleniumQueryQueryUntilHas(quantifier, seleniumQueryObject);
	}
	
	/**
	 * An alias to {@link SeleniumQueryQueryUntilQuantity#has()}.
	 * 
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryQueryUntilHas have() {
		return new SeleniumQueryQueryUntilHas(quantifier, seleniumQueryObject);
	}

}