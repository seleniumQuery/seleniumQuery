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

package io.github.seleniumquery.functions.jquery.traversing.filtering.filterfunction;

import com.google.common.base.Function;
import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.Test;
import testinfrastructure.testutils.FunctionsTestUtils;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.*;
import static testinfrastructure.testdouble.SeleniumQueryObjectMother.createStubSeleniumQueryObjectWithAtLeastOneElement;

public class FilterSelectorFunctionTest {

    private static final String NULL_SELECTOR = null;
    private static final String SELECTOR_DOES_NOT_MATTER_IN_THIS_TEST = null;

    FilterSelectorFunction filterSelectorFunction = new FilterSelectorFunction();

    @Test
    public void null_selector__should_return_EMPTY_element_set() {
        // given
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithAtLeastOneElement();
        // when
        SeleniumQueryObject resultSQO = filterSelectorFunction.filter(targetSQO, NULL_SELECTOR);
        // then
        assertThat(resultSQO.get(), empty());
    }

    @Test
    public void emptyString_selector__should_return_EMPTY_element_set() {
        // given
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithAtLeastOneElement();
        // when
        SeleniumQueryObject resultSQO = filterSelectorFunction.filter(targetSQO, "");
        // then
        assertThat(resultSQO.get(), empty());
    }

    @Test
    public void resultSQO_should_have_targetSQO_as_previous_object__and_same_SQFunctions_as_targetSQO__and_same_WebDriver_as_targetSQO() {
        FunctionsTestUtils.verifyFunctionReturnsSQOWithCorrectWebDriverAndFunctionsAndPrevious(new Function<SeleniumQueryObject, SeleniumQueryObject>() {
            @Override
            public SeleniumQueryObject apply(SeleniumQueryObject targetSQO) {
                return filterSelectorFunction.filter(targetSQO, SELECTOR_DOES_NOT_MATTER_IN_THIS_TEST);
            }
        });
    }

    // TODO filter(this, "selector") --> should keep everyone that matches the isFunction("selector")

}