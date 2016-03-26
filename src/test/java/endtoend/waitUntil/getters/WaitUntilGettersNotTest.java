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

package endtoend.waitUntil.getters;

import io.github.seleniumquery.SeleniumQueryWaitAndOrThen;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.JavaScriptOnly;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WaitUntilGettersNotTest {

    @ClassRule
    @Rule
    public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(WaitUntilGettersTest.class);

    private List<WebElement> divs;

    @Before
    public void setUp() throws Exception {
        divs = $("#d1,#d2").get();
    }

    @Test
    public void size_getter() {
        assertThat($("div").waitUntil().size().not().isEqualTo(3).then().get(), is(divs));
    }

    @Test
    @JavaScriptOnly
    public void attr_getter() {
        assertThat($("div").waitUntil().attr("data-attr").not().isEqualTo("yadda").then().get(), is(divs));
    }

    @Test
    @JavaScriptOnly
    public void prop_getter() {
        assertThat($("div").waitUntil().prop("tagName").not().isEqualTo("NOT").then().get(), is(divs));
    }

    @Test
    public void html_getter() {
        assertThat($("div").waitUntil().html().not().isEqualTo("NOT").then().get(), is(divs));
    }

    @Test
    public void text_getter() {
        assertThat($("div").waitUntil().text().not().isEqualTo("NOT").then().get(), is(divs));
    }

    @Test
    public void size_getter_toString() {
        // when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $("div").waitUntil().size().isEqualTo(2);
        // then
        String expectedToString = "$(\"div\").waitUntil().size().isEqualTo(2)";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
    }

    @Test
    @JavaScriptOnly
    public void attr_getter_toString() {
        // when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $("div").waitUntil().attr("data-attr").isEqualTo("data-attr-value");
        // then
        String expectedToString = "$(\"div\").waitUntil().attr(\"data-attr\").isEqualTo(\"data-attr-value\")";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
    }

    @Test
    @JavaScriptOnly
    public void prop_getter_toString() {
        // when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $("div").waitUntil().prop("tagName").isEqualTo("DIV");
        // then
        String expectedToString = "$(\"div\").waitUntil().prop(\"tagName\").isEqualTo(\"DIV\")";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
    }

    @Test
    public void html_getter_toString() {
        // when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $("div").waitUntil().html().isEqualTo("&gt;&lt;");
        // then
        String expectedToString = "$(\"div\").waitUntil().html().isEqualTo(\"&gt;&lt;\")";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
    }

    @Test
    public void text_getter_toString() {
        // when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $("div").waitUntil().text().contains("< >");
        // then
        String expectedToString = "$(\"div\").waitUntil().text().contains(\"< >\")";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
    }

    @Test
    public void text_not_contains_toString() {
        // when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $("div").waitUntil().text().not().contains("SOMETHING-ELSE");
        // then
        String expectedToString = "$(\"div\").waitUntil().text().not().contains(\"SOMETHING-ELSE\")";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
    }

    @Test
    public void val_not_isEqualTo_toString() {
        // when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $("div").waitUntil().val().not().isEqualTo("STUFF");
        // then
        String expectedToString = "$(\"div\").waitUntil().val().not().isEqualTo(\"STUFF\")";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
    }

    @Test
    @JavaScriptOnly
    public void attr_not_matches_toString() {
        // when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $("div").waitUntil().attr("data-attr").not().matches("x");
        // then
        String expectedToString = "$(\"div\").waitUntil().attr(\"data-attr\").not().matches(\"x\")";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
    }

    @Test
    public void size_not_isGreaterThan_toString() {
        // when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $("div").waitUntil().size().not().isGreaterThan(999);
        // then
        String expectedToString = "$(\"div\").waitUntil().size().not().isGreaterThan(999)";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
    }

    @Test
    @JavaScriptOnly
    public void prop_not_isLessThan_toString() {
        // when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $("div").waitUntil().prop("tagName").not().isLessThan(-1);
        // then
        String expectedToString = "$(\"div\").waitUntil().prop(\"tagName\").not().isLessThan(-1)";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
    }

    @Test
    public void html_not_isGreaterThan_toString() {
        // when
        SeleniumQueryWaitAndOrThen waitAndOrThen = $("div").waitUntil().html().not().isGreaterThan(999);
        // then
        String expectedToString = "$(\"div\").waitUntil().html().not().isGreaterThan(999)";
        assertThat(waitAndOrThen.toString(), is(expectedToString));
        assertThat(waitAndOrThen.then().toString(), is(expectedToString));
    }

}