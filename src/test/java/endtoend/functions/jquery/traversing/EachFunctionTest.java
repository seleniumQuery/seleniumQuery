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

package endtoend.functions.jquery.traversing;

import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import java.util.HashMap;
import java.util.Map;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EachFunctionTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    private WebElement d1;
    private WebElement d2;

    @Before
    public void setUp() {
        this.d1 = $("#d1").get(0);
        this.d2 = $("#d2").get(0);
    }

    @Test
    public void should_execute_function_for_each_element_of_SQObject() {
        // given
        final Map<Integer, WebElement> actual = new HashMap<>();
        SeleniumQueryObject.EachFunction eachFunctionSpy = new SeleniumQueryObject.EachFunction() {
            @Override
            public boolean apply(int index, WebElement element) {
                actual.put(index, element);
                return true;
            }
        };
        // when
        $("div").each(eachFunctionSpy);
        // then
        Map<Integer, WebElement> expected = new HashMap<>();
        expected.put(0, d1);
        expected.put(1, d2);

        assertThat(actual, is(expected));
    }

    @Test
    public void should_stop_execution_if_eachFunction_returns_false() {
        // given
        final Map<Integer, WebElement> actual = new HashMap<>();
        SeleniumQueryObject.EachFunction eachFunctionSpy = new SeleniumQueryObject.EachFunction() {
            @Override
            public boolean apply(int index, WebElement element) {
                actual.put(index, element);
                return false;
            }
        };
        // when
        $("div").each(eachFunctionSpy);
        // then
        Map<Integer, WebElement> expected = new HashMap<>();
        expected.put(0, d1);

        assertThat(actual, is(expected));
    }

}