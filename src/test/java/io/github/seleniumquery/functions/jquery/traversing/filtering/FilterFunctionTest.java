/*
 * Copyright (c) 2015 seleniumQuery authors
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

package io.github.seleniumquery.functions.jquery.traversing.filtering;

import com.google.common.base.Predicate;
import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.Dummies.createDummyWebElement;
import static testinfrastructure.testdouble.SeleniumQueryObjectMother.createStubSeleniumQueryObject;
import static testinfrastructure.testdouble.SeleniumQueryObjectMother.createStubSeleniumQueryObjectWithElements;

public class FilterFunctionTest {

    private static final Predicate<WebElement> NULL_PREDICATE = null;

    FilterFunction filterFunction = new FilterFunction();

    @Test
    public void null_predicate__should_return_same_elements() {
        // given
        WebElement dummyWebElement = createDummyWebElement();
        WebElement dummyWebElement2 = createDummyWebElement();
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithElements(dummyWebElement, dummyWebElement2);
        // when
        SeleniumQueryObject resultSQO = filterFunction.filter(targetSQO, NULL_PREDICATE);
        // then
        assertThat(resultSQO.get(), containsInAnyOrder(dummyWebElement, dummyWebElement2));
    }

    @Test
    public void resultSQO_should_have_targetSQO_as_previous_object() {
        // given
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObject();
        // when
        SeleniumQueryObject resultSQO = filterFunction.filter(targetSQO, NULL_PREDICATE);
        // then
        assertThat(resultSQO.end(), is(targetSQO));
    }

    @Test
    public void resultSQO_should_have_same_SQFunctions_as_targetSQO() {
        // given
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObject();
        // when
        SeleniumQueryObject resultSQO = filterFunction.filter(targetSQO, NULL_PREDICATE);
        // then
        assertThat(resultSQO.getSeleniumQueryFunctions(), is(targetSQO.getSeleniumQueryFunctions()));
    }

}