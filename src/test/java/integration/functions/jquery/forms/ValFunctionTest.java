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

package integration.functions.jquery.forms;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.by.DriverVersionUtils;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ValFunctionTest {
	
	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(ValFunctionTest.class);

	// http://jsbin.com/futuhipuhi/2/edit?html,js,output
    @Test
    public void val() throws Exception {
		assertThat($("#div-with-text-bozo").val(), is(""));

		try {
			$("#div-with-text-bozo").val("SHOULD HAVE NO EFFECT");
            if (!DriverVersionUtils.getInstance().isHtmlUnitDriver($.driver().get())) {
				fail($.driver().get().getClass().toString());
			}
		} catch (Exception ignore) { }
//		assertThat($("#div-with-text-bozo").val(), is("SHOULD HAVE NO EFFECT")); // #disagree jquery sets the val() in a div, but we want as below!
	    assertThat($("#div-with-text-bozo").val(), is("")); // #disagree - this is what we want
		assertThat($("#div-with-text-bozo").text(), is("BOZO"));

		// <select> and <option> ---------------------------------------------------------------------------------------
		assertThat($("#s1").val(), is("a1"));

		assertThat($("#opt").val(), is("a1"));
		$("#opt").val("NEW-OPTION-VALUE"); // #disagree - this willhave no effect

//		assertThat($("#opt").val(), is("NEW-OPTION-VALUE")); // #disagree - we don't set values of <option>s through val()
		assertThat($("#opt").val(), is("a1"));
//		assertThat($("#s1").val(), is("NEW-OPTION-VALUE")); // #disagree (it's be so as options's val() changed)

//		$("#s1").val("NEW-SELECT-VALUE"); // #disagree we dont set selectos to non-existant options
// 		assertThat($("#s1").val(), is(nullValue())); // #disagree so the option didnt change (it'd be null as there'd be no option with that value)

//		$("#s1").val("NEW-OPTION-VALUE"); // #disagree, this option does not exist, as we didnt change its value above
		//assertThat($("#s1").val(), is("NEW-OPTION-VALUE"));// #disagree, the value was not set
//		assertThat($("#s1").val(), is(nullValue()));// #disagree, the value was not set

		// TEXTAREA ----------------------------------------------------------------------------------------------------
		assertThat($("#ta").val().trim(), is("bozo"));
		assertThat($("#ta").val(), is("\t\tbozo\n\t")); // Firefox/Chrome/PhantomJS/IE10

		$("#ta").val("NEW-TEXTAREA-VALUE");
		assertThat($("#ta").val(), is("NEW-TEXTAREA-VALUE"));

		// <input type="text"> -----------------------------------------------------------------------------------------
		assertThat($("#i1").val(), is("ii!"));
		$("#i1").val("NEW-INPUT-TEXT-VALUE");
		assertThat($("#i1").val(), is("NEW-INPUT-TEXT-VALUE"));

		// <input type="radio"> ----------------------------------------------------------------------------------------
		assertThat($("#ir1").val(), is("input-radio-value"));
		$("#ir1").val("new-radio-value"); // #disagree - should have no effect
		//assertThat($("#ir1").val(), is("new-radio-value")); // #disagree - it didnt change as line above should have no effect
		assertThat($("#ir1").val(), is("input-radio-value")); // #disagree - so the value must be the same

		// <input type="checkbox"> -------------------------------------------------------------------------------------
		assertThat($("#ic1").val(), is("input-checkbox-value"));
		$("#ic1").val("new-checkbox-value"); // #disagree - should have no effect
		//assertThat($("#ic1").val(), is("new-checkbox-value")); // #disagree - it didnt change as line above should have no effect
		assertThat($("#ic1").val(), is("input-checkbox-value")); // #disagree - so the value must be the same

		// <input type="hidden"> ---------------------------------------------------------------------------------------
		assertThat($("#ih1").val(), is("input-hidden-value"));
		$("#ih1").val("new-hidden-value"); // #disagree - should have no effect
		//assertThat($("#ih1").val(), is("new-hidden-value")); // #disagree - it didnt change as line above should have no effect
		assertThat($("#ih1").val(), is("input-hidden-value")); // #disagree - so the value must be the same

		// <input type="submit"> ---------------------------------------------------------------------------------------
		assertThat($("#is1").val(), is("input-submit-value"));
		$("#is1").val("new-submit-value"); // #disagree - should have no effect
		//assertThat($("#is1").val(), is("new-submit-value")); // #disagree - it didnt change as line above should have no effect
		assertThat($("#is1").val(), is("input-submit-value")); // #disagree - so the value must be the same

		// <input type="button"> ---------------------------------------------------------------------------------------
		assertThat($("#ib1").val(), is("input-button-value"));
		$("#ib1").val("new-button-value"); // #disagree - should have no effect
		//assertThat($("#ib1").val(), is("new-button-value")); // #disagree - it didnt change as line above should have no effect
		assertThat($("#ib1").val(), is("input-button-value")); // #disagree - so the value must be the same

		// <select id="select-option-without-value"> -------------------------------------------------------------------
		assertThat($("#select-option-without-value").val(), is("selected option without value"));

		// <div id="editable" contenteditable="true"> ------------------------------------------------------------------
		testEditableDiv("#editable", "DIZ EZ EDITABLE");

		// <div id="editable-empty" contenteditable="true"> ------------------------------------------------------------
		try {
			testEditableDiv("#editable-empty", "");
		} catch (ElementNotVisibleException e) {
			if (!($.driver().get() instanceof FirefoxDriver)) {
				throw e;
			}
		}
	}

	private void testEditableDiv(String editableDivId, String initialValue) {
		assertThat($(editableDivId).val(), is(""));
		assertThat($(editableDivId).text(), is(initialValue));
		$(editableDivId).val("TYPED <a>& STUFF");
		assertThat($(editableDivId).text(), is("TYPED <a>& STUFF"));
		//assertThat($("#editable").val(), is("SHOULD HAVE NO EFFECT")); // #disagree jquery sets the val() in a div, we dont want that...
		assertThat($(editableDivId).val(), is("")); // #disagree - ...so the value must be the same
		assertThat($(editableDivId).text(), is("TYPED <a>& STUFF"));

		if ($.driver().get() instanceof InternetExplorerDriver) {
			assertThat($(editableDivId).html(), is(" TYPED &lt;a&gt;&amp; STUFF")); // notice the space at the beginning
		} else if ($.driver().get() instanceof FirefoxDriver) {
			assertThat($(editableDivId).html(), is("TYPED &lt;a&gt;&amp; STUFF<br>")); // notice the <br> at the end
		} else {
			assertThat($(editableDivId).html(), is("TYPED &lt;a&gt;&amp; STUFF"));
		}
	}

}