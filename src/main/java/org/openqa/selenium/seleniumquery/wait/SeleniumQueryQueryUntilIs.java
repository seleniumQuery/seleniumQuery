package org.openqa.selenium.seleniumquery.wait;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.quantifier.And;
import org.openqa.selenium.seleniumquery.wait.quantifier.AtLeast;
import org.openqa.selenium.seleniumquery.wait.quantifier.Every;
import org.openqa.selenium.seleniumquery.wait.quantifier.Quantifier;
import org.openqa.selenium.seleniumquery.wait.restrictor.Is;
import org.openqa.selenium.seleniumquery.wait.restrictor.RestrictorDecorator;

public class SeleniumQueryQueryUntilIs {
	
	private SeleniumQueryObject seleniumQueryObject;

	private Quantifier quantifier;
	
	private RestrictorDecorator restrictorDecorator;
	
	public SeleniumQueryQueryUntilIs(SeleniumQueryObject seleniumQueryObject) {
		this(And.and(AtLeast.ONE, Every.EVERY), seleniumQueryObject);
	}
	
	public SeleniumQueryQueryUntilIs(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
		this.restrictorDecorator = RestrictorDecorator.NO_DECORATION;
	}
	
	public SeleniumQueryQueryUntilIs(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject, RestrictorDecorator restrictorDecorator) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
		this.restrictorDecorator = restrictorDecorator;
	}
	
	public SeleniumQueryQueryUntilIs not() {
		return new SeleniumQueryQueryUntilIs(quantifier, seleniumQueryObject, RestrictorDecorator.NEGATION);
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
		return SeleniumQueryFluentWait.queryUntilIs(quantifier, restrictorDecorator.decorate(Is.VISIBLE), seleniumQueryObject);
	}
	
	public SeleniumQueryObject enabled() {
		return SeleniumQueryFluentWait.queryUntilIs(quantifier, restrictorDecorator.decorate(Is.ENABLED), seleniumQueryObject);
	}
	
	public SeleniumQueryObject present() {
		return SeleniumQueryFluentWait.queryUntilIs(quantifier, restrictorDecorator.decorate(Is.PRESENT), seleniumQueryObject);
	}
	
	public SeleniumQueryObject visibleAndEnabled() {
		return SeleniumQueryFluentWait.queryUntilIs(quantifier, restrictorDecorator.decorate(Is.VISIBLE_AND_ENABLED), seleniumQueryObject);
	}
	
}