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

public class IsNotVisibleEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void success() {
        assertEquals(true, $("span.visible-0-hidden-2").waitUntil().isNotVisible().then().is(":hidden"));
        assertEquals(true, $("span.visible-0-hidden-2").assertThat().isNotVisible().then().is(":hidden"));
        assertEquals(0, $("span.empty-set").waitUntil().isNotVisible().then().size());
        assertEquals(0, $("span.empty-set").assertThat().isNotVisible().then().size());
    }

    @Test
    public void isNotVisible_fails_waitUntil() {
        assertThrowsTimeoutException(
            __ ->
                $("span.visible-2-hidden-6").waitUntil(100).isNotVisible()
            ,
            "Timeout while waiting for $(\"span.visible-2-hidden-6\").waitUntil().isNotVisible().\n\n" +
                "expected: <matched element set to be empty or have only hidden elements>\n" +
                "but: <last matched element set was a 8 element set, of which 2 were not hidden>"
        );
    }

    @Test
    public void isNotVisible_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("span.visible-1-hidden-3").assertThat().isNotVisible()
            ,
            "Failed assertion $(\"span.visible-1-hidden-3\").assertThat().isNotVisible().\n\n" +
                "expected: <matched element set to be empty or have only hidden elements>\n" +
                "but: <matched element set was a 4 element set, of which 1 was not hidden>"
        );
    }

    @Test
    public void isNotVisible_fails_assertThat__all_visible() {
        assertThrowsAssertionError(
            __ ->
                $("span.visible-3-hidden-0").assertThat().isNotVisible()
            ,
            "Failed assertion $(\"span.visible-3-hidden-0\").assertThat().isNotVisible().\n\n" +
                "expected: <matched element set to be empty or have only hidden elements>\n" +
                "but: <matched element set was a 3 element set, with no hidden elements>"
        );
    }

}
