package org.openqa.selenium.seleniumquery.wait.waituntil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;

import com.google.common.base.Function;

public class ContainsText {

	public static void waitUntilcontainsText(final SeleniumQueryObject seleniumQueryObject, final String text) {
		 SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				for (WebElement webElement : seleniumQueryObject) {
					 if (!webElement.getText().contains(text)) {
						 return false;
					 }
				}
				return true;
			}
		}, "to contain text \""+text+"\".");
	}
	
}
