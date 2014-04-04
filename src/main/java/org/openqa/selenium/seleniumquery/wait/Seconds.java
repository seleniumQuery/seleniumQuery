package org.openqa.selenium.seleniumquery.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;

import com.google.common.base.Function;

public class Seconds {

	public static void seconds(final SeleniumQueryObject seleniumQueryObject, final int timeToWait) {
		final Long finalTime = System.currentTimeMillis() + timeToWait * 1000;
		SeleniumQueryFluentWait.fluentWait(seleniumQueryObject,	new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				return System.currentTimeMillis() > finalTime;
			}
		}, "to havce " + timeToWait + " seconds passed.");
	}

}