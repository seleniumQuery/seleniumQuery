/*
 * Copyright (c) 2015 seleniumQuery authors
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

package endtoend.io.github.seleniumquery;

import io.github.seleniumquery.SeleniumQueryBrowser;
import org.junit.Test;

import static infrastructure.IntegrationTestUtils.classNameToTestFileUrl;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class SeleniumQueryBrowserTest {

    @Test
    public void multiple_browser_instances_should_work_OK() {
        SeleniumQueryBrowser chrome = new SeleniumQueryBrowser();
        chrome.$.driver().useHtmlUnit().emulatingChrome();
        chrome.$.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));

        SeleniumQueryBrowser firefox = new SeleniumQueryBrowser();
        firefox.$.driver().useHtmlUnit().emulatingFirefox();
        firefox.$.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));

        assertThat(chrome.$("#agent").text(), containsString("Chrome"));
        assertThat(firefox.$("#agent").text(), containsString("Firefox"));

        chrome.$.quit();
        firefox.$.quit();
    }

}