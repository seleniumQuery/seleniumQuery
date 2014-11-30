package io.github.seleniumquery.browser.driver.builders;

import io.github.seleniumquery.SeleniumQueryBrowserTest;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import static infrastructure.IntegrationTestUtils.classNameToTestFileUrl;
import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.getFullPathForFileInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class PhantomJSDriverBuilderTest {

    static String phantomExecutable = PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_WINDOWS;
    static String originalPathWindows;
    static String originalPathLinux;

    @BeforeClass
    public static void setUp() throws Exception {
        if (!System.getProperty("os.name").toLowerCase().contains("win")) {
            phantomExecutable = PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_LINUX;
        }
        originalPathWindows = PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_WINDOWS;
        originalPathLinux = PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_LINUX;
    }

    @After
    public void tearDown() throws Exception {
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
        // given
        $.driver().usePhantomJS().withPathToPhantomJS("src/test/resources/"+ phantomExecutable);
        // when
        $.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));
        // then
        // no exception is thrown while opening a page
    }

    @Test
    public void usePhantomJS__should_fall_back_to_systemProperty_when_executable_not_found_in_classpath() {
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