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

package endtoend.showcase;

import org.junit.After;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertTrue;

public class SeleniumQueryExampleTest {

    @Test
    public void showcase_should_work() {
        // sets Firefox as the driver (this is optional, if omitted, will default to HtmlUnit)
        $.driver().useFirefox(); // The WebDriver will be instantiated only when first used

        $.url("http://www.google.com/?hl=en");

        $(":text[name='q']").val("selenium"); // the keys are actually typed!

        // Besides the short syntax and the jQuery behavior you already know,
        // other very useful function in seleniumQuery is .waitUntil(),
        // handy for dealing with user-waiting actions (specially in Ajax enabled pages):
        String resultsText = $("#resultStats").waitUntil().is(":visible").then().text();

        assertTrue("\""+ resultsText + "\" should match regex", resultsText.trim().matches("About \\d+,\\d+,\\d+ results \\(\\d+\\.\\d+ seconds\\)"));
        // should print something like: About 24,800,000 results (0.48 seconds)
    }

    @After
    public void tearDown() throws Exception {
        $.quit(); // quits the currently used driver (firefox)
    }

}