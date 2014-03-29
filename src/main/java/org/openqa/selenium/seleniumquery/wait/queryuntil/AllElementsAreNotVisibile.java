package org.openqa.selenium.seleniumquery.wait.queryuntil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;

import com.google.common.base.Function;

public class AllElementsAreNotVisibile {
	
	public void allElementsAreNotVisible(final SeleniumQueryObject seleniumQueryObject) {
		SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				List<WebElement> elements = seleniumQueryObject.getWebDriver().findElements(seleniumQueryObject.getBy());
				for (WebElement webElement : elements) {
					if (webElement.isDisplayed()) {
						return false;
					}
				}
				return true;
			}
		}, "to have all elements not be not visible.");
	}

}