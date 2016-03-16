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

import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.reflect.Method;

import static endtoend.browser.driver.builders.FirefoxDriverBuilderTest.assertJavaScriptIsOff;
import static endtoend.browser.driver.builders.FirefoxDriverBuilderTest.assertJavaScriptIsOn;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HtmlUnitDriverBuilderTest {

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
        assertThat(emulatedBrowser($.driver().get()), is("Chrome"));
    }

    private static String emulatedBrowser(WebDriver htmlUnitDriver) throws Exception {
        Method getWebClientMethod = HtmlUnitDriver.class.getDeclaredMethod("getWebClient");
        getWebClientMethod.setAccessible(true);
        WebClient webClient = (WebClient) getWebClientMethod.invoke(htmlUnitDriver);
        return webClient.getBrowserVersion().toString();
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
        capabilitiesWithoutJavaScript.setBrowserName(BrowserType.FIREFOX);
        // when
        $.driver().useHtmlUnit().withCapabilities(capabilitiesWithoutJavaScript).withJavaScript();
        // then
        assertJavaScriptIsOn($.driver().get());
    }

    @Test
    public void emulatingFirefox__should_emulate_latest_firefox__that_is__FIREFOX_38() throws Exception {
        // given
        // when
        $.driver().useHtmlUnit().emulatingFirefox();
        // then
        assertThat(emulatedBrowser($.driver().get()), is("FF38"));
    }

    @Test
    public void emulatingChrome__should_emulate_CHROME() throws Exception {
        // given
        // when
        $.driver().useHtmlUnit().emulatingChrome();
        // then
        assertThat(emulatedBrowser($.driver().get()), is("Chrome"));
    }

    @Test
    public void emulatingInternetExplorer__should_emulate_latest_IE__that_is__IE11() throws Exception {
        // given
        // when
        $.driver().useHtmlUnit().emulatingInternetExplorer();
        // then
        assertThat(emulatedBrowser($.driver().get()), is("IE11"));
    }

    @Test
    public void emulatingInternetExplorer11__should_emulate_IE11() throws Exception {
        // given
        // when
        $.driver().useHtmlUnit().emulatingInternetExplorer11();
        // then
        assertThat(emulatedBrowser($.driver().get()), is("IE11"));
    }

}