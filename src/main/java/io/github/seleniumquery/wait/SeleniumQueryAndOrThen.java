package io.github.seleniumquery.wait;

import io.github.seleniumquery.SeleniumQueryObject;

public class SeleniumQueryAndOrThen {
	
	private SeleniumQueryObject seleniumQueryObject;
	
	public SeleniumQueryAndOrThen(SeleniumQueryObject seleniumQueryObject) {
		this.seleniumQueryObject = seleniumQueryObject;
	}

	public SeleniumQueryWaitUntil and() {
		return new SeleniumQueryWaitUntil(this.seleniumQueryObject);
	}
	
	public SeleniumQueryObject then() {
		return this.seleniumQueryObject;
	}

}