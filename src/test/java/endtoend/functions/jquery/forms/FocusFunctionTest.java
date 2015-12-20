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

package endtoend.functions.jquery.forms;

import endtoend.functions.ClickFunctionTest;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.firstgen.css.pseudoclasses.UnsupportedXPathPseudoClassException;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import testinfrastructure.junitrule.JavaScriptOnly;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FocusFunctionTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test @JavaScriptOnly
    public void focus_function() {
    	$("#i1").focus();
    	assertThat($("#i1").is(":focus"), is(true));

    	$("#bodyID").focus();
    	assertThat($("body").is(":focus"), is(true));
    	assertThat($("#bodyID").is(":focus"), is(true));

    	$("#i0").focus();
    	assertThat($("#i0").is(":focus"), is(true));

    	$("body").focus();
    	assertThat($("body").is(":focus"), is(true));
    	assertThat($("#bodyID").is(":focus"), is(true));

    	$("#i2").focus();
    	assertThat($("#i2").is(":focus"), is(true));

    	// html is not focusable on HtmlUnit, not even with tabindex!
    	// Due to that, there is not even the possibility of a workaround specific for HtmlUnitDriver.
    	// still, someone wanting to focus the html seems to be a highly unlikely operation, even more
    	// since the body IS focusable
    	$("html").focus();
    	if (!($.driver().get() instanceof HtmlUnitDriver)) {
    		assertThat($("html").is(":focus"), is(true));
    	}

    	$("input").focus();
    	assertThat($("#i2").is(":focus"), is(true));
    	assertThat($("input").is(":focus"), is(true));
    }


    @Test @JavaScriptOnly
    public void focus_function__should_make_sure_the_elements_are_just_focused_and_NOT_clicked() {
		ClickFunctionTest.removeDivAddedByIeWhenPageStarts();

    	// for each click or focus in those elements, there will be a div added to the body
    	// this tests makes sure only a div relative to the focus event is added
    	// (and not two divs, one for focus and one for click, if that happened, the it was a click+focus, not just focus)
    	assertThat($("div").size(), is(0));

        $("#i1").focus();
        ClickFunctionTest.removeDivBodyFocusAddedWhenDriverIsHtmlUnit();
        removeDivBodyFocusAddedWhenDriverIsIeAndWhenFocusingInput();

        assertThat($("#i1").is(":focus"), is(true));
    	assertThat($("div.i1.focus").size(), is(1));
    	assertThat($("div.i1").size(), is(1));
    	assertThat($("div").size(), is(1));

    	$("#i2").focus();
        ClickFunctionTest.removeDivBodyFocusAddedWhenDriverIsHtmlUnit();

    	assertThat($("#i2").is(":focus"), is(true));
    	assertThat($("div").size(), is(2));
    	assertThat($("div.i1").size(), is(1));
    	assertThat($("div.i1.focus").size(), is(1));
    	assertThat($("div.i2").size(), is(1));
    	assertThat($("div.i2.focus").size(), is(1));

    	$("#a1").focus();
        ClickFunctionTest.removeDivBodyFocusAddedWhenDriverIsHtmlUnit();

    	assertThat($("#a1").is(":focus"), is(true));
    	assertThat($("div").size(), is(3));
    	assertThat($("div.i1").size(), is(1));
    	assertThat($("div.i2").size(), is(1));
    	assertThat($("div.a1").size(), is(1));
    	assertThat($("div.a1.focus").size(), is(1));

    	$("#im1").focus();
    	assertThat($("#im1").is(":focus"), is(true));

    	if (!($.driver().get() instanceof HtmlUnitDriver)) {
	    	assertThat($("div").size(), is(4));
	    	assertThat($("div.i1").size(), is(1));
	    	assertThat($("div.i2").size(), is(1));
	    	assertThat($("div.a1").size(), is(1));
	    	assertThat($("div.im1").size(), is(1));
	    	assertThat($("div.im1.focus").size(), is(1));
    	} else {
    		// HtmlUnitDriver will focus, but won't trigger the focus EVENT for the <img> tag... :/
	    	assertThat($("div").size(), is(3));
	    	assertThat($("div.i1").size(), is(1));
	    	assertThat($("div.i2").size(), is(1));
	    	assertThat($("div.a1").size(), is(1));
    	}

    	$("body").focus();
    	assertThat($("body").is(":focus"), is(true));
    	if (!($.driver().get() instanceof HtmlUnitDriver)) {
	    	assertThat($("div").size(), is(5));
	    	assertThat($("div.i1").size(), is(1));
	    	assertThat($("div.i2").size(), is(1));
	    	assertThat($("div.a1").size(), is(1));
	    	assertThat($("div.im1").size(), is(1));
	    	assertThat($("div.body.focus").size(), is(1));
	    	assertThat($("div.body.click").size(), is(0));
    	} else {
    		// HtmlUnitDriver will focus, but won't trigger the focus event for the <body> tag either!
	    	assertThat($("div").size(), is(3));
	    	assertThat($("div.i1").size(), is(1));
	    	assertThat($("div.i2").size(), is(1));
	    	assertThat($("div.a1").size(), is(1));
    	}
    }

    private void removeDivBodyFocusAddedWhenDriverIsIeAndWhenFocusingInput() {
        // If, when the test is run, the IE is the focused window, it adds a new div (due to the focus event). We then remove it.
        // If not (that is, IE is being executed, but the window never gets focused), the div is not added and thus we don't have to remove anything
		JavascriptExecutor driver = ((JavascriptExecutor) $.driver().get());
		SeleniumQueryObject divBodyFocus = $("div.body.focus");
		if (divBodyFocus.size() == 1 && driver instanceof InternetExplorerDriver) {
			driver.executeScript("document.body.removeChild(arguments[0]);", divBodyFocus.get(0));
		}
	}

    @Test(expected = UnsupportedXPathPseudoClassException.class)
    public void focus_pseudoClass_selector() {
    	$("#i1").focus();
    	assertThat($("#i1").is(":focus"), is(true));
    	assertThat($(":focus").size(), is(1));
    	assertThat($(":focus").attr("id"), is("i1"));
    }

}