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

public class IsEmptyEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void success() {
        assertEquals(0, $("span").waitUntil().isEmpty().then().size());
        assertEquals(0, $("span").assertThat().isEmpty().then().size());
    }

    @Test
    public void isEmpty_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("div").waitUntil(100).isEmpty()
            ,
            "Timeout while waiting for $(\"div\").waitUntil().isEmpty().\n\n" +
                "expected: <matched element set to be empty>\n" +
                "but: <last matched element set was not empty (had 3 elements)>"
        );
    }

    @Test
    public void isEmpty_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("body").assertThat().isEmpty()
            ,
            "Failed assertion $(\"body\").assertThat().isEmpty().\n\n" +
                "expected: <matched element set to be empty>\n" +
                "but: <matched element set was not empty (had one element)>"
        );
    }

}
