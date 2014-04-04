package org.openqa.selenium.seleniumquery.wait;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.quantifier.And;
import org.openqa.selenium.seleniumquery.wait.quantifier.AtLeast;
import org.openqa.selenium.seleniumquery.wait.quantifier.Every;
import org.openqa.selenium.seleniumquery.wait.quantifier.Quantifier;
import org.openqa.selenium.seleniumquery.wait.restrictor.Is;
import org.openqa.selenium.seleniumquery.wait.restrictor.RestrictorDecorator;

public class SeleniumQueryWaitUntilIs {
	
	private SeleniumQueryObject seleniumQueryObject;

	private Quantifier quantifier;
	
	private RestrictorDecorator restrictorDecorator;
	
	public SeleniumQueryWaitUntilIs(SeleniumQueryObject seleniumQueryObject) {
		this(And.and(AtLeast.ONE, Every.EVERY), seleniumQueryObject);
	}
	
	public SeleniumQueryWaitUntilIs(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
		this.restrictorDecorator = RestrictorDecorator.NO_DECORATION;
	}
	
	public SeleniumQueryWaitUntilIs(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject, RestrictorDecorator restrictorDecorator) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
		this.restrictorDecorator = restrictorDecorator;
	}
	
	public SeleniumQueryWaitUntilIs not() {
		return new SeleniumQueryWaitUntilIs(quantifier, seleniumQueryObject, RestrictorDecorator.NEGATION);
	}
	
	/**
	 * Requeries the DOM <strong>until at least one element returned</strong> by a query to the selector used to construct this seleniumQuery object
	 * <strong>is visible</strong>.
	 * @return a seleniumQuery object containing the visible elements.
	 * 
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public SeleniumQueryObject visible() {
		return SeleniumQueryFluentWait.waitUntilIs(quantifier, restrictorDecorator.decorate(Is.VISIBLE), seleniumQueryObject);
	}
	
	public SeleniumQueryObject enabled() {
		return SeleniumQueryFluentWait.waitUntilIs(quantifier, restrictorDecorator.decorate(Is.ENABLED), seleniumQueryObject);
	}
	
	public SeleniumQueryObject present() {
		return SeleniumQueryFluentWait.waitUntilIs(quantifier, restrictorDecorator.decorate(Is.PRESENT), seleniumQueryObject);
	}
	
	public SeleniumQueryObject visibleAndEnabled() {
		return SeleniumQueryFluentWait.waitUntilIs(quantifier, restrictorDecorator.decorate(Is.VISIBLE_AND_ENABLED), seleniumQueryObject);
	}
	
//	/**
//	 * Waits until any element on the set is attached to the DOM.
//	 * 
//	 * @return the SeleniumQuery object.
//	 * 
//	 * @author acdcjunior
//	 * @since 0.3.0
//	 */
//	private void isPresent() {
//	}
//
//	/**
//	 * Waits until every element on the set is no longer attached to the DOM.
//	 * 
//	 * @return the SeleniumQuery object.
//	 * 
//	 * @author acdcjunior
//	 * @since 0.3.0
//	 */
//	private void isNotPresent() {
//	}
//	
//	/**
//	 * Waits until every element on the set is visible and enabled.
//	 * 
//	 * @return the SeleniumQuery object.
//	 * 
//	 * @author acdcjunior
//	 * @since 0.3.0
//	 */
//	private void isVisibleAndEnabled() {
//	}
//	
//	/**
//	 * Waits until every element on the set is visible.
//	 * 
//	 * @return the SeleniumQuery object.
//	 * 
//	 * @author acdcjunior
//	 * @since 0.3.0
//	 */
//	private void isVisible() {
//	}
//	
//	/**
//	 * Waits until every element on the set is not visible.
//	 * 
//	 * @return the SeleniumQuery object.
//	 * 
//	 * @author acdcjunior
//	 * @since 0.3.0
//	 */
//	private void isNotVisible() {
//	}
//	
//	/**
//	 * Waits until every element on the set contains <code>text</code>.
//	 * 
//	 * @return the SeleniumQuery object.
//	 * 
//	 * @author acdcjunior
//	 * @since 0.3.0
//	 */
//	private void containsText(String text) {
//	}
//	
//	/**
//	 * Waits until every element on the set has the value <code>value</code>.
//	 * 
//	 * @return the SeleniumQuery object.
//	 * 
//	 * @author acdcjunior
//	 * @since 0.3.0
//	 */
//	private void valueIs(String value) {
//	}
//	
//	/**
//	 * Waits until every element on the set do NOT have the value <code>value</code>.
//	 * 
//	 * @return the SeleniumQuery object.
//	 * 
//	 * @author acdcjunior
//	 * @since 0.3.0
//	 */
//	private void valueIsNot(String value) {
//	}
	
}