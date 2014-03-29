package org.openqa.selenium.seleniumquery.wait.waituntil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Function;

public class ContainsText {

	@Deprecated
	public static List<WebElement> containsText(final SeleniumQueryObject seleniumQueryObject, final String text) {
		 return SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, List<WebElement>>() {
			@Override
			public List<WebElement> apply(By selector) {
				List<WebElement> elements = ExpectedConditions.presenceOfAllElementsLocatedBy(seleniumQueryObject.getBy()).apply(seleniumQueryObject.getWebDriver());
				if (elements != null && !elements.isEmpty()) {
					for (WebElement webElement : elements) {
						 if (!webElement.getText().contains(text)) {
							 return null;
						 }
					}
					return elements;
				}
				return null;
			}
		}, "to contain text \""+text+"\".");
	}
	
}
