/*
 * Copyright (c) 2017 seleniumQuery authors
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

package io.github.seleniumquery.internal.fluentfunctions.assertthat;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.fluentfunctions.assertthat.SeleniumQueryAssertionError;
import io.github.seleniumquery.internal.fluentfunctions.FluentFunction;
import io.github.seleniumquery.internal.fluentfunctions.FluentBehaviorModifier;
import io.github.seleniumquery.internal.fluentfunctions.evaluators.Evaluator;

public class FluentAssertThat implements FluentFunction {

    @Override
    public <T> SeleniumQueryObject apply(Evaluator<T> evaluator, T value, SeleniumQueryObject sqo,
                                         FluentBehaviorModifier fluentBehaviorModifier) {
        boolean passedAssertion = evaluator.evaluate(sqo, value);
        if (fluentBehaviorModifier.isNotExpectedBehavior(passedAssertion)) {
            throw new SeleniumQueryAssertionError("Failed assertion: "+sqo+".assertThat()."+evaluator.stringFor(value, fluentBehaviorModifier));
        }
        return sqo;
    }

}
