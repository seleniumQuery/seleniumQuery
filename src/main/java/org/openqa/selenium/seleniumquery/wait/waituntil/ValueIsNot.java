package org.openqa.selenium.seleniumquery.wait.waituntil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.functions.ValFunction;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;

import com.google.common.base.Function;

public class ValueIsNot {
	
	public static void waitUntilValueIsNot(final SeleniumQueryObject seleniumQueryObject, final String value) {
		SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
					for (WebElement webElement : seleniumQueryObject) {
						boolean valueEquals = ValFunction.val(webElement).equals(value);
						if (valueEquals) {
							return false;
						}
					}
					return true;
			}
		}, "to not have value \""+value+"\".");
	}

}