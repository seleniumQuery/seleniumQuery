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

public class IsDisplayedEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void success() {
        assertEquals(true, $("body").waitUntil().isDisplayed().then().is(":visible"));
        assertEquals(true, $("body").assertThat().isDisplayed().then().is(":visible"));
    }

    @Test
    public void isDisplayed_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("span.visible-2-hidden-3").waitUntil(100).isDisplayed()
            ,
            "Timeout while waiting for $(\"span.visible-2-hidden-3\").waitUntil().isDisplayed().\n\n" +
                "expected: <matched element set to be not empty and have only visible elements>\n" +
                "but: <last matched element set was a 5 element set, of which 3 were not visible>"
        );
    }

    @Test
    public void isDisplayed_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("span.visible-0-hidden-1").assertThat().isDisplayed()
            ,
            "Failed assertion $(\"span.visible-0-hidden-1\").assertThat().isDisplayed().\n\n" +
                "expected: <matched element set to be not empty and have only visible elements>\n" +
                "but: <matched element set was a 1 element set, with no visible elements>"
        );
    }

}
