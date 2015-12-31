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
import io.github.seleniumquery.browser.driver.builders.PhantomJSDriverBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.testutils.EnvironmentTestUtils;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.getFullPathForFileInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;
import static testinfrastructure.testutils.EnvironmentTestUtils.isNotWindowsOS;
import static testinfrastructure.testutils.EnvironmentTestUtils.onlyRunIfDriverTestExecutableExistsForThisOS;

public class PhantomJSDriverBuilderTest {

    static String phantomExecutable = PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_WINDOWS;
    static String originalPathWindows;
    static String originalPathLinux;

    @BeforeClass
    public static void beforeClass() {
        if (isNotWindowsOS()) {
            phantomExecutable = PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_LINUX;
        }
        originalPathWindows = PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_WINDOWS;
        originalPathLinux = PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_LINUX;
    }

    @Before
    public void setUp() {
        assumeTrue(SetUpAndTearDownDriver.driverToRunTestsIn.canRunPhantomJS());
    }

    @After
    public void tearDown() {
        PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_WINDOWS = originalPathWindows;
        PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_LINUX = originalPathLinux;
        $.quit();
    }

    @Test
    public void withCapabilities() {
        // given
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX+"userAgent", "JustAnotherAgent");
        // when
        $.driver().usePhantomJS().withCapabilities(capabilities);
        // then
        $.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));
        assertThat($("#agent").html(), containsString("JustAnotherAgent"));
    }

    @Test
    public void withCapabilities__should_return_the_current_PhantomJSDriverBuilder_instance_to_allow_further_chaining() {
        $.driver().usePhantomJS().withCapabilities(null).withPathToPhantomJS(null); // should compile
    }

    @Test
    public void withPathToPhantomJS() {
        onlyRunIfDriverTestExecutableExistsForThisOS(phantomExecutable);
        // given
        $.driver().usePhantomJS().withPathToPhantomJS("src/test/resources/" + phantomExecutable);
        // when
        $.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));
        // then
        // no exception is thrown while opening a page
    }

    @Test
    public void usePhantomJS__should_fall_back_to_systemProperty_when_executable_not_found_in_classpath() {
        onlyRunIfDriverTestExecutableExistsForThisOS(phantomExecutable);
        // given
        PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_WINDOWS = "not-in-classpath.txt";
        PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_LINUX = "not-in-classpath.txt";
        System.setProperty("phantomjs.binary.path", getFullPathForFileInClasspath(phantomExecutable));
        // when
        $.driver().usePhantomJS();
        $.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));
        // then
        // no exception is thrown while opening a page
    }

}