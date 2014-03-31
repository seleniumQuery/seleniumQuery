package org.openqa.selenium.seleniumquery.wait.queryuntil;

import org.openqa.selenium.By;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;

import com.google.common.base.Function;

public class AllAreNotPresent {

	public static void queryUntilAllAreNotPresent(final SeleniumQueryObject seleniumQueryObject) {
		SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				return seleniumQueryObject.getWebDriver().findElements(seleniumQueryObject.getBy()).size() == 0;
			}
		}, "to be not present.");
	}
	
}