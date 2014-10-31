package io.github.seleniumquery.wait;

import io.github.seleniumquery.SQLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SeleniumQueryBy;
import io.github.seleniumquery.wait.evaluators.Evaluator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

/**
 * @author acdcjunior
 * @since 1.0.0
 */
public class SeleniumQueryFluentWait {
	
	private long waitUntilTimeout;
	private long waitUntilPollingInterval;

	public SeleniumQueryFluentWait(long waitUntilTimeout, long waitUntilPollingInterval) {
		this.waitUntilTimeout = waitUntilTimeout;
		this.waitUntilPollingInterval = waitUntilPollingInterval;
	}

	public <T> SeleniumQueryObject waitUntil(final Evaluator<T> evaluator, final T value,
																	SeleniumQueryObject seleniumQueryObject, final boolean negated) {
		final WebDriver driver = seleniumQueryObject.getWebDriver();
		final SeleniumQueryBy by = seleniumQueryObject.getBy();
		List<WebElement> elements = fluentWait(seleniumQueryObject, new WaitFunction<T>(driver, value, evaluator, by, negated), "to "+evaluator.stringFor(value));
		return SQLocalFactory.createWithInvalidSelector(seleniumQueryObject.getWebDriver(), elements, seleniumQueryObject);
	}

	/**
	 * @since 1.0.0
	 */
	private <T> T fluentWait(SeleniumQueryObject seleniumQueryObject, Function<By, T> function, String reason) {
		try {
			return new FluentWait<By>(seleniumQueryObject.getBy())
							.withTimeout(waitUntilTimeout, TimeUnit.MILLISECONDS)
								.pollingEvery(waitUntilPollingInterval, TimeUnit.MILLISECONDS)
									.ignoring(StaleElementReferenceException.class)
										.ignoring(NoSuchElementException.class)
											.until(function);
		} catch (TimeoutException sourceException) {
			throw new SeleniumQueryWaitException(sourceException, seleniumQueryObject, reason);
		}
	}
	
}

class WaitFunction<T> implements Function<By, List<WebElement>> {
	private final WebDriver driver;
	private final T value;
	private final Evaluator<T> evaluator;
	private final SeleniumQueryBy by;
	private final boolean negated;

	WaitFunction(WebDriver driver, T value, Evaluator<T> evaluator, SeleniumQueryBy by, boolean negated) {
		this.driver = driver;
		this.value = value;
		this.evaluator = evaluator;
		this.by = by;
		this.negated = negated;
	}

	@Override
	public List<WebElement> apply(By selector) {
		List<WebElement> elements = driver.findElements(by);
		final boolean evaluate = evaluator.evaluate(driver, elements, value);
		if (!negated) {
			if (!evaluate) {
				return null;
			}
		} else {
			if (evaluate) {
				return null;
			}
		}
		return elements;
	}
}