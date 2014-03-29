package org.openqa.selenium.seleniumquery.wait.waituntil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class Seconds {

	@Deprecated
	public static void seconds(final SeleniumQueryObject seleniumQueryObject, final int timeToWait) {
		final Long finalTime = System.currentTimeMillis() + timeToWait * 1000;
		new FluentWait<By>(seleniumQueryObject.getBy()).withTimeout(timeToWait+2, TimeUnit.SECONDS)
							  .pollingEvery(timeToWait*200, TimeUnit.MILLISECONDS)
							  .until(new Function<By, Boolean>() {
			@Override public Boolean apply(By selector) { return System.currentTimeMillis() > finalTime; }
		});
	}
	
}