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
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class IsEqualToEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void isEqualTo() {
        assertEquals(true, $("#bar").waitUntil().prop("disabled").isEqualTo(true).then().prop("disabled"));
        assertEquals(true, $("#bar").assertThat().prop("disabled").isEqualTo(true).then().prop("disabled"));
    }

    @Test
    public void isEqualTo_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("#bar").waitUntil(100).prop("disabled").isEqualTo(false)
            ,
            "Timeout while waiting for $(\"#bar\").waitUntil().prop(\"disabled\").isEqualTo(false).\n\n" +
                "expected: <prop(\"disabled\") to equal false>\n" +
                "but: <last prop(\"disabled\") was true>"
        );
    }

    @Test
    // problem is we are converting thevalue returned by prop to string right away
    // it is clear now we shouldn't have done that
    // also, when fixing this, that quoteValue function wont need a "typeReferenceValue" anymore, as it will quote the real argument
    @Ignore
    public void isEqualTo_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("#bar").assertThat().prop("disabled").isEqualTo("true")
            ,
            "Failed assertion $(\"#bar\").assertThat().prop(\"disabled\").isEqualTo(\"true\").\n\n" +
                "expected: <prop(\"disabled\") to equal \"true\">\n" +
                "but: <prop(\"disabled\") was true>"
        );
    }

}
