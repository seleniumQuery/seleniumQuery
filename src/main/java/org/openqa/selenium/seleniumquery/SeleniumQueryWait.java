package org.openqa.selenium.seleniumquery;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.functions.ValFunction;
import org.openqa.selenium.seleniumquery.waituntil.IsPresent;
import org.openqa.selenium.seleniumquery.waituntil.SeleniumQueryFluentWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class SeleniumQueryWait {

	private SeleniumQueryObject seleniumQueryObject;
	
	public SeleniumQueryWait(SeleniumQueryObject seleniumQueryObject) {
		this.seleniumQueryObject = seleniumQueryObject;
	}

	public SeleniumQueryObject isPresent() {
		List<WebElement> presentElements = IsPresent.isPresent(this.seleniumQueryObject);
		seleniumQueryObject.setElements(presentElements);
		return seleniumQueryObject;
	}

	public SeleniumQueryObject isNotPresent() {
		SeleniumQueryFluentWait.fluentWait(this.seleniumQueryObject, new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				return seleniumQueryObject.getWebDriver().findElements(seleniumQueryObject.getBy()).size() == 0;
			}
		}, "to be not present.");
		return seleniumQueryObject;
	}
	
	public SeleniumQueryObject isVisibleAndEnabled() {
		List<WebElement> elements = SeleniumQueryFluentWait.fluentWait(this.seleniumQueryObject, new Function<By, List<WebElement>>() {
			@Override
			public List<WebElement> apply(By selector) {
				List<WebElement> elements = visibilityOfAllElementsLocatedBy(seleniumQueryObject.getBy()).apply(seleniumQueryObject.getWebDriver());
				if (elements != null && !elements.isEmpty()) {
					for (WebElement webElement : elements) {
						 if (!webElement.isEnabled()) {
							 return null;
						 }
					}
					return elements;
				}
				return null;
			}
		}, "to be visible and enabled.");
		seleniumQueryObject.setElements(elements);
		return seleniumQueryObject;
	}
	
	public SeleniumQueryObject isVisible() {
		List<WebElement> element = SeleniumQueryFluentWait.fluentWait(this.seleniumQueryObject, new Function<By, List<WebElement>>() {
			@Override
			public List<WebElement> apply(By selector) {
				 // can be null (will wait again), or the element
				return visibilityOfAllElementsLocatedBy(seleniumQueryObject.getBy()).apply(seleniumQueryObject.getWebDriver());
			}
		}, "to be visible.");
		seleniumQueryObject.setElements(element);
		return seleniumQueryObject;
	}
	
	public SeleniumQueryObject isNotVisible() {
		SeleniumQueryFluentWait.fluentWait(this.seleniumQueryObject, new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				return ExpectedConditions.invisibilityOfElementLocated(seleniumQueryObject.getBy()).apply(seleniumQueryObject.getWebDriver());
			}
		}, "to be not visible.");
		return seleniumQueryObject;
	}
	
	public SeleniumQueryObject containsText(final String text) {
		List<WebElement> element = SeleniumQueryFluentWait.fluentWait(this.seleniumQueryObject, new Function<By, List<WebElement>>() {
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
		seleniumQueryObject.setElements(element);
		return seleniumQueryObject;
	}
	
	public SeleniumQueryObject seconds(final int timeToWait) {
		final Long finalTime = System.currentTimeMillis() + timeToWait * 1000;
		new FluentWait<By>(seleniumQueryObject.getBy()).withTimeout(timeToWait+2, TimeUnit.SECONDS)
							  .pollingEvery(timeToWait*200, TimeUnit.MILLISECONDS)
							  .until(new Function<By, Boolean>() {
			@Override public Boolean apply(By selector) { return System.currentTimeMillis() > finalTime; }
		});
		return seleniumQueryObject;
	}
	
	public SeleniumQueryObject valueIs(final String value) {
		return this.valueIsOrIsNot(value, true);
	}
	
	public SeleniumQueryObject valueIsNot(final String value) {
		return this.valueIsOrIsNot(value, false);
	}
	
	private SeleniumQueryObject valueIsOrIsNot(final String value, final boolean shouldValueBeEqual) {
		SeleniumQueryFluentWait.fluentWait(this.seleniumQueryObject, new Function<By, List<WebElement>>() {
			@Override
			public List<WebElement> apply(By selector) {
				List<WebElement> es = ExpectedConditions.presenceOfAllElementsLocatedBy(seleniumQueryObject.getBy()).apply(seleniumQueryObject.getWebDriver());
				if (es != null) {
					// notice the element is set here, thus not necessary at the end of fluentWait
					seleniumQueryObject.setElements(es);
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
		return seleniumQueryObject;
	}
	
	
	
	
	
	
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