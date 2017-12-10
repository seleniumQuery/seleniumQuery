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

package endtoend.functions.jquery.forms.val;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testutils.DriverInTest.isFirefoxDriver;
import static testinfrastructure.testutils.DriverInTest.isIEDriver;
import static testinfrastructure.testutils.DriverInTest.isOperaDriver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.ChromeOnly;
import testinfrastructure.junitrule.annotation.ChromeShouldBeSkipped;
import testinfrastructure.junitrule.annotation.EdgeSkip;
import testinfrastructure.junitrule.annotation.FirefoxOnly;
import testinfrastructure.junitrule.annotation.FirefoxSkip;
import testinfrastructure.junitrule.annotation.HtmlUnitOnly;
import testinfrastructure.junitrule.annotation.JavaScriptDisabledOnly;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;
import testinfrastructure.junitrule.annotation.OperaSkip;
import testinfrastructure.junitrule.annotation.SafariOnly;
import testinfrastructure.junitrule.annotation.SafariSkip;

// http://jsbin.com/futuhipuhi/2/edit?html,js,output
public class ValFunction_ContentEditableTest {

    private static final String ID_DIV_WITHOUT_CONTENTEDITABLE_BUT_WITH_TEXT = "#div-without-contenteditable-but-with-text";

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();


    @Test
    public void val_read__divWithoutContentEditableAttribute___readsEmptyString() {
        assertThat($(ID_DIV_WITHOUT_CONTENTEDITABLE_BUT_WITH_TEXT).val(), is(""));
    }

    @Test
    @ChromeShouldBeSkipped("chrome can't focus, see test below")
    @JavaScriptEnabledOnly
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    public void val_write__divWithoutContentEditableAttribute___hasNoEffect() {
        verifyAttemptToChangeValOfDivWithoutContentEditableHasNoEffect();
    }

    @Test
    @FirefoxOnly
    @JavaScriptDisabledOnly
    public void val_write__divWithoutContentEditableAttribute___hasNoEffect__Firefox_JS_OFF() {
        verifyAttemptToChangeValOfDivWithoutContentEditableHasNoEffect();
    }

    @Test
    @ChromeOnly
    public void val_write__divWithoutContentEditableAttribute___throws_exception__CHROME() {
        try {
            verifyAttemptToChangeValOfDivWithoutContentEditableHasNoEffect();
        } catch (org.openqa.selenium.WebDriverException e) {
            assertThat(e.getMessage(), startsWith("unknown error: cannot focus element"));
        }
    }

    @Test
    @HtmlUnitOnly
    @JavaScriptDisabledOnly
    public void val_write__divWithoutContentEditableAttribute___hasNoEffect__HtmlUnit_JS_OFF() {
        verifyAttemptToChangeValOfDivWithoutContentEditableHasNoEffect();
    }

    private void verifyAttemptToChangeValOfDivWithoutContentEditableHasNoEffect() {
        // when
        $(ID_DIV_WITHOUT_CONTENTEDITABLE_BUT_WITH_TEXT).val("SHOULD HAVE NO EFFECT");
        // then
        assertThat($(ID_DIV_WITHOUT_CONTENTEDITABLE_BUT_WITH_TEXT).val(), is(""));
        assertThat($(ID_DIV_WITHOUT_CONTENTEDITABLE_BUT_WITH_TEXT).text(), is("DIV INITIAL TEXT"));
    }

    @Test
    @SafariSkip("Due to WebDriverException: Cannot set the selection end")
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    public void val_readAndWrite___divWithContentEditableAttribute___with_INITIAL_VALUE() {
        verifyEditableDivAcceptsHtmlCharsCorrectly("#editable", "DIZ EZ EDITABLE");
    }

    @Test
    @FirefoxSkip @SafariSkip
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("org.openqa.selenium.ElementNotVisibleException: You may only interact with visible elements -- TODO check")
    public void val_readAndWrite___divWithContentEditableAttribute___with_EMPTY_STARTING_VALUE() {
        verifyEditableDivAcceptsHtmlCharsCorrectly("#editable-empty", "");
    }

    @Test(expected = org.openqa.selenium.WebDriverException.class)
    @SafariOnly
    public void val_readAndWrite___divWithContentEditableAttribute___with_EMPTY_STARTING_VALUE__hasProblemsOnSafari() {
        verifyEditableDivAcceptsHtmlCharsCorrectly("#editable-empty", "");
    }

    @Test(expected = InvalidElementStateException.class)
    @FirefoxOnly
    public void val_readAndWrite___divWithContentEditableAttribute___with_EMPTY_STARTING_VALUE__hasProblemsOnFirefox() {
        verifyEditableDivAcceptsHtmlCharsCorrectly("#editable-empty", "");
    }

    private void verifyEditableDivAcceptsHtmlCharsCorrectly(String editableDivId, String initialValue) {
        // checks initial value
		assertThat($(editableDivId).val(), is(""));
		assertThat($(editableDivId).text(), is(initialValue));

        // changes value to weird HTML text
		$(editableDivId).val("TYPED <a>& STUFF");
		assertThat($(editableDivId).val(), is("")); // val() never changes value of contenteditable elements
		assertThat($(editableDivId).text(), is("TYPED <a>& STUFF"));

        assertThat($(editableDivId).html(), is(addCharsDependingOnDriver("TYPED &lt;a&gt;&amp; STUFF")));
	}

    private String addCharsDependingOnDriver(String resultingHtml) {
        WebDriver driver = $.driver().get();
        if (isIEDriver(driver)) {
            return " " + resultingHtml;
		} else if (isFirefoxDriver(driver) || isOperaDriver(driver)) {
            return resultingHtml + " ";
        }
        return resultingHtml;
    }

}
