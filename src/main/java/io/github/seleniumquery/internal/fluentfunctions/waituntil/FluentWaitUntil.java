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

import io.github.seleniumquery.SeleniumQueryConfig;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.fluentfunctions.waituntil.SeleniumQueryTimeoutException;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.FluentFunction;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author acdcjunior
 * @since 0.9.0
 */
public class FluentWaitUntil implements FluentFunction {

    private static final Object ANY_NON_NULL_VALUE = Object.class;

    private static final Object NO_MORE_WAITING = ANY_NON_NULL_VALUE;
    private static final Object CONTINUE_WAITING = null;

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
    public <T> SeleniumQueryObject apply(final Evaluator<T> evaluator,
                                         final T value,
                                         final SeleniumQueryObject seleniumQueryObject,
                                         final FluentBehaviorModifier fluentBehaviorModifier) {

        Function<Object, Object> waitFunction = unused -> {
            // refresh sqo
            seleniumQueryObject.refresh();
            // check if it now passes the required evaluation
            boolean passedEvaluation = evaluator.evaluate(seleniumQueryObject, value);

            if (fluentBehaviorModifier.isExpectedBehavior(passedEvaluation)) {
                return NO_MORE_WAITING;
            } else {
                return CONTINUE_WAITING;
            }
        };

        try {
            new FluentWait<>(ANY_NON_NULL_VALUE)
                            .withTimeout(waitUntilTimeout, TimeUnit.MILLISECONDS)
                                .pollingEvery(waitUntilPollingInterval, TimeUnit.MILLISECONDS)
                                    .ignoring(StaleElementReferenceException.class)
                                        .ignoring(NoSuchElementException.class)
                                            .until(waitFunction);
        } catch (TimeoutException sourceException) {
            throw new SeleniumQueryTimeoutException(sourceException, seleniumQueryObject, "to .waitUntil()."+evaluator.stringFor(value, fluentBehaviorModifier));
        }

        return seleniumQueryObject;
	}

}
