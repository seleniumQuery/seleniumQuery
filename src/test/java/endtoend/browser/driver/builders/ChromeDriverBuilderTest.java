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

package endtoend.browser.driver.builders;

import endtoend.browser.SeleniumQueryBrowserTest;
import io.github.seleniumquery.browser.driver.builders.ChromeDriverBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import java.util.HashMap;
import java.util.Map;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.getFullPathForFileInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;
import static testinfrastructure.testutils.EnvironmentTestUtils.isNotWindowsOS;
import static testinfrastructure.testutils.EnvironmentTestUtils.onlyRunIfDriverTestExecutableExists;

public class ChromeDriverBuilderTest {

    // https://code.google.com/p/chromium/codesearch#chromium/src/chrome/test/chromedriver/chrome/mobile_device_list.cc
    private static final String CHROME_MOBILE_EMULATION_DEVICE = "Apple iPad";
    private static final String CHROME_MOBILE_EMULATION_EXPECTED_AGENT_STRING = "iPad";

    private static String chromeExecutable = ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_WINDOWS;
    private static String originalPathWindows;
    private static String originalPathLinux;

    @BeforeClass
    public static void beforeClass() {
        if (isNotWindowsOS()) {
            chromeExecutable = ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_LINUX;
        }
        originalPathWindows = ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_WINDOWS;
        originalPathLinux = ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_LINUX;
    }

    @Before
    public void setUp() {
        assumeTrue(SetUpAndTearDownDriver.driverToRunTestsIn.canRunChrome());
    }

    @After
    public void tearDown() {
        ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_WINDOWS = originalPathWindows;
        ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_LINUX = originalPathLinux;
        $.quit();
    }

    @Test
    public void withOptions() {
        // given
        ChromeOptions options = createChromeOptionsWithMobileEmulation();
        // when
        $.driver().useChrome().withOptions(options);
        // then
        $.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));
        assertThat($("#agent").text(), containsString(CHROME_MOBILE_EMULATION_EXPECTED_AGENT_STRING));
    }

    @Test
    public void withCapabilities() {
        // given
        ChromeOptions options = createChromeOptionsWithMobileEmulation();

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        // when
        $.driver().useChrome().withCapabilities(capabilities);
        // then
        $.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));
        assertThat($("#agent").text(), containsString(CHROME_MOBILE_EMULATION_EXPECTED_AGENT_STRING));
    }

    private ChromeOptions createChromeOptionsWithMobileEmulation() {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", CHROME_MOBILE_EMULATION_DEVICE);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        return options;
    }

    @Test
    public void withoutCapabilities() {
        // given
        // when
        $.driver().useChrome();
        // then
        $.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));
        assertThat($("#agent").text(), not(containsString(CHROME_MOBILE_EMULATION_EXPECTED_AGENT_STRING)));
    }

    @Test
    public void withCapabilities__should_return_the_current_ChromeDriverBuilder_instance_to_allow_further_chaining() {
        $.driver().useChrome().withCapabilities(null).withOptions(null); // should compile
    }

    @Test
    public void withPathToChromeDriver() {
        onlyRunIfDriverTestExecutableExists(chromeExecutable);
        // given
        $.driver().useChrome().withPathToChromeDriver("src/test/resources/"+chromeExecutable);
        // when
        $.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));
        // then
        // no exception is thrown while opening a page
    }

    @Test
    public void useChrome__should_fall_back_to_systemProperty_when_executable_not_found_in_classpath() {
        onlyRunIfDriverTestExecutableExists(chromeExecutable);
        // given
        ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_WINDOWS = "not-in-classpath.txt";
        ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_LINUX = "not-in-classpath.txt";
        System.setProperty("webdriver.chrome.driver", getFullPathForFileInClasspath(chromeExecutable));
        // when
        $.driver().useChrome();
        $.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));
        // then
        // no exception is thrown while opening a page
    }

}
