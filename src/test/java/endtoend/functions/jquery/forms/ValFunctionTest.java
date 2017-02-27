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

package endtoend.functions.jquery.forms;

import org.hamcrest.Matcher;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.*;
import testinfrastructure.testutils.SauceLabsUtils;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static testinfrastructure.testutils.DriverInTest.*;

// http://jsbin.com/futuhipuhi/2/edit?html,js,output
public class ValFunctionTest {

    private static final String ID_DIV_WITHOUT_CONTENTEDITABLE_BUT_WITH_TEXT = "#div-without-contenteditable-but-with-text";
    private static final String ID_SELECT_1 = "#select-1";

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(ValFunctionTest.class);


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
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("Expected: is 's1o2-value' but: was '' -- TODO check")
    public void val_read__SELECT___readsSelectedOptionsValue() {
        assertThat($(ID_SELECT_1).val(), is("s1o2-value"));
    }

    @Test
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("Expected: is 's1o1-value' but: was '' -- TODO check")
    public void val_read__OPTION__withValueAttribute__readsValueAttribute() {
        assertThat($("#option-1-of-select-1").val(), is("s1o1-value"));
        assertThat($("#option-2-of-select-1").val(), is("s1o2-value"));
    }

    @Test
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("Expected: is 's1o3-text' but: was '' -- TODO check")
    public void val_read__OPTION__withoutValueAttribute__readsText() {
        assertThat($("#option-3-of-select-1").val(), is("s1o3-text"));
    }

    @Test(expected = NoSuchElementException.class)
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("Didnt throw exception-- TODO check")
    public void val_write__SELECT___throwsException__ifThereIsNoOptionWithValue() {
        $(ID_SELECT_1).val("NO OPTION WITH THIS STRING AS VALUE");
    }

    @Test
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("Expected: is 's1o1-value' but: was '' -- TODO check")
    public void val_write__SELECT___changesValue__ifThere_Is_OptionWithValue() {
        // when
        $(ID_SELECT_1).val("s1o1-value");
        // then
        assertThat($(ID_SELECT_1).val(), is("s1o1-value"));
    }

    @Test
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("SEVERAL Expected: is 'X' but: was 'X' -- TODO check")
    public void val_others() {
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

    @Test(expected = ElementNotVisibleException.class)
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
		} else if ((isFirefoxDriver(driver) && isRemoteDriver(driver)) || isOperaDriver(driver)) {
            return resultingHtml + " ";
        } else if (isFirefoxDriver(driver)) {
            return resultingHtml + "<br>";
        }
        return resultingHtml;
    }

    /**
     * IE can't read the values properly. Gives null for .val() and "" for .text().
     */
    @Test
    @ChromeOnly
    public void iframe_with_DesignMode_ON___defaultValues__are__read__correctly() { verifyIframeTextRead("iframe-body-content"); }

    @Test
    @FirefoxOnly
    public void iframe_with_DesignMode_ON___defaultValues__are__read__correctly_ff() { verifyIframeTextRead("iframe-body-content"); }

    @Test
    @SafariOnly
    public void iframe_with_DesignMode_ON___defaultValues__are__read__correctly_safari() { verifyIframeTextRead("iframe-body-content"); }

    @Test
    @EdgeOnly
    public void iframe_with_DesignMode_ON___defaultValues__are__read__correctly__EDGE() { verifyIframeTextRead("iframe-body-content "); }

    private void verifyIframeTextRead(String expectedIframeText) {
        $.url(SauceLabsUtils.fixUrlForRemoteTest($.url()));
        $.driver().get().switchTo().frame("iframe-with-design-mode-on");
        assertThat($("body").text(), is(expectedIframeText));
        assertThat($("body").val(), is(""));
    }

    /**
     * IE simply dies when we try to FETCH an element from the iframe. If gives no stacktrace info.
     * This had to be done for IE to work: $.driver().get().switchTo().frame(0);
     * They suggested, but didnt have effect: WebElement editable =  $.driver().get().switchTo().activeElement();
     * Also suggested $.driver().get().switchTo().defaultContent(); before switching to frame. No use.
     *
     *
     * Safari dies when typing.
     * Firefox does not clear selection.
     * Edge does not read the value correctly (nor types).
     * Chrome works 100%.
     * HtmlUnit reads, but doesnt type.
     * PhantomJS same as Edge.
     */
    @Test
    @JavaScriptEnabledOnly
    @ChromeOnly
    public void iframe_with_DesignMode_ON___values_are_CHANGED_correctly__CHROME() {
        verifyTypingAtIframeChangesValueAsExpected(is("[typed-value]"));
    }
    @Test
    @JavaScriptEnabledOnly
    @FirefoxOnly
    public void iframe_with_DesignMode_ON___values_are_CHANGED_correctly__FIREFOX() {
        verifyTypingAtIframeChangesValueAsExpected(allOf(startsWith("[typed-value]"), not(is("[typed-value]"))));
    }

    private void verifyTypingAtIframeChangesValueAsExpected(Matcher<String> resultTextMatcher) {
        $.url(SauceLabsUtils.remoteAddressForFile($.url()));
        $("#iframe-debug").waitUntil().text().isEqualTo("designMode successfully set to on by JavaScript");
        $.driver().get().switchTo().frame("iframe-with-design-mode-on");
        $("body").val("[typed-value]");
        assertThat($("body").val(), is(""));
        assertThat($("body").text(), resultTextMatcher);
    }

    @Test
    @JavaScriptDisabledOnly
    @FirefoxOnly
    public void iframe_with_DesignMode_ON____JS_OFF____values_DONT_change__but_no_exception_is_thrown_as_well() {
        $.driver().get().switchTo().frame("iframe-with-design-mode-on");
        $("body").val("something");
        assertThat($("body").val(), is(""));
        assertThat($("body").text(), is("iframe-body-content"));

        $.driver().get().switchTo().defaultContent();
        assertThat($("#iframe-debug").text(), is("no-javascript")); // make sure JS didn't run!
    }

}
