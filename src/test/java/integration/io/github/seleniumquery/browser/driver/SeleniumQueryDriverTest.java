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

package integration.io.github.seleniumquery.browser.driver;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class SeleniumQueryDriverTest {

    @Test
    public void use__should_set_the_argument_as_current_WebDriver_____and___get__should_return_it() {
        // given
        WebDriver driver = mock(WebDriver.class);
        // when
        $.driver().use(driver);
        // then
        WebDriver currentDriver = $.driver().get();
        assertThat(currentDriver, is(driver));
    }

    @Test
    public void quit__should_quit_the_current_webDriver() {
        // given
        WebDriver driver = mock(WebDriver.class);
        $.driver().use(driver);
        assertDriverWasNotQuit(driver);
        // when
        $.driver().quit();
        // then
        assertDriverWasQuit(driver);
    }

    @Test
    public void use__should_quit_the_previous_driver() {
        // given
        WebDriver previousDriver = mock(WebDriver.class);
        $.driver().use(previousDriver);
        assertDriverWasNotQuit(previousDriver);
        // when
        WebDriver newDriver = mock(WebDriver.class);
        $.driver().use(newDriver);
        // then
        assertDriverWasQuit(previousDriver);
    }

    private void assertDriverWasQuit(WebDriver previousDriver) {
        verify(previousDriver).quit();
    }

    private void assertDriverWasNotQuit(WebDriver previousDriver) {
        verify(previousDriver, never()).quit();
    }

    @Test
    public void get__should_return_the_same_WebDriver_upon_multiple_calls() {
        // given
        WebDriver previousDriver = mock(WebDriver.class);
        $.driver().use(previousDriver);
        WebDriver driverGetOnce = $.driver().get();
        // when
        WebDriver driverGetTwice = $.driver().get();
        // then
        assertThat(driverGetOnce, is(previousDriver));
        assertThat(driverGetTwice, is(driverGetTwice));
    }

    @Test
    public void useHtmlUnit__should_create_a_HtmlUnitDriver_upon_first_use() {
        // given
        $.driver().useHtmlUnit();
        // when
        WebDriver driver = $.driver().get();
        // then
        try {
            assertThat(driver, instanceOf(HtmlUnitDriver.class));
        } finally {
            $.driver().quit();
        }
    }

    @Test
    public void useHtmlUnit__should_quit_the_previous_driver() {
        // given
        WebDriver previousDriver = mock(WebDriver.class);
        $.driver().use(previousDriver);
        assertDriverWasNotQuit(previousDriver);
        // when
        $.driver().useHtmlUnit();
        // then
        assertDriverWasQuit(previousDriver);
    }

    @Test // I know this test asserts two things, but instantiating a FirefoxDriver is expensive!
    public void useFirefox__should_create_a_FirefoxDriver_upon_first_use___and___should_quit_the_previous_driver() {
        // given
        WebDriver previousDriver = mock(WebDriver.class);
        $.driver().use(previousDriver);
        assertDriverWasNotQuit(previousDriver);
        try {
            // when
            $.driver().useFirefox();
            WebDriver driver = $.driver().get();
            // then
            assertDriverWasQuit(previousDriver);
            assertThat(driver, instanceOf(FirefoxDriver.class));
        } finally {
            $.driver().quit();
        }
    }

    @Test // I know this test asserts two things, but instantiating a ChromeDriver is expensive!
    public void useChrome__should_create_a_ChromeDriver_upon_first_use___and___should_quit_the_previous_driver() {
        // given
        WebDriver previousDriver = mock(WebDriver.class);
        $.driver().use(previousDriver);
        assertDriverWasNotQuit(previousDriver);
        try {
            // when
            $.driver().useChrome();
            WebDriver driver = $.driver().get();
            // then
            assertDriverWasQuit(previousDriver);
            assertThat(driver, instanceOf(ChromeDriver.class));
        } finally {
            $.driver().quit();
        }
    }

    @Test // I know this test asserts two things, but instantiating a InternetExplorerDriver is expensive!
    public void useInternetExplorer__should_create_a_InternetExplorerDriver_upon_first_use___and___should_quit_the_previous_driver() {
        // given
        WebDriver previousDriver = mock(WebDriver.class);
        $.driver().use(previousDriver);
        assertDriverWasNotQuit(previousDriver);
        try {
            // when
            $.driver().useInternetExplorer();
            WebDriver driver = $.driver().get();
            // then
            assertDriverWasQuit(previousDriver);
            assertThat(driver, instanceOf(InternetExplorerDriver.class));
        } finally {
            $.driver().quit();
        }
    }

    @Test // I know this test asserts two things, but instantiating a PhantomJSDriver is expensive!
    public void usePhantomJS__should_create_a_PhantomJSDriver_upon_first_use___and___should_quit_the_previous_driver() {
        // given
        WebDriver previousDriver = mock(WebDriver.class);
        $.driver().use(previousDriver);
        assertDriverWasNotQuit(previousDriver);
        try {
            // when
            $.driver().usePhantomJS();
            WebDriver driver = $.driver().get();
            // then
            assertDriverWasQuit(previousDriver);
            assertThat(driver, instanceOf(PhantomJSDriver.class));
        } finally {
            $.driver().quit();
        }
    }

}