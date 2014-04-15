package org.openqa.selenium.seleniumquery.wait;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;

public class SeleniumQueryAndOrThen {
	
	private SeleniumQueryObject seleniumQueryObject;
	
	public SeleniumQueryAndOrThen(SeleniumQueryObject seleniumQueryObject) {
		this.seleniumQueryObject = seleniumQueryObject;
	}

	public SeleniumQueryQueryUntil and() {
		return new SeleniumQueryQueryUntil(this.seleniumQueryObject);
	}
	
	public SeleniumQueryObject then() {
		return this.seleniumQueryObject;
	}

}