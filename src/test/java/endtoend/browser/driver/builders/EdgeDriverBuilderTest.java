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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import endtoend.browser.util.BrowserAgentTestUtils;
import endtoend.browser.util.DriverBuilderTestUtil;
import endtoend.browser.util.DriverBuilderTestUtil.VerySpecialExceptionOnlyWeThrow;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class EdgeDriverBuilderTest {

    @Before
    public void setUp() {
        assumeTrue("To run the tests in this class, EndToEndTestConfig#whatDriversShouldTestsRun() should return DriverToRunTestsIn.EDGE.",
            SetUpAndTearDownDriver.driverToRunTestsIn.canRunEdge());
    }

    @After
    public void tearDown() {
        $.quit();
    }

    @Test(expected = VerySpecialExceptionOnlyWeThrow.class)
    public void withOptions() {
        // given
        EdgeOptions edgeOptions = new EdgeOptions() {
            @Override
            public boolean is(String capabilityName) {
                throw new VerySpecialExceptionOnlyWeThrow();
            }
        };
        // when
        $.driver().useEdge().autoDriverDownload().withOptions(edgeOptions);
        DriverBuilderTestUtil.openAnyUrl();
        // then
        // exception is thrown when the browser inits with the passed options
    }

    @Test(expected = VerySpecialExceptionOnlyWeThrow.class)
    @SuppressWarnings("deprecation")
    public void withCapabilities() {
        // given
        DesiredCapabilities capabilities = new DesiredCapabilities("MicrosoftEdge", "", Platform.WINDOWS) {
            @Override
            public boolean is(String capabilityName) {
                throw new VerySpecialExceptionOnlyWeThrow();
            }
        };
        // when
        $.driver().useEdge().autoDriverDownload().withCapabilities(capabilities);
        DriverBuilderTestUtil.openAnyUrl();
        // then
        // exception is thrown when the browser inits with the passed capabilities
    }

    @Test
    public void autoDriverDownload() {
        // given
        // when
        $.driver().useEdge().autoDriverDownload();
        // then
        BrowserAgentTestUtils.assertBrowserAgent(containsString("Edge"));
    }

    @Test
    @SuppressWarnings({"deprecation", "ConstantConditions"})
    public void withCapabilities__should_return_the_current_EdgeDriverBuilder_instance_to_allow_further_chaining() {
        EdgeOptions edgeOptions = null;
        $.driver().useEdge().withCapabilities(null).withOptions(edgeOptions); // should compile
    }

}
