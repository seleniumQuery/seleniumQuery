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
import testinfrastructure.junitrule.JavaScriptOnly;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SubmitFunctionTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test @JavaScriptOnly
    public void submit__input_element() {
        $("#input-a").submit();
        assertOutput("a");
    }
    @Test @JavaScriptOnly
    public void submit__non_input_element() {
        $("#div-a").submit();
        assertOutput("a");
    }
    @Test @JavaScriptOnly
    public void submit__several_input_elements() {
        $("input").submit();
        assertOutput("ab");
    }
    @Test @JavaScriptOnly
    public void submit__several_non_input_elements() {
        $("div").submit();
        assertOutput("ab");
    }

    private void assertOutput(String value) {
        assertThat($("#out").text(), is(value));
    }

}