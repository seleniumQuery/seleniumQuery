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

package endtoend.fluentfunctions.evaluators.is;

import static endtoend.fluentfunctions.evaluators.EvaluatorsExceptionTestUtils.assertThrowsAssertionError;
import static endtoend.fluentfunctions.evaluators.EvaluatorsExceptionTestUtils.assertThrowsTimeoutException;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class IsBlankEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void empty() {
        assertEquals("", $("#empty").waitUntil().val().isBlank().then().val());
        assertEquals("", $("#empty").assertThat().val().isBlank().then().val());
    }

    @Test
    public void whitespaces() {
        assertEquals("   ", $("#3whitespaces").waitUntil().val().isBlank().then().val());
        assertEquals("   ", $("#3whitespaces").assertThat().val().isBlank().then().val());
    }

    @Test
    public void isBlank_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("#non-blank").waitUntil(100).val().isBlank()
            ,
            "Timeout while waiting for $(\"#non-blank\").waitUntil().val().isBlank().\n\n" +
                "expected: <val() to be blank>\n" +
                "but: <last val() was \" a \">"
        );
    }

    @Test
    public void isBlank_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("#non-blank").assertThat().val().isBlank()
            ,
            "Failed assertion $(\"#non-blank\").assertThat().val().isBlank().\n\n" +
                "expected: <val() to be blank>\n" +
                "but: <val() was \" a \">"
        );
    }

}
