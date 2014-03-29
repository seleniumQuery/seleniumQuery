package org.openqa.selenium.seleniumquery.wait.queryuntil;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.functions.ValFunction;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryFluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Function;

public class ValueIsOrIsNot {

	static SeleniumQueryObject valueIsOrIsNot(final SeleniumQueryObject seleniumQueryObject, final String value, final boolean shouldValueBeEqual) {
		List<WebElement> es = SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, List<WebElement>>() {
			@Override
			public List<WebElement> apply(By selector) {
				List<WebElement> es = ExpectedConditions.presenceOfAllElementsLocatedBy(seleniumQueryObject.getBy()).apply(seleniumQueryObject.getWebDriver());
				if (es != null) {
					if (!es.isEmpty()) {
						for (WebElement webElement : es) {
							boolean valueEquals = ValFunction.val(Arrays.asList(webElement)).equals(value);
							if ((shouldValueBeEqual && !valueEquals) || (!shouldValueBeEqual && valueEquals)) {
								return null;
							}
						}
						return es;
					}
				}
				return null;
			}
		}, "to"+ (shouldValueBeEqual ? "" : " not") + " have value \""+value+"\".");
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), es);
	}
	
}
