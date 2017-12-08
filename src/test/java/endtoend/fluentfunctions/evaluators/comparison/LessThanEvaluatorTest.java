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

public class LessThanEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void isLessThan() {
        assertEquals(2, $("div").waitUntil().size().isLessThan(3).then().size());
        assertEquals(2, $("div").assertThat().size().isLessThan(3).then().size());
    }

    @Test
    public void isLessThan_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("div").waitUntil(100).size().isLessThan(2)
            ,
            "Timeout while waiting for $(\"div\").waitUntil().size().isLessThan(2).\n\n" +
                "expected: <size() to be less than 2>\n" +
                "but: <last size() was 2>"
        );
    }

    @Test
    public void isLessThan_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("div").assertThat().size().isLessThan(2)
            ,
            "Failed assertion $(\"div\").assertThat().size().isLessThan(2).\n\n" +
                "expected: <size() to be less than 2>\n" +
                "but: <size() was 2>"
        );
    }

}
