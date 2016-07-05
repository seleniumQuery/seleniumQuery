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

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testutils.DriverInTest.*;

public class DoubleClickFunctionTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test @JavaScriptEnabledOnly
    public void dblclick_function() {
        assertThat($("div").size(), is(0));

        $("#i1").dblclick();

        int operaDiff = 0;
        int otherDiff = 0;
        WebDriver driver = $.driver().get();
        if (isOperaDriver(driver)) {
            operaDiff++;
        } else if (isHtmlUnitDriver(driver)
            || isIEDriver(driver)
            || isPhantomJSDriver(driver)
            || isSafariDriver(driver)
            || isEdgeDriver(driver)) {
            otherDiff++;
        }

        assertThat($("div.click.i1").size(), is(1 + operaDiff + otherDiff)); // 2 when simulated manually
        assertThat($("div.click.body").size(), is(1 + operaDiff + otherDiff)); // 2
        assertThat($("div.dblclick.i1").size(), is(1));
        assertThat($("div.dblclick.body").size(), is(1));
        assertThat($("div").size(), is(4 + operaDiff*2 + otherDiff*2)); // 6

        $("#i2").dblclick();

        assertThat($("div.click.i1").size(), is(1 + operaDiff + otherDiff)); // 2
        assertThat($("div.dblclick.i1").size(), is(1));
        assertThat($("div.click.i2").size(), is(1 + operaDiff + otherDiff)); // 2
        assertThat($("div.dblclick.i2").size(), is(1));
        assertThat($("div.click.body").size(), is(1+1 + operaDiff*2 + otherDiff*2)); // 2+2
        assertThat($("div.dblclick.body").size(), is(1+1));
        assertThat($("div").size(), is(8 + operaDiff*4 + otherDiff*4)); // 12

        $("body").dblclick();

        assertThat($("div.click.i1").size(), is(1 + operaDiff*3 + otherDiff)); // 2
        assertThat($("div.dblclick.i1").size(), is(1 + operaDiff));
        assertThat($("div.click.i2").size(), is(1 + operaDiff + otherDiff)); // 2
        assertThat($("div.dblclick.i2").size(), is(1));
        assertThat($("div.click.body").size(), is(2+1 + operaDiff*3 + otherDiff*3)); // 4+2
        assertThat($("div.dblclick.body").size(), is(2+1));
        assertThat($("div").size(), is(10 + operaDiff*8 + otherDiff*5)); // 15
    }

}
