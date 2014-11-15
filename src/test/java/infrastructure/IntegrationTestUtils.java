package infrastructure;

import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class IntegrationTestUtils {

    /**
     * Asserts that a select matches the given IDs
     * -param {String} a - Assertion name
     * -param {String} b - Sizzle selector
     * -param {String} c - Array of ids to construct what is expected
     * -example t("Check for something", "//[a]", ["foo", "baar"]);
     * -result returns true if "//[a]" return two elements with the IDs 'foo' and 'baar'
     */
    public static NegativeAbleTest t(String assertionName, String selector, String... expectedIds) {
        List<String> actualIds = asList(ids(selector));
        List<String> expectedIdsList = asList(expectedIds);
        assertEquals(assertionName + " --> Lists differ!", expectedIdsList.toString(), actualIds.toString());
        return new NegativeAbleTest(assertionName);
    }

    public static NegativeAbleTest t(String assertionName, String selector, int expectedMatchedSetSize) {
        int actualIds = ids(selector).length;
        assertEquals(assertionName + " --> Lists differ!", expectedMatchedSetSize, actualIds);
        return new NegativeAbleTest(assertionName);
    }

    public static String[] ids(String selector) {
        SeleniumQueryObject f = $(selector);
        List<String> actualIds = new ArrayList<String>();
        for (WebElement webElement : f) {
            actualIds.add(webElement.getAttribute("id"));
        }
        return actualIds.toArray(new String[actualIds.size()]);
    }

    /**
     * The "negative" is just some assertion to make sure your test is really working (and not just silently failing).
     */
    public static class NegativeAbleTest {
        private String originalAssertionName;
        private NegativeAbleTest(String originalAssertionName) {
            this.originalAssertionName = originalAssertionName;
        }
        public void negative(String selector, String... ids) {
            t("NEGATION of: "+originalAssertionName, selector, ids);
        }
        public void negative(String selector, int expectedMatchedSetSize) {
            t("NEGATION of: "+originalAssertionName, selector, expectedMatchedSetSize);
        }
    }

}