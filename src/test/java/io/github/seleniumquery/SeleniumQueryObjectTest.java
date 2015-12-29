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

package io.github.seleniumquery;

import com.google.common.base.Predicate;
import io.github.seleniumquery.functions.SeleniumQueryFunctions;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static testinfrastructure.testdouble.Dummies.createDummyWebElementPredicate;
import static testinfrastructure.testdouble.SeleniumQueryObjectMother.createDummySeleniumQueryObject;
import static testinfrastructure.testdouble.SeleniumQueryObjectMother.createStubSeleniumQueryObjectWithSeleniumQueryFunctions;

public class SeleniumQueryObjectTest {

    public static SeleniumQueryFunctions createMockSeleniumQueryFunctions() {
        return mock(SeleniumQueryFunctions.class);
    }

    @Test
    public void propRead() {
        // given
        SeleniumQueryFunctions seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        String propertyName = "propertyName";
        String configuredPropertyValue = "propertyValue";
        given(seleniumQueryFunctions.propRead(seleniumQueryObject, propertyName)).willReturn(configuredPropertyValue);
        // when
        Object returnedPropertyValue = seleniumQueryObject.prop(propertyName);
        // then
        assertThat((String) returnedPropertyValue, is(configuredPropertyValue));
    }

    @Test
    public void propWrite() {
        // given
        SeleniumQueryFunctions seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        String propertyName = "propertyName";
        String propertyValue = "propertyValue";
        SeleniumQueryObject configuredReturningObject = createDummySeleniumQueryObject();
        given(seleniumQueryFunctions.propWrite(seleniumQueryObject, propertyName, propertyValue)).willReturn(configuredReturningObject);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.prop(propertyName, propertyValue);
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

    @Test
    public void valRead() {
        // given
        SeleniumQueryFunctions seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        String configuredValue = "configuredValue";
        given(seleniumQueryFunctions.valRead(seleniumQueryObject)).willReturn(configuredValue);
        // when
        String returnedValue = seleniumQueryObject.val();
        // then
        assertThat(returnedValue, is(configuredValue));
    }

    @Test
    public void valWriteString() {
        // given
        SeleniumQueryFunctions seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        String propertyValue = "propertyValue";
        SeleniumQueryObject configuredReturningObject = createDummySeleniumQueryObject();
        given(seleniumQueryFunctions.valWrite(seleniumQueryObject, propertyValue)).willReturn(configuredReturningObject);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.val(propertyValue);
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

    @Test
    public void valWriteNumber() {
        // given
        SeleniumQueryFunctions seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);

        Number propertyValue = 1.0;
        SeleniumQueryObject configuredReturningObject = createDummySeleniumQueryObject();
        given(seleniumQueryFunctions.valWrite(seleniumQueryObject, propertyValue)).willReturn(configuredReturningObject);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.val(propertyValue);
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

    @Test
    public void filterFunction() {
        // given
        SeleniumQueryFunctions seleniumQueryFunctions = createMockSeleniumQueryFunctions();
        SeleniumQueryObject seleniumQueryObject = createStubSeleniumQueryObjectWithSeleniumQueryFunctions(seleniumQueryFunctions);
        SeleniumQueryObject configuredReturningObject = createDummySeleniumQueryObject();

        Predicate<WebElement> filterFunction = createDummyWebElementPredicate();
        given(seleniumQueryFunctions.filterFunction(seleniumQueryObject, filterFunction)).willReturn(configuredReturningObject);
        // when
        SeleniumQueryObject returnedObject = seleniumQueryObject.filter(filterFunction);
        // then
        assertThat(returnedObject, is(configuredReturningObject));
    }

}