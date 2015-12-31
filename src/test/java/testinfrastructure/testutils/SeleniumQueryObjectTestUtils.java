package testinfrastructure.testutils;

import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SeleniumQueryObjectTestUtils {

    public static void verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre(String selector, String... ids) {
        SeleniumQueryObject seleniumQueryObject = $(selector);
        assertThat(seleniumQueryObject.size(), is(ids.length));
        for (int i = 0; i < ids.length; i++) {
            WebElement webElement = seleniumQueryObject.get(i);
            String expectedId = ids[i];
            assertThat(webElement.getAttribute("id"), is(expectedId));
        }
    }

    public static void verifySeleniumQueryObjectHasElements(SeleniumQueryObject seleniumQueryObject, WebElement... elements) {
        assertThat(seleniumQueryObject.size(), is(elements.length));
        for (int i = 0; i < elements.length; i++) {
            WebElement expectedElement = elements[i];
            WebElement actualElement = seleniumQueryObject.get(i);
            assertThat(actualElement, is(expectedElement));
        }
    }

}