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

package endtoend.fluentfunctions.evaluators.matches;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hamcrest.Matchers;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import io.github.seleniumquery.fluentfunctions.waituntil.SeleniumQueryTimeoutException;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class MatchesHamcrestMatcherEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void matches__success() {
        assertEquals("!visibleDiv!", $(".visibleDiv").waitUntil().text().matches(Matchers.containsString("isibleDi")).then().text());
    }

    @Test
    public void matches__fails() {
        try {
            $(".visibleDiv").waitUntil(100).text().matches(Matchers.containsString("isibleDix"));
            fail();
        } catch (SeleniumQueryTimeoutException e) {
            assertEquals("Timeout while waiting for $(\".visibleDiv\") to .waitUntil().text().matches" +
                "(<a string containing \"isibleDix\">)", e.getMessage());
        }
    }

}
