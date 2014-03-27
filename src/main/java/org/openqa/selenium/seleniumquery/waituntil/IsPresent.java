package org.openqa.selenium.seleniumquery.waituntil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Function;

public class IsPresent {

	public static List<WebElement> isPresent(final SeleniumQueryObject seleniumQueryObject) {
		List<WebElement> element = SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, List<WebElement>>() {
			@Override
			public List<WebElement> apply(By selector) {
				return ExpectedConditions.presenceOfAllElementsLocatedBy(seleniumQueryObject.getBy()).apply(seleniumQueryObject.getWebDriver());
			}
		}, "to be present.");
		return element;
	}

}