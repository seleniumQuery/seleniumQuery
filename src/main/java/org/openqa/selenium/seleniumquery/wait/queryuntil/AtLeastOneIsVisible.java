package org.openqa.selenium.seleniumquery.wait.queryuntil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.EnhancedExpectedConditions;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;

import com.google.common.base.Function;

public class AtLeastOneIsVisible {
	
	public static List<WebElement> queryUntilAtLeastOneIsVisible(final SeleniumQueryObject seleniumQueryObject) {
		return SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, List<WebElement>>() {
			@Override
			public List<WebElement> apply(By selector) {
				List<WebElement> elements = EnhancedExpectedConditions.visibilityOfAllElementsLocatedBy(seleniumQueryObject.getBy()).apply(seleniumQueryObject.getWebDriver());
				if (elements != null && !elements.isEmpty()) {
					return elements;
				}
				return null;
			}
		}, "to have at least one visible.");
	}

}