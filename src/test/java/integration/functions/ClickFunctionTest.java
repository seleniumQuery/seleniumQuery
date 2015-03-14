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

package integration.functions;

import infrastructure.junitrule.JavaScriptOnly;
import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ClickFunctionTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test @JavaScriptOnly
    public void click_function() {
		WebDriver driver = $.driver().get();
		boolean isIE = driver instanceof InternetExplorerDriver;
		if (isIE) {
			// IE, when STARTING, focuses the <BODY> by itself, so a div is generated and we don't want it, as we are using
			// the number of generated divs in the test!
			for (WebElement div : $("div")) {
				((JavascriptExecutor) driver).executeScript("document.body.removeChild(arguments[0]);", div);
			}
		}

		assertThat($("div").size(), is(0));

    	$("#i1").click();
    	assertThat($("div").size(), is(3));
    	assertThat($("div.i1").size(), is(2));
    	assertThat($("div.i1.click").size(), is(1));
    	assertThat($("div.i1.focus").size(), is(1));
    	assertThat($("div.body").size(), is(1));
    	assertThat($("div.body.click").size(), is(1));
    	
    	$("#i2").click();
    	assertThat($("div").size(), is(6));
    	assertThat($("div.i1").size(), is(2));
    	assertThat($("div.i1.click").size(), is(1));
    	assertThat($("div.i1.focus").size(), is(1));
    	assertThat($("div.i2").size(), is(2));
    	assertThat($("div.i2.click").size(), is(1));
    	assertThat($("div.i2.focus").size(), is(1));
    	assertThat($("div.body").size(), is(2));
    	assertThat($("div.body.click").size(), is(2));
    	
    	$("body").click();
		assertThat($("div.i1").size(), is(2));
		assertThat($("div.i2").size(), is(2));
		// IE focuses <body> when clicking it, so it generates an additional DIV...
		if (!isIE) {
			assertThat($("div").size(), is(7));
			assertThat($("div.body").size(), is(3));
			assertThat($("div.body.focus").size(), is(0));
			assertThat($("div.body.click").size(), is(3));
		} else {
			assertThat($("div").size(), is(8));
			assertThat($("div.body").size(), is(4));
			assertThat($("div.body.focus").size(), is(1));
			assertThat($("div.body.click").size(), is(3));
		}
	}

}