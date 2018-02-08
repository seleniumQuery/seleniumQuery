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

import io.github.seleniumquery.SeleniumQueryFluentAndOrThen;
import io.github.seleniumquery.SeleniumQueryFluentFunction;
import io.github.seleniumquery.SeleniumQueryObject;

/**
 * A builder class that allows to specify the next step after waiting for some condition: to wait for more
 * (<code>.and()</code>) or to do something else (<code>.then()</code>).
 *
 * @author acdcjunior
 * @since 0.9.0
 */
class AndOrThen implements SeleniumQueryFluentAndOrThen {

	private final SeleniumQueryObject seleniumQueryObject;
    private final FluentFunction fluentFunction;

    AndOrThen(SeleniumQueryObject seleniumQueryObject, FluentFunction fluentFunction) {
		this.seleniumQueryObject = seleniumQueryObject;
        this.fluentFunction = fluentFunction;
    }

	@Override
	public SeleniumQueryFluentFunction and() {
		return new SqFluentFunction(this.seleniumQueryObject, this.fluentFunction);
	}

	@Override
	public SeleniumQueryObject then() {
		return this.seleniumQueryObject;
	}

	@Override
	public String toString() {
		return this.seleniumQueryObject.toString();
	}

}
