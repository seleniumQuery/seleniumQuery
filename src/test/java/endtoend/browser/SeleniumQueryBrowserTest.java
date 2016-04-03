/*
 * Copyright (c) 2016 seleniumQuery authors
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

package endtoend.browser;

import io.github.seleniumquery.SeleniumQueryBrowser;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static testinfrastructure.EndToEndTestUtils.openUrl;

public class SeleniumQueryBrowserTest {

    private SeleniumQueryBrowser chrome = new SeleniumQueryBrowser();
    private SeleniumQueryBrowser firefox = new SeleniumQueryBrowser();

    @Test
    public void multiple_browser_instances_should_work_OK() {
        chrome.$.driver().useHtmlUnit().emulatingChrome();
        openUrl(SeleniumQueryBrowserTest.class, chrome.$);

        firefox.$.driver().useHtmlUnit().emulatingFirefox();
        openUrl(SeleniumQueryBrowserTest.class, firefox.$);

        assertThat(chrome.$("#agent").text(), containsString("Chrome"));
        assertThat(firefox.$("#agent").text(), containsString("Firefox"));
    }

    @After
    public void tearDown() {
        chrome.$.quit();
        firefox.$.quit();
    }

}