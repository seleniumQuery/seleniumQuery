package org.openqa.selenium.seleniumquery.wait;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExtraExpectedConditions {

	public static ExpectedCondition<List<WebElement>> visibilityOfAllElementsLocatedBy(final By locator) {
		return new ExpectedCondition<List<WebElement>>() {
			@Override
			public List<WebElement> apply(WebDriver driver) {
				try {
					List<WebElement> findElements = driver.findElements(locator);
					for (WebElement webElement : findElements) {
						if (!webElement.isDisplayed()) {
							return null;
						}
					}
					return findElements;
				} catch (StaleElementReferenceException e) {
					return null;
				}
			}
	
			@Override
			public String toString() {
				return "visibility of all elements located by " + locator;
			}
		};
	}

}