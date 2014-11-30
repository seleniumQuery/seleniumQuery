package io.github.seleniumquery.browser.driver.builders;

import io.github.seleniumquery.SeleniumQueryBrowserTest;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import static infrastructure.IntegrationTestUtils.classNameToTestFileUrl;
import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.browser.driver.builders.DriverInstantiationUtils.getFullPathForFileInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class PhantomJSDriverBuilderTest {

    String phantomExecutable = PhantomJSDriverBuilder.PHANTOMJS_EXE;

    @After
    public void tearDown() throws Exception {
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
        PhantomJSDriverBuilder.PHANTOMJS_EXE = "not-in-classpath.txt";
        System.setProperty("phantomjs.binary.path", getFullPathForFileInClasspath(phantomExecutable));
        // when
        $.driver().usePhantomJS();
        $.url(classNameToTestFileUrl(SeleniumQueryBrowserTest.class));
        // then
        // no exception is thrown while opening a page
    }

}