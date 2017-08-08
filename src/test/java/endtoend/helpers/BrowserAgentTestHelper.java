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

package endtoend.helpers;

import static org.junit.Assert.assertEquals;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;

import io.github.seleniumquery.SeleniumQuery;
import io.github.seleniumquery.SeleniumQueryBrowser;

public class BrowserAgentTestHelper {

    private static final String AGENT_TEST_URL = classNameToTestFileUrl(BrowserAgentTestHelper.class);

    public static void assertAgentString(String agentString) {
        SeleniumQueryBrowser globalBrowser = SeleniumQuery.seleniumQueryBrowser();
        openAgentTestHelperUrl(globalBrowser);
        assertBrowserAgent(globalBrowser, agentString);
    }

    public static void assertBrowserAgent(SeleniumQueryBrowser browser, String agentString) {
        assertEquals(agentString, browser.$("#agent").text());
    }

    public static void openAgentTestHelperUrl(SeleniumQueryBrowser browser) {
        browser.$.url(BrowserAgentTestHelper.AGENT_TEST_URL);
    }

}
