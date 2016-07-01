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

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import testinfrastructure.testdouble.org.openqa.selenium.WebDriverQuitSpy;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testdouble.org.openqa.selenium.WebDriverQuitSpy.createWebDriverQuitSpy;

public class SeleniumQueryDriverTest {

    @Test
    public void use__should_set_the_argument_as_current_WebDriver_____and___get__should_return_it() {
        // given
        WebDriver driver = createWebDriverQuitSpy();
        // when
        $.driver().use(driver);
        // then
        WebDriver currentDriver = $.driver().get();
        assertThat(currentDriver, is(driver));
    }

    @Test
    public void quit__should_quit_the_current_webDriver() {
        // given
        WebDriverQuitSpy driver = createWebDriverQuitSpy();
        $.driver().use(driver);
        driver.assertDriverWasNotQuit();
        // when
        $.driver().quit();
        // then
        driver.assertDriverWasQuit();
    }

    @Test
    public void use__should_quit_the_previous_driver() {
        // given
        WebDriverQuitSpy previousDriver = createWebDriverQuitSpy();
        $.driver().use(previousDriver);
        previousDriver.assertDriverWasNotQuit();
        // when
        WebDriver newDriver = createWebDriverQuitSpy();
        $.driver().use(newDriver);
        // then
        previousDriver.assertDriverWasQuit();
    }

    @Test
    public void get__should_return_the_same_WebDriver_upon_multiple_calls() {
        // given
        WebDriver previousDriver = createWebDriverQuitSpy();
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
        WebDriverQuitSpy previousDriver = createWebDriverQuitSpy();
        $.driver().use(previousDriver);
        previousDriver.assertDriverWasNotQuit();
        // when
        $.driver().useHtmlUnit();
        // then
        previousDriver.assertDriverWasQuit();
    }

}
