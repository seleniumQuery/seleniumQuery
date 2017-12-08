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

public class ContainsIgnoreCaseEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void containsIgnoreCase() {
        assertEquals("yo", $("#yo").waitUntil().val().containsIgnoreCase("yO").then().val());
        assertEquals("yo", $("#yo").assertThat().val().containsIgnoreCase("Yo").then().val());
    }

    @Test
    public void containsIgnoreCase_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("#yo").waitUntil(100).val().containsIgnoreCase("x")
            ,
            "Timeout while waiting for $(\"#yo\").waitUntil().val().containsIgnoreCase(\"x\").\n\n" +
                "expected: <val() to contain \"x\" ignoring case>\n" +
                "but: <last val() was \"yo\">"
        );
    }

    @Test
    public void containsIgnoreCase_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("#yo").assertThat().val().containsIgnoreCase("x")
            ,
            "Failed assertion $(\"#yo\").assertThat().val().containsIgnoreCase(\"x\").\n\n" +
                "expected: <val() to contain \"x\" ignoring case>\n" +
                "but: <val() was \"yo\">"
        );
    }

}
