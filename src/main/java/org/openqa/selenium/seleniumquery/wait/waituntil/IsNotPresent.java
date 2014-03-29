package org.openqa.selenium.seleniumquery.wait.waituntil;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;

import com.google.common.base.Function;

public class IsNotPresent {

	public static void waitUntilIsNotPresent(final SeleniumQueryObject seleniumQueryObject) {
		SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				for (WebElement webElement : seleniumQueryObject) {
			        try {
			            // Calling any method forces a staleness check
			        	webElement.isEnabled();
			        	// at this point, no exception was thrown, so the element is still present
			            return false;
			          } catch (StaleElementReferenceException expected) { }
				}
				return true;
			}
		}, "to be not present.");
	}

}