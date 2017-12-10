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

package endtoend.browser.driver.builders;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assume.assumeTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import endtoend.browser.util.DriverBuilderTestUtil;
import endtoend.browser.util.HeadlessTestUtils;
import endtoend.browser.util.JsOnOffTestUtils;
import io.github.seleniumquery.SeleniumQueryException;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class FirefoxDriverBuilderTest {

    @Before
    public void setUp() {
        assumeTrue("To run the tests in this class, EndToEndTestConfig#whatDriversShouldTestsRun() should return DriverToRunTestsIn.FIREFOX.",
            SetUpAndTearDownDriver.driverToRunTestsIn.canRunFirefox());
    }

    @After
    public void tearDown() {
        $.driver().quit();
    }

    @Test
    public void useFirefox__should_have_js_ON_by_default() {
        // given
        // when
        $.driver().useFirefox();
        // then
        JsOnOffTestUtils.assertJavaScriptIsOn($.driver().get());
    }

    @Test(expected = SeleniumQueryException.class)
    @SuppressWarnings("deprecation")
    public void withoutJavaScript__should_throw_exception() {
        $.driver().useFirefox().withoutJavaScript();
    }

    @Test
    public void withProfile__should_set_the_given_profile() {
        // given
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.startup.homepage", "about:blank?setViaProfile");
        // when
        $.driver().useFirefox().autoDriverDownload().withProfile(profile).autoQuitDriver();
        // then
        $.driver().get();
        Assert.assertEquals("about:blank?setViaProfile", $.url());
    }

    @Test
    public void autoDriverDownload() {
        // given
        // when
        $.driver().useFirefox().autoDriverDownload();
        // then
        DriverBuilderTestUtil.openAnyUrl();
    }

    @Test
    public void headless__yes() {
        // when
        $.driver().useFirefox().headless().autoDriverDownload();
        // then
        HeadlessTestUtils.assertHeadlessYes($.driver().get());
    }

    @Test
    public void headless__not() {
        // when
        $.driver().useFirefox().autoDriverDownload();
        // then
        HeadlessTestUtils.assertHeadlessNot($.driver().get());
    }

    @Test
    public void withOptions() {
        // when
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        $.driver().useFirefox().withOptions(firefoxOptions).withBinary(firefoxBinary).autoDriverDownload();
        // then
        HeadlessTestUtils.assertHeadlessYes($.driver().get());
    }

}
