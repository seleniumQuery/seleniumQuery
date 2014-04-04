package org.openqa.selenium.seleniumquery.wait;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.quantifier.And;
import org.openqa.selenium.seleniumquery.wait.quantifier.AtLeast;
import org.openqa.selenium.seleniumquery.wait.quantifier.Every;
import org.openqa.selenium.seleniumquery.wait.quantifier.Quantifier;
import org.openqa.selenium.seleniumquery.wait.restrictor.Is;
import org.openqa.selenium.seleniumquery.wait.restrictor.RestrictorDecorator;

public class SeleniumQueryWaitUntilHas {
	
	private SeleniumQueryObject seleniumQueryObject;

	private Quantifier quantifier;
	
	private RestrictorDecorator restrictorDecorator;
	
	public SeleniumQueryWaitUntilHas(SeleniumQueryObject seleniumQueryObject) {
		this(And.and(AtLeast.ONE, Every.EVERY), seleniumQueryObject);
	}
	
	public SeleniumQueryWaitUntilHas(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
		this.restrictorDecorator = RestrictorDecorator.NO_DECORATION;
	}
	
	public SeleniumQueryWaitUntilHas(Quantifier quantifier, SeleniumQueryObject seleniumQueryObject, RestrictorDecorator restrictorDecorator) {
		this.quantifier = quantifier;
		this.seleniumQueryObject = seleniumQueryObject;
		this.restrictorDecorator = restrictorDecorator;
	}
	
	public SeleniumQueryWaitUntilHas not() {
		return new SeleniumQueryWaitUntilHas(quantifier, seleniumQueryObject, RestrictorDecorator.NEGATION);
	}
	
	public SeleniumQueryObject value(String value) {
		return SeleniumQueryFluentWait.waitUntilIs(quantifier, restrictorDecorator.decorate(Is.withValue(value)), seleniumQueryObject);
	}
	
	public SeleniumQueryObject textContaining(String text) {
		return SeleniumQueryFluentWait.waitUntilIs(quantifier, restrictorDecorator.decorate(Is.textContaining(text)), seleniumQueryObject);
	}
	
}