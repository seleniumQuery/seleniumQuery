package io.github.seleniumquery.browser.driver.builders;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static infrastructure.IntegrationTestUtils.classNameToTestFileUrl;
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