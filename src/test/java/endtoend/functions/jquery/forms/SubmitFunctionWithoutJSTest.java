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

package endtoend.functions.jquery.forms;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertThat;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class SubmitFunctionWithoutJSTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void submit_function_without_js() {
        assertThat($.url(), endsWith("WithoutJSTest.html"));
    }

    @Test
    public void submit_function_without_js__input_a() {
        $("#input-a").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));
    }

    @Test
    public void submit_function_without_js__div_a() {
        $("#div-a").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));
    }

    @Test
    public void submit_function_without_js__input_b() {
        $("#input-b").submit();
        assertThat($.url(), endsWith("WithoutJSTest_3.html?bName=bValue"));
    }

    @Test
    public void submit_function_without_js__div_b() {
        $("#div-b").submit();
        $.pause(1000);
        assertThat($.url(), endsWith("WithoutJSTest_3.html?bName=bValue"));
    }

    @Test
    public void submit_function_without_js__input() {
        $("input").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));
    }

    @Test
    public void submit_function_without_js__div() {
        $("div").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));
    }

}
