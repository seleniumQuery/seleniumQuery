package io.github.seleniumquery.globalfunctions.driver.builders;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.openqa.selenium.remote.CapabilityType.SUPPORTS_JAVASCRIPT;

public class FirefoxDriverBuilderTest {

    @After
    public void tearDown() throws Exception {
        $.driver().quit();
    }

    @Test
    public void useFirefox__should_have_js_ON_by_default() {
        // given
        // when
        $.driver().useFirefox();
        // then
        assertJavaScriptIsOn($.driver().get());
    }

    @Test
    public void withoutJavaScript__should_set_js_OFF() {
        // given
        // when
        $.driver().useFirefox().withoutJavaScript();
        // then
        assertJavaScriptIsOff($.driver().get());
    }

    @Test
    public void withProfile__should_set_the_given_profile() {
        // given
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("javascript.enabled", false);
        // when
        $.driver().useFirefox().withProfile(profile);
        // then
        assertJavaScriptIsOff($.driver().get());
    }

    private void assertJavaScriptIsOn(WebDriver driver) {
        driver.get(htmlTestFileUrl(getClass()));
        assertThat(driver.findElements(By.tagName("div")), hasSize(1+3));
    }


    // TODO reuse methods from SetUpAndTearDownDriver - maybe extract them to some other class
    // i aint doing it now because there is another branch with changes to that file and I dont want to cause
    // merge difficulties. ALSO, maybe we should move these assertJavaScriptOn/Off methods to some test utilities class as well
    private static final String TEST_SRC_FOLDER = "src/test/java/";
    private static String htmlTestFileUrl(Class<?> clazz) {
        String classFullName = clazz.getName();
        String classPath = classFullName.replace('.', '/');
        String htmlPath = TEST_SRC_FOLDER + classPath + ".html";
        return new File(htmlPath).toURI().toString();
    }

    private void assertJavaScriptIsOff(WebDriver driver) {
        driver.get(htmlTestFileUrl(getClass()));
        assertThat(driver.findElements(By.tagName("div")), hasSize(1));
    }

}