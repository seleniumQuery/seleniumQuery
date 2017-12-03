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

package io.github.seleniumquery.functions.sq;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.io.github.seleniumquery.SeleniumQueryObjectMother
    .createStubSeleniumQueryObjectWithAtLeastOneElement;
import static testinfrastructure.testdouble.io.github.seleniumquery.SeleniumQueryObjectMother
    .createStubSeleniumQueryObjectWithElements;
import static testinfrastructure.testdouble.org.openqa.selenium.WebElementMother.createWebElementWithTag;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import io.github.seleniumquery.SeleniumQueryObject;

public class MapFunctionTest {

    @Test(expected = NullPointerException.class)
    public void null_function__should_throw_exception() {
        // given
        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithAtLeastOneElement();
        // when
        sqo.map(null);
        // then
        // should throw exception
    }

    @Test
    public void should_execute_function_for_each_element_of_seleniumQueryObject() {
        // given
        WebElement someSpan = createWebElementWithTag("span");
        WebElement someDiv = createWebElementWithTag("div");

        SeleniumQueryObject sqo = createStubSeleniumQueryObjectWithElements(someSpan, someDiv);
        // when
        List<String> tagNames = sqo.map(WebElement::getTagName);
        // then
        assertThat(tagNames, contains("span", "div"));
    }

}
