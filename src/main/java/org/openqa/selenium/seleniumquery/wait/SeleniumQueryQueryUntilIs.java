package org.openqa.selenium.seleniumquery.wait;

import static org.openqa.selenium.seleniumquery.wait.Command.IS;
import static org.openqa.selenium.seleniumquery.wait.Command.IS_NOT;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.quantifier.And;
import org.openqa.selenium.seleniumquery.wait.quantifier.AtLeast;
import org.openqa.selenium.seleniumquery.wait.quantifier.Every;
import org.openqa.selenium.seleniumquery.wait.quantifier.Quantifier;
import org.openqa.selenium.seleniumquery.wait.restrictor.Is;
import org.openqa.selenium.seleniumquery.wait.restrictor.Restrictor;

public class SeleniumQueryQueryUntilIs {
	
	private SeleniumQueryObject seleniumQueryObject;
	private Quantifier quantifier;
	private Command command;
	
	public SeleniumQueryQueryUntilIs(SeleniumQueryObject seleniumQueryObject) {
		this(Every.EVERY, seleniumQueryObject);
	}
	
	public SeleniumQueryQueryUntilIs(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject) {
		this(quantifier, seleniumQueryObject, IS);
	}
	
	private SeleniumQueryQueryUntilIs(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject, Command command) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
		this.command = command;
	}
	
	public SeleniumQueryQueryUntilIs not() {
		return new SeleniumQueryQueryUntilIs(quantifier, seleniumQueryObject, IS_NOT);
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
		return SeleniumQueryFluentWait.queryUntilIs(getDecoratedQuantifier(), decorateRestrictor(Is.VISIBLE), seleniumQueryObject);
	}
	
	public SeleniumQueryObject enabled() {
		return SeleniumQueryFluentWait.queryUntilIs(getDecoratedQuantifier(), decorateRestrictor(Is.ENABLED), seleniumQueryObject);
	}
	
	public SeleniumQueryObject present() {
		return SeleniumQueryFluentWait.queryUntilIs(getDecoratedQuantifier(), decorateRestrictor(Is.PRESENT), seleniumQueryObject);
	}
	
	public SeleniumQueryObject visibleAndEnabled() {
		return SeleniumQueryFluentWait.queryUntilIs(getDecoratedQuantifier(), decorateRestrictor(Is.VISIBLE_AND_ENABLED), seleniumQueryObject);
	}
	
	/**
	 * If not a negation, the restrictor (VISIBLE/PRESENT/etc) should test if at least there is one element
	 * otherwise they may be successful when no elements are VISIBLE/PRESENT but the element count is zero
	 */
	private Quantifier getDecoratedQuantifier() {
		if (!isNegation()) {
			return And.and(AtLeast.ONE, quantifier); 
		}
		return quantifier;
	}
	
	/**
	 * If this is a negation, will return the restrictor negated, otherwise does nothing.
	 */
	private Restrictor decorateRestrictor(Restrictor restrictor) {
		if (isNegation()) {
			return Is.not(restrictor);
		}
		return restrictor;
	}
	
	private boolean isNegation() {
		return this.command == IS_NOT;
	}
	
}