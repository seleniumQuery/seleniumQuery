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

package endtoend.fluentfunctions.evaluators.comparison;

import static endtoend.fluentfunctions.evaluators.EvaluatorsExceptionTestUtils.assertThrowsAssertionError;
import static endtoend.fluentfunctions.evaluators.EvaluatorsExceptionTestUtils.assertThrowsTimeoutException;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class GreaterThanEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void isGreaterThan() {
        assertEquals(1, $("#foo").waitUntil().size().isGreaterThan(0).then().size());
        assertEquals(1, $("#foo").assertThat().size().isGreaterThan(0).then().size());
    }

    @Test
    public void isGreaterThan_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("#foo").waitUntil(100).size().isGreaterThan(1)
            ,
            "Timeout while waiting for $(\"#foo\").waitUntil().size().isGreaterThan(1).\n\n" +
                "expected: <size() to be greater than 1>\n" +
                "but: <last size() was 1>"
        );
    }

    @Test
    public void isGreaterThan_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("#foo").assertThat().size().isGreaterThan(1)
            ,
            "Failed assertion $(\"#foo\").assertThat().size().isGreaterThan(1).\n\n" +
                "expected: <size() to be greater than 1>\n" +
                "but: <size() was 1>"
        );
    }

}
