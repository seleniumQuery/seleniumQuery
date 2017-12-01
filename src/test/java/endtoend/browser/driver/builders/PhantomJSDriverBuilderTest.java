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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;
import static testinfrastructure.testutils.EnvironmentTestUtils.onlyRunIfDriverTestExecutableExists;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import endtoend.helpers.BrowserAgentTestHelper;
import io.github.seleniumquery.browser.driver.builders.PhantomJSDriverBuilder;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class PhantomJSDriverBuilderTest {

    @Before
    public void setUp() {
        assumeTrue("SetUpAndTearDownDriver should authorize to run tests in PhantomJS", SetUpAndTearDownDriver.driverToRunTestsIn.canRunPhantomJS());
    }

    @After
    public void tearDown() {
        $.quit();
    }

    @Test
    public void withCapabilities() {
        // given
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX+"userAgent", "JustAnotherAgent WebKit");
        // when
        $.driver().usePhantomJS().withCapabilities(capabilities);
        // then
        BrowserAgentTestHelper.assertBrowserAgent(containsString("JustAnotherAgent"));
    }

    @Test
    public void withCapabilities__should_return_the_current_PhantomJSDriverBuilder_instance_to_allow_further_chaining() {
        // given
        PhantomJSDriverBuilder phantomJSDriverBuilderAfterFirstCall = $.driver().usePhantomJS().withCapabilities(null);
        // when
        PhantomJSDriverBuilder phantomJSDriverBuilderAfterSecondCall = phantomJSDriverBuilderAfterFirstCall.withPathToPhantomJS(null);
        // then
        assertThat(phantomJSDriverBuilderAfterSecondCall, is(phantomJSDriverBuilderAfterFirstCall));
    }

    @Test
    public void withPathToPhantomJS() {
        assumeTrue(SystemUtils.IS_OS_WINDOWS);
        onlyRunIfDriverTestExecutableExists("phantomjs.exe");
        // given
        $.driver().usePhantomJS().withPathToPhantomJS("src/test/resources/phantomjs.exe");
        // when
        BrowserAgentTestHelper.openBrowserAgentTestHelperUrl();
        // then
        // no exception is thrown while opening a page
    }

}
