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
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assume.assumeTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import endtoend.browser.util.BrowserAgentTestUtils;
import endtoend.browser.util.DriverBuilderTestUtil;
import io.github.seleniumquery.SeleniumQueryException;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class OperaDriverBuilderTest {

    private static final String OPERA_BINARY_PARH = "C:/Program Files/Opera/49.0.2725.47/opera.exe";

    @Before
    public void setUp() {
        assumeTrue("To run the tests in this class, EndToEndTestConfig#whatDriversShouldTestsRun() should return DriverToRunTestsIn.OPERA.",
            SetUpAndTearDownDriver.driverToRunTestsIn.canRunOpera());
    }

    @After
    public void tearDown() {
        $.quit();
    }

    @Test(expected = DriverBuilderTestUtil.VerySpecialExceptionOnlyWeThrow.class)
    public void withOptions() {
        // given
        OperaOptions operaOptions = new OperaOptions() {
            @Override
            public boolean is(String capabilityName) {
                throw new DriverBuilderTestUtil.VerySpecialExceptionOnlyWeThrow();
            }
        };
        // when
        $.driver().useOpera().autoDriverDownload().withOptions(operaOptions);
        DriverBuilderTestUtil.openAnyUrl();
        // then
        // exception is thrown when the browser inits with the passed options
    }

    @Test(expected = DriverBuilderTestUtil.VerySpecialExceptionOnlyWeThrow.class)
    @SuppressWarnings("deprecation")
    public void withCapabilities() {
        // given
        DesiredCapabilities operaBlink = DesiredCapabilities.operaBlink();
        DesiredCapabilities capabilities = new DesiredCapabilities(operaBlink.getBrowserName(), operaBlink.getVersion(), operaBlink.getPlatform()) {
            @Override
            public boolean is(String capabilityName) {
                throw new DriverBuilderTestUtil.VerySpecialExceptionOnlyWeThrow();
            }
        };
        // when
        $.driver().useOpera().autoDriverDownload().withCapabilities(capabilities);
        DriverBuilderTestUtil.openAnyUrl();
        // then
        // exception is thrown when the browser inits with the passed capabilities
    }

    @Test(expected = SeleniumQueryException.class)
    public void withoutBinary__shouldThrow_custom_SeleniumQueryException() {
        // when
        $.driver().useOpera().autoDriverDownload();
        // then
        BrowserAgentTestUtils.assertBrowserAgent(containsString("OPR"));
    }

    @Test
    public void withBinary_string__and__autoDriverDownload() {
        // when
        $.driver().useOpera().withBinary(OPERA_BINARY_PARH).autoDriverDownload();
        // then
        BrowserAgentTestUtils.assertBrowserAgent(containsString("OPR"));
    }

    @Test
    public void withBinary_file() {
        // when
        $.driver().useOpera().withBinary(new File(OPERA_BINARY_PARH)).autoDriverDownload();
        // then
        BrowserAgentTestUtils.assertBrowserAgent(containsString("OPR"));
    }

    @Test
    @SuppressWarnings({"deprecation", "ConstantConditions"})
    public void withCapabilities__should_return_the_current_OperaDriverBuilder_instance_to_allow_further_chaining() {
        OperaOptions operaOptions = null;
        $.driver().useOpera().withCapabilities(null).withOptions(operaOptions); // should compile
    }

}
