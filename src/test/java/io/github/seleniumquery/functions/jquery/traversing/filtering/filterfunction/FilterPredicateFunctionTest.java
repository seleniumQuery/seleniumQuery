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
import com.google.common.base.Predicate;
import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.testutils.FunctionsTestUtils;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.io.github.seleniumquery.SeleniumQueryObjectMother.createStubSeleniumQueryObjectWithAtLeastOneElement;
import static testinfrastructure.testdouble.io.github.seleniumquery.SeleniumQueryObjectMother.createStubSeleniumQueryObjectWithElements;
import static testinfrastructure.testdouble.org.openqa.selenium.WebElementMother.createWebElementWithTag;

public class FilterPredicateFunctionTest {

    private static final Predicate<WebElement> NULL_PREDICATE = null;
    private static final Predicate<WebElement> PREDICATE_DOES_NOT_MATTER_IN_THIS_TEST = null;

    FilterPredicateFunction filterPredicateFunction = new FilterPredicateFunction();

    @Test
    public void null_predicate__should_return_EMPTY_element_set() {
        // given
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithAtLeastOneElement();
        // when
        SeleniumQueryObject resultSQO = filterPredicateFunction.filter(targetSQO, NULL_PREDICATE);
        // then
        assertThat(resultSQO.get(), empty());
    }

    @Test
    public void resultSQO_should_have_targetSQO_as_previous_object__and_same_SQFunctions_as_targetSQO__and_same_WebDriver_as_targetSQO() {
        FunctionsTestUtils.verifyFunctionReturnsSQOWithCorrectWebDriverAndFunctionsAndPrevious(new Function<SeleniumQueryObject, SeleniumQueryObject>() {
            @Override
            public SeleniumQueryObject apply(SeleniumQueryObject targetSQO) {
                return filterPredicateFunction.filter(targetSQO, PREDICATE_DOES_NOT_MATTER_IN_THIS_TEST);
            }
        });
    }

    @Test
    public void resultSQO_should_onlyKeepElementsThatPassThePredicateFunction() {
        // given
        WebElement spanOne = createWebElementWithTag("span");
        WebElement notSpan = createWebElementWithTag("div");
        WebElement spanTwo = createWebElementWithTag("span");

        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithElements(spanOne, notSpan, spanTwo);
        // when
        Predicate<WebElement> keepSpansPredicate = new Predicate<WebElement>() {
            @Override
            public boolean apply(WebElement webElement) {
                return "span".equals(webElement.getTagName());
            }
        };
        SeleniumQueryObject resultSQO = filterPredicateFunction.filter(targetSQO, keepSpansPredicate);
        // then
        assertThat(resultSQO.get(), contains(spanOne, spanTwo));
    }

}