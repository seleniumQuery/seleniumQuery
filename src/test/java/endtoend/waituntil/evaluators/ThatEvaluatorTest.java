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

package endtoend.waituntil.evaluators;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hamcrest.Matchers;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import io.github.seleniumquery.wait.SeleniumQueryTimeoutException;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class ThatEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Rule public ExpectedException thrown = ExpectedException.none();

    @Test
    public void that() {
        assertEquals("!visibleDiv!", $(".visibleDiv").waitUntil().text().that(Matchers.containsString("isibleDi")).then().text());
    }

    @Test
    public void that_fails() {
        try {
            assertEquals("!visibleDiv!", $(".visibleDiv").waitUntil(100).text().that(Matchers.containsString("isibleDix")).then().text());
            fail();
        } catch (SeleniumQueryTimeoutException e) {
            assertEquals("Timeout while waiting for $(\".visibleDiv\") to .waitUntil().text().that" +
                "(<a string containing \"isibleDix\">)", e.getMessage());
        }
    }


}
