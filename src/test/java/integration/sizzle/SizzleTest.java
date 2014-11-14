package integration.sizzle;

import io.github.seleniumquery.SQLocalFactory;
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
        return SQLocalFactory.createWithInvalidSelector($.browser.getDefaultDriver(), x, null).find(selector);
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

        List<String> actualIds = new ArrayList<String>();
        for (WebElement webElement : f) {
            actualIds.add(webElement.getAttribute("id"));
        }
        return actualIds;
    }

    protected void equal(Object o1, Object o2, String msg) {
        assertEquals(msg, o2, o1);
    }

    protected void deepEqual(SeleniumQueryObject o1, List<WebElement> o2, String msg) {
        assertEquals(msg, o2, o1.get());
    }

    protected Object executeJS(String javaScriptCode, Object... args) {
        return ((JavascriptExecutor) $.browser.getDefaultDriver()).executeScript(javaScriptCode, args);
    }

    /**
     * Returns an array of elements with the given IDs
     * Example: q("main", "foo", "bar")
     * Result: [<div id="main">, <span id="foo">, <input id="bar">]
     */
    protected List<WebElement> q(String... ids) {
        List<WebElement> els = new ArrayList<WebElement>();
        for (String id : ids) {
            els.add(id(id));
        }
        return els;
    }

    protected WebElement id(String id) {
        return $.browser.getDefaultDriver().findElement(By.id(id));
    }

    protected void ok(boolean b, String msg) {
        assertTrue(msg, b);
    }

}