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

package endtoend.showcase;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assume.assumeTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class SeleniumQueryExampleTest {

    @Before
    public void setUp() {
        assumeTrue(SetUpAndTearDownDriver.driverToRunTestsIn.canRunChrome());
    }

    @Test
    public void showcase_should_work() {
        // sets Chrome as the driver (this is optional, if omitted, will default to HtmlUnit)
        $.driver().useChrome(); // The WebDriver will be instantiated only when first used

        // or use any previously existing driver
        // $.driver().use(new FirefoxDriver());

        // starts the driver (if not started already) and opens the URL
        $.url("http://www.google.com/?hl=en");

        // interact with the page
        $(":text[name='q']").val("seleniumQuery"); // the keys are actually typed!

        // Besides the short syntax and the jQuery behavior you already know,
        // other very useful function in seleniumQuery is .waitUntil(),
        // handy for dealing with user-waiting actions (specially in Ajax enabled pages):

        // the command below waits until the button is visible and then performs a real user click (not just the JS event)
        $(":button[value='Google Search']").waitUntil().is(":visible").then().click();

        // this waits for the #resultStats to be visible and, when it is visible, returns its text content
        String resultsText = $("#resultStats").waitUntil().is(":visible").then().text();

        System.out.println(resultsText);
        // should print something like: About 1,080 results (0.26 seconds)

        $.quit(); // quits the currently used driver (chrome)
    }

    @After
    public void tearDown() {
        $.quit(); // quits the currently used driver (firefox)
    }

}
