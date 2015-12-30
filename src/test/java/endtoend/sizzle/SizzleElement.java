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

package endtoend.sizzle;

import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.JavaScriptOnly;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.testutils.DriverInTest;

import java.util.ArrayList;

import static io.github.seleniumquery.SeleniumQuery.$;
import static java.util.Arrays.asList;

public class SizzleElement extends SizzleTest {

    @ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);
    @Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

    @Test
    public void element_selectors() {
//TODO(issue#33) - Have empty selectors $("") return an empty list right off the bat
//        equal(Sizzle("").size(), 0, "Empty selector returns an empty array");
//        deepEqual(Sizzle("div", document.createTextNode("")),[],"Text element as context fails silently");

        WebElement form = id("form");
        for (String s : asList("", " ", "   ", "\t", "\n", "\r", "\r\n")) {
            ok(!Sizzle(form).is(s), "Empty string passed to matchesSelector does not match");
        }

//TODO(issue#33)
//        equal(Sizzle(" ").size(), 0, "Empty selector returns an empty array");
//        equal(Sizzle("\t").size(), 0, "Empty selector returns an empty array");

        SeleniumQueryObject all = Sizzle("*");
        ok(all.size() >= 30, "Select all");
    }
    @Test @JavaScriptOnly
    public void element_selectors_2() {
        SeleniumQueryObject all = Sizzle("*");
        for (int i = 0; i < all.size(); i++) {
            String nodeType = executeJS("return arguments[0].nodeType", all.get(i)).toString();
            if (nodeType.equals("8")) {
                ok(false, "Select all elements, no comment nodes");
            }
        }
    }
    @Test
    public void element_selectors_3() {
        t("Element Selector", "html", new String[]{"html"});
        t("Element Selector", "body", new String[]{"body"});
        t("Element Selector", "#qunit-fixture p", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});

        t("Leading space", " #qunit-fixture p", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t("Leading tab", "\t#qunit-fixture p", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t("Leading carriage return", "\r#qunit-fixture p", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t("Leading line feed", "\n#qunit-fixture p", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t("Leading form feed", "\f#qunit-fixture p", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t("Trailing space", "#qunit-fixture p ", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t("Trailing tab", "#qunit-fixture p\t", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t("Trailing carriage return", "#qunit-fixture p\r", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t("Trailing line feed", "#qunit-fixture p\n", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});
        t("Trailing form feed", "#qunit-fixture p\f", new String[]{"firstp", "ap", "sndp", "en", "sap", "first"});

        t("Parent Element", "dl ol", new String[]{"empty", "listWithTabIndex"});
        t("Parent Element (non-space descendant combinator)", "dl\tol", new String[]{"empty", "listWithTabIndex"});

        // Check for unique-ness and sort order
        deepEqual(Sizzle("p, div p"), Sizzle("p").get(), "Check for duplicates: p, div p");
    }
    @Test @JavaScriptOnly
    public void element_selectors_4() {
        executeJS("jQuery(\"<h1 id='h1'/><h2 id='h2'/><h2 id='h2-2'/>\").prependTo(\"#qunit-fixture\");");
        // the original tests checked the order, but we won't struggle for that...
        tio("Checking sort order", "#qunit-fixture h2, #qunit-fixture h1", new String[]{"h1", "h2", "h2-2"});
        tio("Checking sort order", "#qunit-fixture h2:first, #qunit-fixture h1:first", new String[]{"h1", "h2"});
        tio("Checking sort order", "#qunit-fixture p, #qunit-fixture p a", new String[]{"firstp", "simon1", "ap", "google", "groups", "anchor1", "mark", "sndp", "en", "yahoo", "sap", "anchor2", "simon", "first"});
    }
    @Test
    public void element_selectors_5() {
        // Test Conflict ID
        WebElement lengthtest = id("lengthtest");
        deepEqual(Sizzle("#idTest", lengthtest), q("idTest"), "Finding element with id of ID.");
        deepEqual(Sizzle("[name='id']", lengthtest), q("idTest"), "Finding element with id of ID.");
        deepEqual(Sizzle("input[id='idTest']", lengthtest), q("idTest"), "Finding elements with id of ID.");

        WebElement siblingTest = id("siblingTest");
        deepEqual(Sizzle("div em", siblingTest), new ArrayList<WebElement>(), "Element-rooted QSA does not select based on document context");
// TODO(issue#52)
//        deepEqual(Sizzle("div em, div em, div em:not(div em)", siblingTest), new ArrayList<WebElement>(), "Element-rooted QSA does not select based on document context");

// TODO(issue#53)
//        deepEqual(Sizzle("div em, em\\,", siblingTest), new ArrayList<WebElement>(), "Escaped commas do not get treated with an id in element-rooted QSA");
    }
    @Test @JavaScriptOnly
    public void element_selectors_6() {
/*
        String html = "";
        for (i = 0; i < 100; i++) {
            html = "<div>" + html + "</div>";
        }
        html = jQuery(html).appendTo(document.body);
        ok(Sizzle("body div div div").size() != 0, "No stack or performance problems with large amounts of descendents");
        ok(Sizzle("body>div div div").size() != 0, "No stack or performance problems with large amounts of descendents");
        html.remove();
*/
        // Real use case would be using .watch in browsers with window.watch (see Issue #157)
        executeJS("document.getElementById('qunit-fixture').appendChild(document.createElement('toString')).id = 'toString';");
        t("Element name matches Object.prototype property", "tostring#toString", new String[]{"toString"});

        if (DriverInTest.isNotHtmlUnitDriver($.driver().get())) {
            t("Element name matches Object.prototype property", "toString#toString", new String[]{"toString"});
        } else {
            // #Cross-Driver
            // HtmlUnit is case SENSITIVE, BUT considers tags to be LOWERCASE :(
            // - One way to work around this is to detect if the browser is HtmlUnit and add case-insensitiveness there
            //          --> but we cant because we currently don't send the WebDriver instance to the CSS->XPath translator.
            // - Another way would be to just add case-insensitiveness to everyone
            //          --> we wont do this because right now that is just too much work for so little gain.
            //          --> if in the future this proves necessary, than we may do it.
            t("Element name matches Object.prototype property", "tostring#toString", new String[]{"toString"});
        }
    }

}