package org.openqa.selenium.seleniumquery.wait;

import static org.openqa.selenium.seleniumquery.wait.Command.HAS;
import static org.openqa.selenium.seleniumquery.wait.Command.HAS_NOT;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.quantifier.And;
import org.openqa.selenium.seleniumquery.wait.quantifier.AtLeast;
import org.openqa.selenium.seleniumquery.wait.quantifier.Every;
import org.openqa.selenium.seleniumquery.wait.quantifier.Quantifier;
import org.openqa.selenium.seleniumquery.wait.restrictor.Not;
import org.openqa.selenium.seleniumquery.wait.restrictor.Restrictor;
import org.openqa.selenium.seleniumquery.wait.restrictor.has.HasValEqualTo;
import org.openqa.selenium.seleniumquery.wait.restrictor.has.HasTextContaining;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public class SeleniumQueryWaitUntilHas {
	
	private SeleniumQueryObject seleniumQueryObject;
	private Quantifier quantifier;
	private Command command;
	
	public SeleniumQueryWaitUntilHas(SeleniumQueryObject seleniumQueryObject) {
		this(Every.EVERY, seleniumQueryObject);
	}
	
	public SeleniumQueryWaitUntilHas(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject) {
		this(quantifier, seleniumQueryObject, HAS);
	}
	
	private SeleniumQueryWaitUntilHas(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject, Command command) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
		this.command = command;
	}
	
	public SeleniumQueryWaitUntilHas not() {
		return new SeleniumQueryWaitUntilHas(quantifier, seleniumQueryObject, HAS_NOT);
	}
	
	public SeleniumQueryObject value(String value) {
		return SeleniumQueryFluentWait.waitUntilIs(getDecoratedQuantifier(), decorateRestrictor(HasValEqualTo.hasValEqualTo(value)), seleniumQueryObject);
	}
	
	public SeleniumQueryObject textContaining(String text) {
		return SeleniumQueryFluentWait.waitUntilIs(getDecoratedQuantifier(), decorateRestrictor(HasTextContaining.hasTextContaining(text)), seleniumQueryObject);
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
			return Not.not(restrictor);
		}
		return restrictor;
	}

	private boolean isNegation() {
		return this.command == HAS_NOT;
	}
	
}