package org.openqa.selenium.seleniumquery.wait.queryuntil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Function;

public class AtLeastOneIsPresent {

	public static List<WebElement> queryUntilAtLeastOneIsPresent(final SeleniumQueryObject seleniumQueryObject) {
		return SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, List<WebElement>>() {
			@Override
			public List<WebElement> apply(By selector) {
				return ExpectedConditions.presenceOfAllElementsLocatedBy(seleniumQueryObject.getBy()).apply(seleniumQueryObject.getWebDriver());
			}
		}, "to be present.");
	}

}