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

package endtoend.functions.jquery.manipulation;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.testutils.DriverInTest;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TextFunctionTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void text_function() {
		WebDriver driver = $.driver().get();

    	if (DriverInTest.isHtmlUnitDriver(driver)) {
			assertThat($("div.demo-container").text(), is("Demonstration Box\nlist item 1 list item 2"));
    	} else {
    		assertThat($("div.demo-container").text(), is("Demonstration Box\nlist item 1\nlist item 2"));
    	}

    	assertThat($("div.d").text(), is("Batman Spider Man yo Hulk"));

    	assertThat($("#myTextArea").text().trim(), is("Initial value for textarea"));
    	assertThat($("#myTextArea").val().trim(), is("Initial value for textarea"));
		$("#myTextArea").val("Typed stuff in textarea");

		// #Cross-Driver
		// this is no fix, just documenting the difference
		// IE (tested IE11) will make .text() follow the typed text. Other browsers will keep the original one.
		// This is OK, as it is the SAME behavior presented by jQuery!
		//
		// Well, HtmlUnit follows IE...
        if (DriverInTest.isIEDriver(driver) || DriverInTest.isHtmlUnitDriver(driver)) {
    		assertThat($("#myTextArea").text().trim(), is("Typed stuff in textarea"));
		} else {
			assertThat($("#myTextArea").text().trim(), is("Initial value for textarea"));
		}
    	assertThat($("#myTextArea").val().trim(), is("Typed stuff in textarea"));
	}

}