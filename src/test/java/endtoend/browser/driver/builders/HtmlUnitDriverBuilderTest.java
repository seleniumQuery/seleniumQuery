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

package endtoend.browser.driver.builders;

import static endtoend.browser.driver.builders.FirefoxDriverBuilderTest.assertJavaScriptIsOff;
import static endtoend.browser.driver.builders.FirefoxDriverBuilderTest.assertJavaScriptIsOn;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.openqa.selenium.remote.CapabilityType.SUPPORTS_JAVASCRIPT;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import endtoend.helpers.BrowserAgentTestHelper;

public class HtmlUnitDriverBuilderTest {

    private static final String HTMLUNIT_IE_AGENT_STRING = "Mozilla/5.0 (Windows NT 6.1; Trident/7.0; rv:11.0) like Gecko";
    private static final String HTMLUNIT_CHROME_AGENT_STRING = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";
    private static final String HTMLUNIT_FF_AGENT_STRING = "Mozilla/5.0 (Windows NT 6.1; rv:52.0) Gecko/20100101 Firefox/52.0";

    @After
    public void tearDown() throws Exception {
        $.driver().quit();
    }

    @Test
    public void useHtmlUnit__should_have_js_ON_by_default() {
        // given
        // when
        $.driver().useHtmlUnit();
        // then
        assertJavaScriptIsOn($.driver().get());
    }

    @Test
    public void useHtmlUnit__should_emulate_CHROME_by_default() throws Exception {
        // given
        // when
        $.driver().useHtmlUnit();
        // then
        BrowserAgentTestHelper.assertAgentString(HTMLUNIT_CHROME_AGENT_STRING);
    }

    @Test
    public void withoutJavaScript__should_set_js_OFF() {
        // given
        // when
        $.driver().useHtmlUnit().withoutJavaScript();
        // then
        assertJavaScriptIsOff($.driver().get());
    }

    @Test
    public void withJavaScript__should_set_js_ON_overriding_given_capabilities() {
        // given
        DesiredCapabilities capabilitiesWithoutJavaScript = DesiredCapabilities.htmlUnit();
        capabilitiesWithoutJavaScript.setCapability(SUPPORTS_JAVASCRIPT, false);
        // when
        $.driver().useHtmlUnit().withCapabilities(capabilitiesWithoutJavaScript).withJavaScript();
        // then
        assertJavaScriptIsOn($.driver().get());
    }

    @Test
    public void emulatingFirefox__should_emulate_latest_firefox__that_is__FIREFOX_45() {
        // given
        // when
        $.driver().useHtmlUnit().emulatingFirefox();
        // then
        BrowserAgentTestHelper.assertAgentString(HTMLUNIT_FF_AGENT_STRING);
    }

    @Test
    public void emulatingChrome__should_emulate_CHROME() {
        // given
        // when
        $.driver().useHtmlUnit().emulatingChrome();
        // then
        BrowserAgentTestHelper.assertAgentString(HTMLUNIT_CHROME_AGENT_STRING);
    }

    @Test
    public void emulatingInternetExplorer__should_emulate_latest_IE() {
        // given
        // when
        $.driver().useHtmlUnit().emulatingInternetExplorer();
        // then
        BrowserAgentTestHelper.assertAgentString(HTMLUNIT_IE_AGENT_STRING);
    }

}
