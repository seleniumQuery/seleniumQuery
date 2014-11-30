package io.github.seleniumquery.browser.driver.builders;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import static infrastructure.IntegrationTestUtils.classNameToTestFileUrl;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

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

    public static void assertJavaScriptIsOn(WebDriver driver) {
        driver.get(classNameToTestFileUrl(FirefoxDriverBuilderTest.class));
        assertThat(driver.findElements(By.tagName("div")), hasSize(1 + 3));
    }

    public static void assertJavaScriptIsOff(WebDriver driver) {
        driver.get(classNameToTestFileUrl(FirefoxDriverBuilderTest.class));
        assertThat(driver.findElements(By.tagName("div")), hasSize(1));
    }

}