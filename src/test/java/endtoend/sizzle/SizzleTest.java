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

import io.github.seleniumquery.InternalSeleniumQueryObjectFactory;
import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Base class used by all sizzle tests.
 */
public class SizzleTest {

    protected SeleniumQueryObject Sizzle(String selector) {
        return $(selector);
    }

    @SuppressWarnings("unused")
    protected SeleniumQueryObject Sizzle(String selector, Boolean b, Boolean c, List<WebElement> x) {
        return InternalSeleniumQueryObjectFactory.instance().createWithInvalidSelector($.driver().get(), x, null).find(selector);
    }

    protected SeleniumQueryObject Sizzle(WebElement we) {
        return $(we);
    }

    protected SeleniumQueryObject Sizzle(String selector, WebElement we) {
        SeleniumQueryObject sq = $(we);
        return sq.find(selector);
    }

    protected static final class Sizzle {
        public static boolean matchesSelector(WebElement el, String selector) {
            return $(el).is(selector);
        }
    }

    /**
     * Asserts that a select matches the given IDs
     * -param {String} a - Assertion name
     * -param {String} b - Sizzle selector
     * -param {String} c - Array of ids to construct what is expected
     * -example t("Check for something", "//[a]", ["foo", "baar"]);
     * -result returns true if "//[a]" return two elements with the IDs 'foo' and 'baar'
     */
    protected void t(String assertionName, String selector, String[] expectedIds) {
        List<String> actualIds = extractIdsList(selector);
        List<String> expectedIdsList = asList(expectedIds);
        assertEquals(assertionName + " --> Lists differ!", expectedIdsList.toString(), actualIds.toString());
    }

    /**
     * Same as {@link #t(String, String, String[])}, except that this one [I]gnores expected ids [O]rder.
     */
    protected void tio(String assertionName, String selector, String[] expectedIds) {
        List<String> actualIds = extractIdsList(selector);
        List<String> expectedIdsList = asList(expectedIds);
        Collections.sort(actualIds);
        Collections.sort(expectedIdsList);
        assertEquals(assertionName + " --> Lists differ!", expectedIdsList.toString(), actualIds.toString());
    }

    private List<String> extractIdsList(String selector) {
        SeleniumQueryObject f = $(selector);

        List<String> actualIds = new ArrayList<>();
        for (WebElement webElement : f) {
            actualIds.add(webElement.getAttribute("id"));
        }
        return actualIds;
    }

    protected void deepEqual(SeleniumQueryObject o1, List<WebElement> o2, String msg) {
        assertEquals(msg, o2, o1.get());
    }

    protected Object executeJS(String javaScriptCode, Object... args) {
        return ((JavascriptExecutor) $.driver().get()).executeScript(javaScriptCode, args);
    }

    /**
     * Returns an array of elements with the given IDs
     * Example: q("main", "foo", "bar")
     * Result: [<div id="main">, <span id="foo">, <input id="bar">]
     */
    protected List<WebElement> q(String... ids) {
        List<WebElement> els = new ArrayList<>();
        for (String id : ids) {
            els.add(id(id));
        }
        return els;
    }

    protected WebElement id(String id) {
        return $.driver().get().findElement(By.id(id));
    }

    protected void ok(boolean b, String msg) {
        assertTrue(msg, b);
    }

}