/*
 * Copyright (c) 2016 seleniumQuery authors
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

package endtoend.browser.driver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.ChromeOnly;
import testinfrastructure.junitrule.annotation.FirefoxOnly;
import testinfrastructure.junitrule.annotation.IEOnly;
import testinfrastructure.junitrule.annotation.PhantomJSOnly;
import testinfrastructure.junitrule.sample.SampleTest;
import testinfrastructure.testdouble.org.openqa.selenium.WebDriverQuitSpy;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.org.openqa.selenium.WebDriverQuitSpy.createWebDriverQuitSpy;

public class SeleniumQueryDriverOthersTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver rule = new SetUpAndTearDownDriver(SampleTest.class);

    @Test
    @FirefoxOnly
    public void useFirefox__should_create_a_FirefoxDriver_upon_first_use___and___should_quit_the_previous_driver() {
        // given
        WebDriverQuitSpy previousDriver = createWebDriverQuitSpy();
        $.driver().use(previousDriver);
        previousDriver.assertDriverWasNotQuit();
        try {
            // when
            $.driver().useFirefox();
            WebDriver driver = $.driver().get();
            // then
            previousDriver.assertDriverWasQuit();
            assertThat(driver, instanceOf(FirefoxDriver.class));
        } finally {
            $.driver().quit();
        }
    }

    @Test
    @ChromeOnly
    public void useChrome__should_create_a_ChromeDriver_upon_first_use___and___should_quit_the_previous_driver() {
        // given
        WebDriverQuitSpy previousDriver = createWebDriverQuitSpy();
        $.driver().use(previousDriver);
        previousDriver.assertDriverWasNotQuit();
        try {
            // when
            $.driver().useChrome();
            WebDriver driver = $.driver().get();
            // then
            previousDriver.assertDriverWasQuit();
            assertThat(driver, instanceOf(ChromeDriver.class));
        } finally {
            $.driver().quit();
        }
    }

    @Test
    @IEOnly
    public void useInternetExplorer__should_create_a_InternetExplorerDriver_upon_first_use___and___should_quit_the_previous_driver() {
        // given
        WebDriverQuitSpy previousDriver = createWebDriverQuitSpy();
        $.driver().use(previousDriver);
        previousDriver.assertDriverWasNotQuit();
        try {
            // when
            $.driver().useInternetExplorer();
            WebDriver driver = $.driver().get();
            // then
            previousDriver.assertDriverWasQuit();
            assertThat(driver, instanceOf(InternetExplorerDriver.class));
        } finally {
            $.driver().quit();
        }
    }

    @Test
    @PhantomJSOnly
    public void usePhantomJS__should_create_a_PhantomJSDriver_upon_first_use___and___should_quit_the_previous_driver() {
        // given
        WebDriverQuitSpy previousDriver = createWebDriverQuitSpy();
        $.driver().use(previousDriver);
        previousDriver.assertDriverWasNotQuit();
        try {
            // when
            $.driver().usePhantomJS();
            WebDriver driver = $.driver().get();
            // then
            previousDriver.assertDriverWasQuit();
            assertThat(driver, instanceOf(PhantomJSDriver.class));
        } finally {
            $.driver().quit();
        }
    }

}
