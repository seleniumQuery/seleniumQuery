package org.openqa.selenium.seleniumquery.waituntil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.seleniumquery.SeleniumQueryConfig;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class SeleniumQueryFluentWait {

	public static <T> T fluentWait(SeleniumQueryObject seleniumQueryObject, Function<By, T> function, String reason) {
		try {
			return new FluentWait<By>(seleniumQueryObject.getBy())
					.withTimeout(SeleniumQueryConfig.getWaitTimeoutInSeconds(), TimeUnit.SECONDS)
					.pollingEvery(SeleniumQueryConfig.getWaitPollingInMillisseconds(), TimeUnit.MILLISECONDS)
					.ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class)
					.until(function);
		} catch (TimeoutException sourceException) {
			throw new SeleniumQueryWaitException(sourceException, seleniumQueryObject, reason);
		}
	}

}