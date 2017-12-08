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

public class ContainsEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void contains() {
        assertEquals("foo", $("#yo").waitUntil().prop("className").contains("foo").then().prop("className"));
        assertEquals("foo", $("#yo").assertThat().prop("className").contains("foo").then().prop("className"));
    }

    @Test
    public void contains_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("#yo").waitUntil(100).prop("className").contains("fOO")
            ,
            "Timeout while waiting for $(\"#yo\").waitUntil().prop(\"className\").contains(\"fOO\").\n\n" +
                "expected: <prop(\"className\") to contain \"fOO\">\n" +
                "but: <last prop(\"className\") was \"foo\">"
        );
    }

    @Test
    public void contains_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("#yo").assertThat().prop("className").contains("fOO")
            ,
            "Failed assertion $(\"#yo\").assertThat().prop(\"className\").contains(\"fOO\").\n\n" +
                "expected: <prop(\"className\") to contain \"fOO\">\n" +
                "but: <prop(\"className\") was \"foo\">"
        );
    }

}
