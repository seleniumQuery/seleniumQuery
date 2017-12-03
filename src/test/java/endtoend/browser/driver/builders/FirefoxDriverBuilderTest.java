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
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxProfile;

import endtoend.browser.util.DriverBuilderTestUtil;
import endtoend.browser.util.JsOnOffTestUtils;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class FirefoxDriverBuilderTest {

    @Before
    public void setUp() {
        assumeTrue("To run the tests in this class, EndToEndTestConfig#whatDriversShouldTestsRun() should return Firefox.",
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

    @Test
    public void withoutJavaScript__should_set_js_OFF() {
        // given
        // when
        $.driver().useFirefox().withoutJavaScript();
        // then
        JsOnOffTestUtils.assertJavaScriptIsOff($.driver().get());
    }

    @Test
    @Ignore("It works, but we gotta find a way to TEST if the changed preference was really set")
    public void withProfile__should_set_the_given_profile() {
        // given
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.startup.homepage", "about:blank?stuff");
        // when
        $.driver().useFirefox().withProfile(profile);
        // then
        // assert that preference was set
        // the code works, but I can't find a way to verify it yet
    }

    @Test
    public void autoDriverDownload() {
        // given
        // when
        $.driver().useFirefox().autoDriverDownload();
        // then
        DriverBuilderTestUtil.openAnyUrl();
    }

}
