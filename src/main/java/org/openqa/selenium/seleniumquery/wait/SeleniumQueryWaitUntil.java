package org.openqa.selenium.seleniumquery.wait;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.quantifier.AtLeast;
import org.openqa.selenium.seleniumquery.wait.quantifier.AtMost;
import org.openqa.selenium.seleniumquery.wait.quantifier.Every;
import org.openqa.selenium.seleniumquery.wait.quantifier.Exactly;
import org.openqa.selenium.seleniumquery.wait.quantifier.None;

public class SeleniumQueryWaitUntil {

	private SeleniumQueryObject seleniumQueryObject;
	
	public SeleniumQueryWaitUntil(SeleniumQueryObject seleniumQueryObject) {
		this.seleniumQueryObject = seleniumQueryObject;
	}

	public SeleniumQueryWaitUntilIs is() {
		return new SeleniumQueryWaitUntilIs(seleniumQueryObject);
	}
	
	public SeleniumQueryWaitUntilHas has() {
		return new SeleniumQueryWaitUntilHas(seleniumQueryObject);
	}
	
	public SeleniumQueryWaitUntilQuantity atLeast(int minNumberOfElementsExpected) {
		return new SeleniumQueryWaitUntilQuantity(new AtLeast(minNumberOfElementsExpected), seleniumQueryObject);
	}
	public SeleniumQueryWaitUntilQuantity exactly(int exactNumberOfElementsExpected) {
		return new SeleniumQueryWaitUntilQuantity(new Exactly(exactNumberOfElementsExpected), seleniumQueryObject);
	}
	public SeleniumQueryWaitUntilQuantity atMost(int maxNumberOfElementsExpected) {
		return new SeleniumQueryWaitUntilQuantity(new AtMost(maxNumberOfElementsExpected), seleniumQueryObject);
	}
	
	public SeleniumQueryWaitUntilQuantity everyElement() {
		return new SeleniumQueryWaitUntilQuantity(Every.EVERY, seleniumQueryObject);
	}
	
	public SeleniumQueryWaitUntilQuantity noElement() {
		return new SeleniumQueryWaitUntilQuantity(None.NONE, seleniumQueryObject);
	}
	
	public SeleniumQueryWaitUntilQuantity atLeastOneElement() {
		return new SeleniumQueryWaitUntilQuantity(AtLeast.ONE, seleniumQueryObject);
	}
	
	public SeleniumQueryWaitUntilQuantity exactlyOneElement() {
		return new SeleniumQueryWaitUntilQuantity(Exactly.ONE, seleniumQueryObject);
	}
	
	public SeleniumQueryWaitUntilQuantity atMostOneElement() {
		return new SeleniumQueryWaitUntilQuantity(AtMost.ONE, seleniumQueryObject);
	}
	
}