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

package io.github.seleniumquery.internal.fluentfunctions;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.SeleniumQueryWaitAndOrThen;
import io.github.seleniumquery.SeleniumQueryWaitEvaluateUntil;
import io.github.seleniumquery.SeleniumQueryWaitUntil;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.IsEvaluator;
import io.github.seleniumquery.internal.fluentfunctions.getters.AttrGetter;
import io.github.seleniumquery.internal.fluentfunctions.getters.HtmlGetter;
import io.github.seleniumquery.internal.fluentfunctions.getters.PropGetter;
import io.github.seleniumquery.internal.fluentfunctions.getters.SizeGetter;
import io.github.seleniumquery.internal.fluentfunctions.getters.TextGetter;
import io.github.seleniumquery.internal.fluentfunctions.getters.ValGetter;

/**
 * .waitUntil() functions.
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class SqFluentFunction implements SeleniumQueryWaitUntil {

	private SeleniumQueryObject seleniumQueryObject;

	private FluentFunction fluentFunction;

	/**
	 * Creates a waitUntil object for the given seleniumQueryObject, with the given fluent function.
	 *
	 * @param seleniumQueryObject The object to wait for.
	 * @param fluentFunction The fluent function to be evaluated.
     * @since 0.9.0
     */
	public SqFluentFunction(SeleniumQueryObject seleniumQueryObject, FluentFunction fluentFunction) {
		this.seleniumQueryObject = seleniumQueryObject;
		this.fluentFunction = fluentFunction;
	}

	@Override
	public SeleniumQueryWaitAndOrThen is(String selector) {
        SeleniumQueryObject sqoAfter = this.fluentFunction.apply(
            IsEvaluator.IS_EVALUATOR, selector, seleniumQueryObject, FluentBehaviorModifier.REGULAR_BEHAVIOR
        );
		return new AndOrThen(sqoAfter, this.fluentFunction);
	}

	@Override
	public SeleniumQueryWaitEvaluateUntil<String> val() {
		return new ApplyUntil<>(this.fluentFunction, ValGetter.VAL_GETTER, seleniumQueryObject);
	}

	@Override
	public SeleniumQueryWaitEvaluateUntil<String> text() {
		return new ApplyUntil<>(this.fluentFunction, TextGetter.TEXT_GETTER, seleniumQueryObject);
	}

	@Override
	public SeleniumQueryWaitEvaluateUntil<String> attr(String attributeName) {
		return new ApplyUntil<>(this.fluentFunction, new AttrGetter(attributeName), seleniumQueryObject);
	}

	@Override
	public <T> SeleniumQueryWaitEvaluateUntil<T> prop(String propertyName) {
		return new ApplyUntil<>(this.fluentFunction, new PropGetter<T>(propertyName), seleniumQueryObject);
	}

	@Override
	public SeleniumQueryWaitEvaluateUntil<String> html() {
		return new ApplyUntil<>(this.fluentFunction, HtmlGetter.HTML_GETTER, seleniumQueryObject);
	}

	@Override
	public SeleniumQueryWaitEvaluateUntil<Integer> size() {
		return new ApplyUntil<>(this.fluentFunction, SizeGetter.SIZE_GETTER, seleniumQueryObject);
	}

}
