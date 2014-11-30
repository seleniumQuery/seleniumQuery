package io.github.seleniumquery.browser.driver.builders;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static infrastructure.IntegrationTestUtils.classNameToTestFileUrl;
import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.getFullPathForFileInClasspath;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ChromeDriverBuilderTest {

    @Test
    public void withOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        $.driver().useChrome().withOptions(options);

        $.url(classNameToTestFileUrl(ChromeDriverBuilderTest.class));

        try {
            assertThat($("#isMaximized").text(), is("yes"));
        } finally {
            $.quit();
        }
    }

    @Test
    public void withCapabilities() {
        // given
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        // when
        $.driver().useChrome().withCapabilities(capabilities);
        // then
        $.url(classNameToTestFileUrl(ChromeDriverBuilderTest.class));
        try {
            assertThat($("#isMaximized").text(), is("yes"));
        } finally {
            $.quit();
        }
    }

    @Test
    public void withCapabilities__should_return_the_current_ChromeDriverBuilder_instance_to_allow_further_chaining() {
        $.driver().useChrome().withCapabilities(null).withOptions(null); // should compile
    }

    @Test
    public void withPathToChromeDriverExe() {
        $.driver().useChrome().withPathToChromeDriverExe("src/test/resources/chromedriver.exe");
        $.url(classNameToTestFileUrl(ChromeDriverBuilderTest.class)); // just opening a page should work
        $.quit();
    }

    @Test
    public void useChrome__should_fall_back_to_systemProperty_when_executable_not_found_in_classpath() {
        // given
        ChromeDriverBuilder.CHROMEDRIVER_EXE = "not-in-classpath.exe";
        // when
        System.setProperty("webdriver.chrome.driver", getFullPathForFileInClasspath("chromedriver.exe"));
        $.driver().useChrome();
        // then
        $.url(classNameToTestFileUrl(ChromeDriverBuilderTest.class)); // just opening a page should work
        $.quit();
    }

}