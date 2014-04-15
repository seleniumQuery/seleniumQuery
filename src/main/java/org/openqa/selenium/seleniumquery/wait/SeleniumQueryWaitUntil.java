package org.openqa.selenium.seleniumquery.wait;

import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait.WaitMethod;

public class SeleniumQueryWaitUntil extends SeleniumQueryQueryUntil {
	
	public SeleniumQueryWaitUntil(SeleniumQueryObject seleniumQueryObject) {
		super(seleniumQueryObject);
	}

	@Override
	protected WaitMethod getWaitMethod() {
		return SeleniumQueryFluentWait.WAIT_UNTIL;
	}
	
}