package io.github.seleniumquery.browser.driver.builders;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static infrastructure.IntegrationTestUtils.classNameToTestFileUrl;
import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.getFullPathForFileInClasspath;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ChromeDriverBuilderTest {

    static String chromeExecutable = ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_WINDOWS;
    static String originalPathWindows;
    static String originalPathLinux;

    @BeforeClass
    public static void setUp() throws Exception {
        if (!System.getProperty("os.name").toLowerCase().contains("win")) {
            chromeExecutable = ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_LINUX;
        }
        originalPathWindows = ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_WINDOWS;
        originalPathLinux = ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_LINUX;
    }

    @After
    public void tearDown() throws Exception {
        ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_WINDOWS = originalPathWindows;
        ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_LINUX = originalPathLinux;
        $.quit();
    }

    @Test
    public void withOptions() {
        // given
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        // when
        $.driver().useChrome().withOptions(options);
        // then
        $.url(classNameToTestFileUrl(ChromeDriverBuilderTest.class));
        assertThat($("#isMaximized").text(), is("yes"));
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
        assertThat($("#isMaximized").text(), is("yes"));
    }

    @Test
    public void withCapabilities__should_return_the_current_ChromeDriverBuilder_instance_to_allow_further_chaining() {
        $.driver().useChrome().withCapabilities(null).withOptions(null); // should compile
    }

    @Test
    public void withPathToChromeDriver() {
        // given
        $.driver().useChrome().withPathToChromeDriver("src/test/resources/"+chromeExecutable);
        // when
        $.url(classNameToTestFileUrl(ChromeDriverBuilderTest.class));
        // then
        // no exception is thrown while opening a page
    }

    @Test
    public void useChrome__should_fall_back_to_systemProperty_when_executable_not_found_in_classpath() {
        // given
        ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_WINDOWS = "not-in-classpath.txt";
        ChromeDriverBuilder.CHROMEDRIVER_EXECUTABLE_LINUX = "not-in-classpath.txt";
        System.setProperty("webdriver.chrome.driver", getFullPathForFileInClasspath(chromeExecutable));
        // when
        $.driver().useChrome();
        $.url(classNameToTestFileUrl(ChromeDriverBuilderTest.class));
        // then
        // no exception is thrown while opening a page
    }

}