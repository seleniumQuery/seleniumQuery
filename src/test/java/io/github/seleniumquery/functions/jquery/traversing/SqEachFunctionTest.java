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

package io.github.seleniumquery.functions.jquery.traversing;

import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.io.github.seleniumquery.SeleniumQueryObjectMother.*;
import static testinfrastructure.testdouble.org.openqa.selenium.WebElementMother.createWebElementWithTag;

public class SqEachFunctionTest {

    private static final SeleniumQueryObject.EachFunction NULL_FUNCTION = null;
    private static final SeleniumQueryObject.EachFunction NON_NULL_EACH_FUNCTION_DOES_NOT_MATTER_IN_THIS_TEST = createDummyEachFunction();

    private SqEachFunction sqEachFunction = new SqEachFunction();

    @Test(expected = NullPointerException.class)
    public void null_function__should_throw_exception() {
        // given
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithAtLeastOneElement();
        // when
        sqEachFunction.each(targetSQO, NULL_FUNCTION);
        // then
        // should throw exception
    }

    @Test
    public void resultSQO_should_be_targetSQO() {
        // given
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithAtLeastOneElement();
        // when
        SeleniumQueryObject resultSQO = sqEachFunction.each(targetSQO, NON_NULL_EACH_FUNCTION_DOES_NOT_MATTER_IN_THIS_TEST);
        // then
        assertThat(resultSQO, is(targetSQO));
    }

    @Test
    public void should_execute_function_for_each_element_of_SQObject() {
        // given
        WebElement someSpan = createWebElementWithTag("span");
        WebElement someDiv = createWebElementWithTag("div");

        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithElements(someSpan, someDiv);

        final Map<Integer, WebElement> actual = new HashMap<>();
        SeleniumQueryObject.EachFunction eachFunctionSpy = new SeleniumQueryObject.EachFunction() {
            @Override
            public boolean apply(int index, WebElement element) {
                actual.put(index, element);
                return true;
            }
        };
        // when
        sqEachFunction.each(targetSQO, eachFunctionSpy);
        // then
        Map<Integer, WebElement> expected = new HashMap<>();
        expected.put(1, someDiv);
        expected.put(0, someSpan);

        assertThat(actual, is(expected));
    }

    @Test
    public void should_stop_execution_if_eachFunction_returns_false() {
        // given
        WebElement someSpan = createWebElementWithTag("span");
        WebElement someDiv = createWebElementWithTag("div");

        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithElements(someSpan, someDiv);

        final Map<Integer, WebElement> actual = new HashMap<>();
        SeleniumQueryObject.EachFunction eachFunctionSpy = new SeleniumQueryObject.EachFunction() {
            @Override
            public boolean apply(int index, WebElement element) {
                actual.put(index, element);
                return false;
            }
        };
        // when
        sqEachFunction.each(targetSQO, eachFunctionSpy);
        // then
        Map<Integer, WebElement> expected = new HashMap<>();
        expected.put(0, someSpan);

        assertThat(actual, is(expected));
    }

}