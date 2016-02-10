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

import io.github.seleniumquery.SeleniumQueryConfig;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.SeleniumQueryWaitAndOrThen;
import io.github.seleniumquery.SeleniumQueryWaitEvaluateUntil;
import io.github.seleniumquery.wait.evaluators.IsEvaluator;
import io.github.seleniumquery.wait.getters.*;

/**
 * .waitUntil() functions.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class SqWaitUntil implements io.github.seleniumquery.SeleniumQueryWaitUntil {
	
	private SeleniumQueryObject seleniumQueryObject;
	
	private FluentSqWait fluentWait;
	
	/**
	 * Creates a waitUntil object for the given seleniumQueryObject, with timeout and polling interval
	 * as defined in the config files.
	 * @param seleniumQueryObject The object to wait for.
	 * @since 0.9.0
	 */
	public SqWaitUntil(SeleniumQueryObject seleniumQueryObject) {
		this(seleniumQueryObject, SeleniumQueryConfig.getWaitUntilTimeout(), SeleniumQueryConfig.getWaitUntilPollingInterval());
	}
	
	/**
	 * Creates a waitUntil object for the given seleniumQueryObject, with the given timeout and polling interval
	 * as defined in the config files.
	 * @param seleniumQueryObject The object to wait for.
	 * @param waitUntilTimeout Time, in ms, to wait.
	 * @since 0.9.0
     */
	public SqWaitUntil(SeleniumQueryObject seleniumQueryObject, long waitUntilTimeout) {
		this(seleniumQueryObject, waitUntilTimeout, SeleniumQueryConfig.getWaitUntilPollingInterval());
	}
	
	/**
	 * Creates a waitUntil object for the given seleniumQueryObject, with the given timeout and polling interval.
	 *
	 * @param seleniumQueryObject The object to wait for.
	 * @param waitUntilTimeout Time, in ms, to wait.
	 * @param waitUntilPollingInterval Interval, in ms, to poll the object.
	 * @since 0.9.0
     */
	public SqWaitUntil(SeleniumQueryObject seleniumQueryObject, long waitUntilTimeout, long waitUntilPollingInterval) {
		this.seleniumQueryObject = seleniumQueryObject;
		this.fluentWait = new FluentSqWait(waitUntilTimeout, waitUntilPollingInterval);
	}
	
	@Override
	public SeleniumQueryWaitAndOrThen is(String selector) {
		SeleniumQueryObject seleniumQueryObjectAfterWait = this.fluentWait.waitUntil(IsEvaluator.IS_EVALUATOR, selector, seleniumQueryObject, false);
		return new AndOrThen(seleniumQueryObjectAfterWait);
	}

	@Override
	public SeleniumQueryWaitEvaluateUntil<String> val() {
		return new EvaluateUntil<>(this.fluentWait, ValGetter.VAL_GETTER, seleniumQueryObject);
	}

	@Override
	public SeleniumQueryWaitEvaluateUntil<String> text() {
		return new EvaluateUntil<>(this.fluentWait, TextGetter.TEXT_GETTER, seleniumQueryObject);
	}

	@Override
	public SeleniumQueryWaitEvaluateUntil<String> attr(String attributeName) {
		return new EvaluateUntil<>(this.fluentWait, new AttrGetter(attributeName), seleniumQueryObject);
	}

	@Override
	public <T> SeleniumQueryWaitEvaluateUntil<T> prop(String propertyName) {
		return new EvaluateUntil<>(this.fluentWait, new PropGetter<T>(propertyName), seleniumQueryObject);
	}

	@Override
	public SeleniumQueryWaitEvaluateUntil<String> html() {
		return new EvaluateUntil<>(this.fluentWait, HtmlGetter.HTML_GETTER, seleniumQueryObject);
	}

	@Override
	public SeleniumQueryWaitEvaluateUntil<Integer> size() {
		return new EvaluateUntil<>(this.fluentWait, SizeGetter.SIZE_GETTER, seleniumQueryObject);
	}
	
}