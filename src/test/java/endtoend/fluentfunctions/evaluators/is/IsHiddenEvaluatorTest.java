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

public class IsHiddenEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void success() {
        assertEquals(true, $("div").waitUntil().isHidden().then().is(":hidden"));
        assertEquals(true, $("div").assertThat().isHidden().then().is(":hidden"));
    }

    @Test
    public void isHidden_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("article").waitUntil(100).isHidden()
            ,
            "Timeout while waiting for $(\"article\").waitUntil().isHidden().\n\n" +
                "expected: <matched element set to be not empty and have only hidden elements>\n" +
                "but: <last matched element set was a 8 element set, of which 2 were not hidden>"
        );
    }

    @Test
    public void isHidden_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("span.empty-set").assertThat().isHidden()
            ,
            "Failed assertion $(\"span.empty-set\").assertThat().isHidden().\n\n" +
                "expected: <matched element set to be not empty and have only hidden elements>\n" +
                "but: <matched element set was an empty element set>"
        );
    }

    @Test
    public void isHidden_fails_waitUntil__one_not_hidden() {
        assertThrowsTimeoutException(
            __ ->
                $("span.one-not-hidden").waitUntil(100).isHidden()
            ,
            "Timeout while waiting for $(\"span.one-not-hidden\").waitUntil().isHidden().\n\n" +
                "expected: <matched element set to be not empty and have only hidden elements>\n" +
                "but: <last matched element set was a 4 element set, of which 1 was not hidden>"
        );
    }

    @Test
    public void isHidden_fails_assertThat__all_visible() {
        assertThrowsAssertionError(
            __ ->
                $("span.all-visible").assertThat().isHidden()
            ,
            "Failed assertion $(\"span.all-visible\").assertThat().isHidden().\n\n" +
                "expected: <matched element set to be not empty and have only hidden elements>\n" +
                "but: <matched element set was a 3 element set, with no hidden elements>"
        );
    }

}
