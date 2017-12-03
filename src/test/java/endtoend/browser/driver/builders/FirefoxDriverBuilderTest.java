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

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;

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
        assertJavaScriptIsOn($.driver().get());
    }

    @Test
    public void withoutJavaScript__should_set_js_OFF() {
        // given
        // when
        $.driver().useFirefox().withoutJavaScript();
        // then
        assertJavaScriptIsOff($.driver().get());
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
        // the code works, but I can't find a way to test it yet
    }

    public static void assertJavaScriptIsOn(WebDriver driver) {
        driver.get(classNameToTestFileUrl(FirefoxDriverBuilderTest.class));
        assertThat(driver.findElements(By.tagName("div")), hasSize(1 + 3));
    }

    public static void assertJavaScriptIsOff(WebDriver driver) {
        driver.get(classNameToTestFileUrl(FirefoxDriverBuilderTest.class));
        assertThat(driver.findElements(By.tagName("div")), hasSize(1));
    }

    @Test
    public void autoDriverDownload() {
        // given
        // when
        $.driver().useFirefox().autoDriverDownload();
        // then
        assertJavaScriptIsOn($.driver().get());
    }

}
