/*
 * Copyright (c) 2016 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.wait;

import com.google.common.base.Function;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.SqObjectFactory;
import io.github.seleniumquery.wait.evaluators.Evaluator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author acdcjunior
 * @since 0.9.0
 */
class SeleniumQueryFluentWait {
	
	private long waitUntilTimeout;
	private long waitUntilPollingInterval;

	SeleniumQueryFluentWait(long waitUntilTimeout, long waitUntilPollingInterval) {
		this.waitUntilTimeout = waitUntilTimeout;
		this.waitUntilPollingInterval = waitUntilPollingInterval;
	}

	public <T> SeleniumQueryObject waitUntil(final Evaluator<T> evaluator, final T value,
																	SeleniumQueryObject seleniumQueryObject, final boolean negated) {
		final WebDriver driver = seleniumQueryObject.getWebDriver();
		final By by = seleniumQueryObject.getBy();
		List<WebElement> elements = fluentWait(seleniumQueryObject, new WaitFunction<>(driver, value, evaluator, by, negated), "to "+evaluator.stringFor(value));
        return SqObjectFactory.instance().createWithInvalidSelector(seleniumQueryObject.getWebDriver(), elements, seleniumQueryObject);
    }

	/**
	 * @since 0.9.0
	 */
	private <T> T fluentWait(SeleniumQueryObject seleniumQueryObject, Function<By, T> function, String reason) {
		try {
			return new FluentWait<>(seleniumQueryObject.getBy())
							.withTimeout(waitUntilTimeout, TimeUnit.MILLISECONDS)
								.pollingEvery(waitUntilPollingInterval, TimeUnit.MILLISECONDS)
									.ignoring(StaleElementReferenceException.class)
										.ignoring(NoSuchElementException.class)
											.until(function);
		} catch (TimeoutException sourceException) {
			throw new SeleniumQueryTimeoutException(sourceException, seleniumQueryObject, reason);
		}
	}
	
}

class WaitFunction<T> implements Function<By, List<WebElement>> {

	private final WebDriver driver;
	private final T value;
	private final Evaluator<T> evaluator;
	private final By by;
	private final boolean negated;

	WaitFunction(WebDriver driver, T value, Evaluator<T> evaluator, By by, boolean negated) {
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