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

package endtoend.io.github.seleniumquery.browser.driver.builders;

import io.github.seleniumquery.browser.driver.builders.InternetExplorerDriverBuilder;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static testinfrastructure.IntegrationTestUtils.classNameToTestFileUrl;
import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.getFullPathForFileInClasspath;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InternetExplorerDriverBuilderTest {

    @After
    public void tearDown() throws Exception {
        $.quit();
    }

    @Test
    public void withCapabilities() {
        // given
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        String fileUrl = classNameToTestFileUrl(ChromeDriverBuilderTest.class);
        capabilities.setCapability("initialBrowserUrl", fileUrl);
        // when
        $.driver().useInternetExplorer().withCapabilities(capabilities);
        // then
        // should start at the page specified by the capabilities
        assertThat($.url(), is(fileUrl.replace("/", "\\").replace("file:\\", "file://")));
    }

    @Test
    public void withCapabilities__should_return_the_current_InternetExplorerDriverBuilder_instance_to_allow_further_chaining() {
        $.driver().useInternetExplorer().withCapabilities(null).withPathToIEDriverServerExe(null); // should compile
    }

    @Test
    public void withPathToIEDriverServerExe() {
        // given
        $.driver().useInternetExplorer().withPathToIEDriverServerExe("src/test/resources/IEDriverServer.exe");
        // when
        $.url(classNameToTestFileUrl(ChromeDriverBuilderTest.class));
        // then
        // no exception is thrown while opening a page
    }

    @Test
    public void useInternetExplorer__should_fall_back_to_systemProperty_when_executable_not_found_in_classpath() {
        // given
        InternetExplorerDriverBuilder.IEDRIVERSERVER_EXE = "not-in-classpath.exe";
        System.setProperty("webdriver.ie.driver", getFullPathForFileInClasspath("IEDriverServer.exe"));
        // when
        $.driver().useInternetExplorer();
        $.url(classNameToTestFileUrl(ChromeDriverBuilderTest.class));
        // then
        // no exception is thrown while opening a page
    }

}