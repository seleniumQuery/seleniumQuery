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
import org.openqa.selenium.ie.InternetExplorerDriver;
import testinfrastructure.junitrule.JavaScriptOnly;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.testutils.DriverInTest;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ClickFunctionTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test @JavaScriptOnly
    public void click_function() {
        removeDivAddedByIeWhenPageStarts();

        assertThat($("div").size(), is(0));

        $("#i1").click();
        removeDivBodyFocusAddedWhenDriverIsHtmlUnit();

        assertThat($("div.i1.click").size(), is(1));
        assertThat($("div.i1.focus").size(), is(1));
        assertThat($("div.body.click").size(), is(1));
        assertThat($("div.i1").size(), is(2));
        assertThat($("div.body").size(), is(1));
        assertThat($("div").size(), is(3));

        $("#i2").click();
        removeDivBodyFocusAddedWhenDriverIsHtmlUnit();

        assertThat($("div.i1.click").size(), is(1));
        assertThat($("div.i1.focus").size(), is(1));
        assertThat($("div.i2.click").size(), is(1));
        assertThat($("div.i2.focus").size(), is(1));
        assertThat($("div.body.click").size(), is(2));
        assertThat($("div.i1").size(), is(2));
        assertThat($("div.i2").size(), is(2));
        assertThat($("div.body").size(), is(2));
        assertThat($("div").size(), is(6));

        $("body").click();
        removeDivAddedByIeWhenClickingBody();

        assertThat($("div.i1.click").size(), is(1));
        assertThat($("div.i1.focus").size(), is(1));
        assertThat($("div.i2.click").size(), is(1));
        assertThat($("div.i2.focus").size(), is(1));
        assertThat($("div.i1").size(), is(2));
        assertThat($("div.i2").size(), is(2));
        assertThat($("div.body").size(), is(3));
        assertThat($("div.body.focus").size(), is(0));
        assertThat($("div.body.click").size(), is(3));
        assertThat($("div").size(), is(7));
    }

    private static void removeDivAddedByIeWhenClickingBody() {
        // #CrossDriver: IE focuses <body> when clicking it, so it generates an additional DIV...
        removeDivBodyFocusAddedByIe();
    }

    public static void removeDivAddedByIeWhenPageStarts() {
        // #CrossDriver
        // IE, when STARTING, focuses the <BODY> by itself, so a div is generated and we don't want it, as we are using the number of generated divs in the test!
        removeDivBodyFocusAddedByIe();
    }

    private static void removeDivBodyFocusAddedByIe() {
        boolean isIE = $.driver().get() instanceof InternetExplorerDriver;
        if (isIE) {
            removeDivBodyFocus();
        }
    }

    public static void removeDivBodyFocusAddedWhenDriverIsHtmlUnit() {
        WebDriver driver = $.driver().get();

        if (DriverInTest.isHtmlUnitDriver(driver)) {
            // #CrossDriver
            // HtmlUnit emits a focus on body when i1 is clicked (other browsers just emit a focus on i1)
            removeDivBodyFocus();
        }
    }

    private static void removeDivBodyFocus() {
        JavascriptExecutor driver = ((JavascriptExecutor) $.driver().get());
        SeleniumQueryObject divBodyFocus = $("div.body.focus");
        assertThat(divBodyFocus.size(), is(1));
        driver.executeScript("document.body.removeChild(arguments[0]);", divBodyFocus.get(0));
    }

}