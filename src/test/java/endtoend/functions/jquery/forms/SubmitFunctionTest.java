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

package endtoend.functions.jquery.forms;

import infrastructure.junitrule.JavaScriptOnly;
import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SubmitFunctionTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test @JavaScriptOnly
    public void submit_function() {
        assertOutput("");
        $("#input-a").submit();
        assertOutput("a");
        $("#div-a").submit();
        assertOutput("aa");
        $("#input-b").submit();
        assertOutput("aab");
        $("#div-b").submit();
        assertOutput("aabb");
        $("input").submit();
        assertOutput("aabbab");
        $("div").submit();
        assertOutput("aabbabab");
    }

    private void assertOutput(String value) {
        assertThat($("#out").text(), is(value));
    }

}