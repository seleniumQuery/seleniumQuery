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

public class EqualsEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void isEqualTo() {
        assertEquals("foo", $("#bar").waitUntil().html().isEqualTo("foo").then().html());
        assertEquals("foo", $("#bar").assertThat().html().isEqualTo("foo").then().html());
    }

    @Test
    public void isEqualTo_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("#bar").waitUntil(100).html().isEqualTo("qux")
            ,
            "Timeout while waiting for $(\"#bar\").waitUntil().html().isEqualTo(\"qux\").\n\n" +
                "expected: <html() to equal \"qux\">\n" +
                "but: <last html() was \"foo\">"
        );
    }

    @Test
    public void isEqualTo_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("#bar").assertThat().html().isEqualTo("qux")
            ,
            "Failed assertion $(\"#bar\").assertThat().html().isEqualTo(\"qux\").\n\n" +
                "expected: <html() to equal \"qux\">\n" +
                "but: <html() was \"foo\">"
        );
    }

}
