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

package endtoend.fluentfunctions.evaluators;

import static endtoend.fluentfunctions.evaluators.EvaluatorsExceptionTestUtils.assertThrowsAssertionError;
import static endtoend.fluentfunctions.evaluators.EvaluatorsExceptionTestUtils.assertThrowsTimeoutException;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;

public class IsEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    @JavaScriptEnabledOnly
    public void isEvaluator() {
        assertEquals("bar", $("#bar").waitUntil().is(":not(:enabled)").then().attr("id"));
        assertEquals("bar", $("#bar").assertThat().is(":not(:enabled)").then().attr("id"));
    }

    @Test
    @JavaScriptEnabledOnly
    public void isEvaluator_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("#bar").waitUntil(100).is(":enabled")
            ,
            "Timeout while waiting for $(\"#bar\").waitUntil().is(\":enabled\").\n\n" +
                "expected: <matched element set to be \":enabled\">\n" +
                "but: <last matched element set was \"<input id=\"bar\" disabled=\"\" value=\"> <input id='q' disabled value='a'> " +
                "foo\" class=\"abc zyx www-yy...\">"
        );
    }

    @Test
    @JavaScriptEnabledOnly
    public void isEvaluator_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("#bar").assertThat().is(":enabled")
            ,
            "Failed assertion $(\"#bar\").assertThat().is(\":enabled\").\n\n" +
                "expected: <matched element set to be \":enabled\">\n" +
                "but: <matched element set was \"<input id=\"bar\" disabled=\"\" value=\"> <input id='q' disabled value='a'> foo\" " +
                "class=\"abc zyx www-yy...\">"
        );
    }

}
