package org.openqa.selenium.seleniumquery.wait;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.quantifier.And;
import org.openqa.selenium.seleniumquery.wait.quantifier.AtLeast;
import org.openqa.selenium.seleniumquery.wait.quantifier.Every;
import org.openqa.selenium.seleniumquery.wait.quantifier.Quantifier;
import org.openqa.selenium.seleniumquery.wait.restrictor.Is;
import org.openqa.selenium.seleniumquery.wait.restrictor.RestrictorDecorator;

public class SeleniumQueryQueryUntilHas {
	
	private SeleniumQueryObject seleniumQueryObject;

	private Quantifier quantifier;
	
	private RestrictorDecorator restrictorDecorator;
	
	public SeleniumQueryQueryUntilHas(SeleniumQueryObject seleniumQueryObject) {
		this(And.and(AtLeast.ONE, Every.EVERY), seleniumQueryObject);
	}
	
	public SeleniumQueryQueryUntilHas(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
		this.restrictorDecorator = RestrictorDecorator.NO_DECORATION;
	}
	
	public SeleniumQueryQueryUntilHas(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject, RestrictorDecorator restrictorDecorator) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
		this.restrictorDecorator = restrictorDecorator;
	}
	
	public SeleniumQueryQueryUntilHas not() {
		return new SeleniumQueryQueryUntilHas(quantifier, seleniumQueryObject, RestrictorDecorator.NEGATION);
	}
	
	public SeleniumQueryObject val(String value) {
		return SeleniumQueryFluentWait.queryUntilIs(quantifier, restrictorDecorator.decorate(Is.withValue(value)), seleniumQueryObject);
	}
	
	public SeleniumQueryObject textContaining(String text) {
		return SeleniumQueryFluentWait.queryUntilIs(quantifier, restrictorDecorator.decorate(Is.textContaining(text)), seleniumQueryObject);
	}
	
}