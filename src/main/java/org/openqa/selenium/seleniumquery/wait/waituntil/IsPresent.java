package org.openqa.selenium.seleniumquery.wait.waituntil;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;

import com.google.common.base.Function;

public class IsPresent {

	public static void waitUntilIsPresent(final SeleniumQueryObject seleniumQueryObject) {
		SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				for (WebElement webElement : seleniumQueryObject) {
			        try {
			            // Calling any method forces a staleness check
			        	webElement.isEnabled();
			        	// at this point, no exception was thrown, so the element is still present
			            return true;
			          } catch (StaleElementReferenceException expected) { }
				}
				// all elements threw a stale reference exception
				return false;
			}
		}, "to be not present.");
	}

}