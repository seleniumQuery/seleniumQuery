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

public class IsVisibleEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void success() {
        assertEquals(true, $("span.all-visible").waitUntil().isVisible().then().is(":visible"));
        assertEquals(true, $("span.all-visible").assertThat().isVisible().then().is(":visible"));
    }

    @Test
    public void isVisible_fails_waitUntil__all_hidden() {
        assertThrowsTimeoutException(
            __ ->
                $("span.all-hidden").waitUntil(100).isVisible()
            ,
            "Timeout while waiting for $(\"span.all-hidden\").waitUntil().isVisible().\n\n" +
                "expected: <matched element set to be not empty and have only visible elements>\n" +
                "but: <last matched element set was a 2 element set, with no visible elements>"
        );
    }

    @Test
    public void isVisible_fails_assertThat__one_not_hidden() {
        assertThrowsAssertionError(
            __ ->
                $("span.one-not-hidden").assertThat().isVisible()
            ,
            "Failed assertion $(\"span.one-not-hidden\").assertThat().isVisible().\n\n" +
                "expected: <matched element set to be not empty and have only visible elements>\n" +
                "but: <matched element set was a 4 element set, of which 3 were not visible>"
        );
    }

    @Test
    public void isVisible_fails_waitUntil__empty_set() {
        assertThrowsTimeoutException(
            __ ->
                $("span.empty-set").waitUntil(100).isVisible()
            ,
            "Timeout while waiting for $(\"span.empty-set\").waitUntil().isVisible().\n\n" +
                "expected: <matched element set to be not empty and have only visible elements>\n" +
                "but: <last matched element set was an empty element set>"
        );
    }

    @Test
    public void isVisible_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("span.with-8-els-2-not-hidden").assertThat().isVisible()
            ,
            "Failed assertion $(\"span.with-8-els-2-not-hidden\").assertThat().isVisible().\n\n" +
                "expected: <matched element set to be not empty and have only visible elements>\n" +
                "but: <matched element set was a 8 element set, of which 6 were not visible>"
        );
    }

}
