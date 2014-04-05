package org.openqa.selenium.seleniumquery.wait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryConfig;
import org.openqa.selenium.seleniumquery.SeleniumQueryLocalFactory;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.seleniumquery.wait.quantifier.Quantifier;
import org.openqa.selenium.seleniumquery.wait.restrictor.Restrictor;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

/**
 * @author acdcjunior
 * @since 0.1.0
 */
public class SeleniumQueryFluentWait {

	/**
	 * @author acdcjunior
	 * @since 0.1.0
	 */
	public static <T> T fluentWait(SeleniumQueryObject seleniumQueryObject, Function<By, T> function, String reason) {
		try {
			return new FluentWait<By>(seleniumQueryObject.getBy())
					.withTimeout(SeleniumQueryConfig.getWaitTimeoutInSeconds(), TimeUnit.SECONDS)
					.pollingEvery(SeleniumQueryConfig.getWaitPollingInMillisseconds(), TimeUnit.MILLISECONDS)
					.ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class)
					.until(function);
		} catch (TimeoutException sourceException) {
			throw new SeleniumQueryWaitException(sourceException, seleniumQueryObject, reason);
		}
	}

	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public static SeleniumQueryObject queryUntilIs(final Quantifier quantifier, final Restrictor restrictor, final SeleniumQueryObject seleniumQueryObject) {
		List<WebElement> elements = fluentWait(seleniumQueryObject, new Function<By, List<WebElement>>() {
			@Override
			public List<WebElement> apply(By selector) {
				List<WebElement> elements = seleniumQueryObject.getWebDriver().findElements(seleniumQueryObject.getBy());
				int totalFulfillingRestriction = 0;
				for (WebElement webElement : elements) {
					if (restrictor.fulfillsRestriction(webElement)) {
						totalFulfillingRestriction++;
					}
				}
				if (!quantifier.isQuantityGoodEnough(elements.size(), totalFulfillingRestriction)) {
					return null;
				}
				return elements;
			}
		}, "to have "+quantifier+" element(s) "+restrictor+".");
		return SeleniumQueryLocalFactory.getInstance().create(seleniumQueryObject, elements);
	}
	
	/**
	 * @author acdcjunior
	 * @since 0.4.0
	 */
	public static SeleniumQueryObject waitUntilIs(final Quantifier quantifier, final Restrictor restrictor, final SeleniumQueryObject seleniumQueryObject) {
		SeleniumQueryFluentWait.fluentWait(seleniumQueryObject, new Function<By, Boolean>() {
			@Override
			public Boolean apply(By selector) {
				int totalFulfillingRestriction = 0;
				for (WebElement webElement : seleniumQueryObject) {
					if (restrictor.fulfillsRestriction(webElement)) {
						totalFulfillingRestriction++;
					}
				}
				return quantifier.isQuantityGoodEnough(seleniumQueryObject.size(), totalFulfillingRestriction);
			}
		}, "to have "+quantifier+" element(s) "+restrictor+".");
		return seleniumQueryObject;
	}

}