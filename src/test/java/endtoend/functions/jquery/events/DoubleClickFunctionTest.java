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

package endtoend.functions.jquery.events;

import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import testinfrastructure.junitrule.JavaScriptOnly;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.testutils.DriverInTest;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testutils.DriverInTest.isIEDriver;

public class DoubleClickFunctionTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test @JavaScriptOnly
    public void dblclick_function() {
        assertThat($("div").size(), is(0));

        $("#i1").dblclick();
        removeDivAddedWhenBySomeDrivers("div.click.i1:eq(0)");
        removeDivAddedWhenBySomeDrivers("div.click.body:eq(0)");

        assertThat($("div.click.i1").size(), is(1)); // 2 when simulated manually
        assertThat($("div.click.body").size(), is(1)); // 2
        assertThat($("div.dblclick.i1").size(), is(1));
        assertThat($("div.dblclick.body").size(), is(1));
        assertThat($("div").size(), is(4)); // 6

        $("#i2").dblclick();
        removeDivAddedWhenBySomeDrivers("div.click.i2:eq(0)");
        removeDivAddedWhenBySomeDrivers("div.click.body:eq(0)");

        assertThat($("div.click.i1").size(), is(1)); // 2
        assertThat($("div.dblclick.i1").size(), is(1));
        assertThat($("div.click.i2").size(), is(1)); // 2
        assertThat($("div.dblclick.i2").size(), is(1));
        assertThat($("div.click.body").size(), is(1+1)); // 2+2
        assertThat($("div.dblclick.body").size(), is(1+1));
        assertThat($("div").size(), is(8)); // 12

        $("body").dblclick();
        removeDivAddedWhenBySomeDrivers("div.click.body:eq(0)");

        assertThat($("div.click.i1").size(), is(1)); // 2
        assertThat($("div.dblclick.i1").size(), is(1));
        assertThat($("div.click.i2").size(), is(1)); // 2
        assertThat($("div.dblclick.i2").size(), is(1));
        assertThat($("div.click.body").size(), is(2+1)); // 4+2
        assertThat($("div.dblclick.body").size(), is(2+1));
        assertThat($("div").size(), is(10)); // 15
    }

    private static void removeDivAddedWhenBySomeDrivers(String selector) {
        WebDriver driver = $.driver().get();
        if (DriverInTest.isHtmlUnitDriver(driver)
                || isIEDriver($.driver().get())
                || driver instanceof PhantomJSDriver) {
            remove($(selector));
        }
    }

    private static void remove(SeleniumQueryObject e) {
        JavascriptExecutor driver = ((JavascriptExecutor) e.getWebDriver());
        assertThat(e.size(), is(1));
        driver.executeScript("document.body.removeChild(arguments[0]);", e.get(0));
    }

}