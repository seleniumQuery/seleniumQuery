package io.github.seleniumquery.wait;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.wait.SeleniumQueryFluentWait.WaitMethod;

public class SeleniumQueryWaitUntil extends SeleniumQueryQueryUntil {
	
	public SeleniumQueryWaitUntil(SeleniumQueryObject seleniumQueryObject) {
		super(seleniumQueryObject);
	}

	@Override
	protected WaitMethod getWaitMethod() {
		return SeleniumQueryFluentWait.WAIT_UNTIL;
	}
	
}