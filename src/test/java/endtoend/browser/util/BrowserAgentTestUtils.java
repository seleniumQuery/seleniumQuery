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

package endtoend.browser.util;

import static org.junit.Assert.assertThat;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import io.github.seleniumquery.SeleniumQuery;
import io.github.seleniumquery.SeleniumQueryBrowser;

public class BrowserAgentTestUtils {

    public static final String AGENT_TEST_URL = classNameToTestFileUrl(BrowserAgentTestUtils.class);

    public static void assertBrowserAgent(String agentString) {
        openBrowserAgentTestHelperUrl();
        assertBrowserAgent(SeleniumQuery.seleniumQueryBrowser(), agentString);
    }

    public static void assertBrowserAgent(Matcher<String> agentMatcher) {
        openBrowserAgentTestHelperUrl();
        assertBrowserAgent(SeleniumQuery.seleniumQueryBrowser(), agentMatcher);
    }

    public static void openBrowserAgentTestHelperUrl() {
        openBrowserAgentTestHelperUrl(SeleniumQuery.seleniumQueryBrowser());
    }

    public static void openBrowserAgentTestHelperUrl(SeleniumQueryBrowser browser) {
        browser.$.url(BrowserAgentTestUtils.AGENT_TEST_URL);
    }

    public static void assertBrowserAgent(SeleniumQueryBrowser browser, String agentString) {
        assertBrowserAgent(browser, Matchers.equalTo(agentString));
    }

    public static void assertBrowserAgent(SeleniumQueryBrowser browser, Matcher<String> agentMatcher) {
        assertThat(browser.$("#agent").text(), agentMatcher);
    }

}
