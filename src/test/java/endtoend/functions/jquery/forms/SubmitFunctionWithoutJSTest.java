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

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertThat;

public class SubmitFunctionWithoutJSTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SubmitFunctionWithoutJSTest.class);

    @Test
    public void submit_function_without_js() {
        String url = $.url();
        assertThat(url, endsWith("WithoutJSTest.html"));

        $("#input-a").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));

        $.url(url);
        $("#div-a").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));

        $.url(url);
        $("#input-b").submit();
        assertThat($.url(), endsWith("WithoutJSTest_3.html?bName=bValue"));

        $.url(url);
        $("#div-b").submit();
        assertThat($.url(), endsWith("WithoutJSTest_3.html?bName=bValue"));

        $.url(url);
        $("input").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));

        $.url(url);
        $("div").submit();
        assertThat($.url(), endsWith("WithoutJSTest_2.html?aName=aValue"));
    }

}