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
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.ChromeOnly;
import testinfrastructure.junitrule.annotation.EdgeOnly;
import testinfrastructure.junitrule.annotation.FirefoxOnly;
import testinfrastructure.junitrule.annotation.JavaScriptDisabledOnly;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;
import testinfrastructure.junitrule.annotation.SafariOnly;
import testinfrastructure.testutils.SauceLabsUtils;

public class ValFunction_IframeTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

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
