package infrastructure;

import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IntegrationTestUtils {

    private static final String TEST_SRC_FOLDER = "src/test/java/";

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

    public static String htmlTestFileUrl(Class<?> clazz) {
        String classFullName = clazz.getName();
        String classPath = classFullName.replace('.', '/');
        String htmlPath = TEST_SRC_FOLDER + classPath + ".html";
        return new File(htmlPath).toURI().toString();
    }

    protected void tIS(String assertionName, String selector, String[] expectedIds) {
        for (String expectedId : expectedIds) {
            System.out.println("$(\"#"+expectedId+"\").is(\""+selector+"\")");
            assertTrue(assertionName, $("#"+expectedId).is(selector));
        }
    }

    public static String[] ids(String selector) {
        SeleniumQueryObject f = $(selector);
        List<String> actualIds = new ArrayList<String>();
        for (WebElement webElement : f) {
            actualIds.add(id(webElement));
        }
        return actualIds.toArray(new String[actualIds.size()]);
    }

    /**
     * Gets the @id of the given element.
     * @param webElement element to extract id.
     * @return the ID attribute.
     */
    public static String id(WebElement webElement) {
        return webElement.getAttribute("id");
    }

    /**
     * Gets the @id of the first element in the matched set.
     * @param sq matched set to extract id of first element from.
     * @return the ID attribute.
     */
    public static String id(SeleniumQueryObject sq) {
        return id(sq.get(0));
    }

    /**
     * Gets the @ids of all elements in the matched set.
     * @param sq matched set to extract ids from.
     * @return list of IDs.
     */
    public static List<String> ids(SeleniumQueryObject sq) {
        List<String> ids = new ArrayList<String>(sq.size());
        for (WebElement element : sq) {
            ids.add(id(element));
        }
        return ids;
    }

    public static void equal(Object o1, Object o2, String msg) {
        assertEquals(msg, o2, o1);
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