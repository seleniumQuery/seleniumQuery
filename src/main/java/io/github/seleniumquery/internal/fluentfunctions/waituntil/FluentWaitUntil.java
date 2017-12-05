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

package io.github.seleniumquery.internal.fluentfunctions.waituntil;

import static io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier.REGULAR_BEHAVIOR;

import io.github.seleniumquery.SeleniumQueryConfig;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.SeleniumQueryInvalidBy;
import io.github.seleniumquery.fluentfunctions.waituntil.SeleniumQueryTimeoutException;
import io.github.seleniumquery.internal.SqObjectFactory;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.FluentFunction;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author acdcjunior
 * @since 0.9.0
 */
public class FluentWaitUntil implements FluentFunction {

	private long waitUntilTimeout;
	private long waitUntilPollingInterval;

    /**
     * Creates a waitUntil object for the given seleniumQueryObject, with timeout and polling interval
     * as defined in the config files.
     * @since 0.9.0
     */
    public FluentWaitUntil() {
        this(SeleniumQueryConfig.getWaitUntilTimeout(), SeleniumQueryConfig.getWaitUntilPollingInterval());
	}

    /**
     * Creates a waitUntil object for the given seleniumQueryObject, with the given timeout and polling interval
     * as defined in the config files.
     * @param waitUntilTimeout Timeout in ms.
     * @since 0.9.0
     */
    public FluentWaitUntil(long waitUntilTimeout) {
        this(waitUntilTimeout, SeleniumQueryConfig.getWaitUntilPollingInterval());
	}

    /**
     * Creates a waitUntil object for the given seleniumQueryObject, with the given timeout and polling interval.
     * @param waitUntilTimeout Timeout in ms.
     * @param waitUntilPollingInterval Polling interval in ms.
     * @since 0.9.0
     */
    public FluentWaitUntil(long waitUntilTimeout, long waitUntilPollingInterval) {
		this.waitUntilTimeout = waitUntilTimeout;
		this.waitUntilPollingInterval = waitUntilPollingInterval;
	}

	@Override
    public <T> SeleniumQueryObject apply(Evaluator<T> evaluator, T value, SeleniumQueryObject sqo, FluentBehaviorModifier negated) {
        return this.waitUntil(evaluator, value, sqo, negated);
    }

    private <T> SeleniumQueryObject waitUntil(final Evaluator<T> evaluator, final T value,
                                              SeleniumQueryObject seleniumQueryObject,
                                              FluentBehaviorModifier fluentBehaviorModifier) {
		final WebDriver driver = seleniumQueryObject.getWebDriver();
		final By by = seleniumQueryObject.getBy();

        FluentSqWaitFunction<T> fluentSqWaitFunction = new FluentSqWaitFunction<>(driver, value, evaluator, by, fluentBehaviorModifier);

        List<WebElement> elements = fluentWait(
            seleniumQueryObject,
            fluentSqWaitFunction,
            "to .waitUntil()."+evaluator.stringFor(value, fluentBehaviorModifier)
        );

		return SqObjectFactory.instance().create(
				seleniumQueryObject.getWebDriver(),
                new SeleniumQueryInvalidBy(seleniumQueryObject, ".waitUntil()." + evaluator.stringFor(value, fluentBehaviorModifier)),
                elements, seleniumQueryObject
        );
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


class FluentSqWaitFunction<T> implements Function<By, List<WebElement>> {

    private final WebDriver driver;
    private final T value;
    private final Evaluator<T> evaluator;
    private final By by;
    private final FluentBehaviorModifier fluentBehaviorModifier;

    FluentSqWaitFunction(WebDriver driver, T value, Evaluator<T> evaluator, By by, FluentBehaviorModifier fluentBehaviorModifier) {
        this.driver = driver;
        this.value = value;
        this.evaluator = evaluator;
        this.by = by;
        this.fluentBehaviorModifier = fluentBehaviorModifier;
    }

    @Override
    public List<WebElement> apply(By selector) {
        List<WebElement> elements = driver.findElements(by);
        final boolean evaluate = evaluator.evaluate(driver, elements, value);
        // TODO use function from fluentBehaviorModifier instead of if
        if (fluentBehaviorModifier == REGULAR_BEHAVIOR) {
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
