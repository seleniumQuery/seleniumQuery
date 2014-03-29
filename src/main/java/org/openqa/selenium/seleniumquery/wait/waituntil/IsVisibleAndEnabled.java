package org.openqa.selenium.seleniumquery.wait.waituntil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;

import com.google.common.base.Function;

public class IsVisibleAndEnabled {
	
	public static void waitUntilIsVisibleAndEnabled(final SeleniumQueryObject seleniumQueryObject) {
		SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				for (WebElement webElement : seleniumQueryObject) {
					 if (!webElement.isDisplayed() || !webElement.isEnabled()) {
						 return false;
					 }
				}
				return true;
			}
		}, "to be visible and enabled.");
	}

}