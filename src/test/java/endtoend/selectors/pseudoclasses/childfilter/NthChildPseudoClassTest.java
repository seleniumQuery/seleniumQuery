package endtoend.selectors.pseudoclasses.childfilter;

import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NthChildPseudoClassTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    private void verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre(String selector, String... ids) {
        SeleniumQueryObject seleniumQueryObject = $(selector);
        assertThat(seleniumQueryObject.size(), is(ids.length));
        for (int i = 0; i < ids.length; i++) {
            WebElement webElement = seleniumQueryObject.get(i);
            String expectedId = ids[i];
            assertThat(webElement.getAttribute("id"), is(expectedId));
        }
    }

    @Test
    public void nth_child__2n_b() {
        verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre("div:nth-child(2n+1)", "d1", "d3", "d5");
        verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre("div:nth-child(2n+2)", "d2", "d4", "d6");
    }
    @Test
    public void nth_child__n_2() {
        verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre("div:nth-child(n+2)", "d2", "d3", "d4", "d5", "d6");
    }
    @Test
    public void nth_child__1n_0() {
        verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre("div:nth-child(1n+0)", "d1", "d2", "d3", "d4", "d5", "d6");
    }
    @Test
    public void nth_child__odd() {
        verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre("div:nth-child(odd)", "d1", "d3", "d5");
    }
    @Test
    public void nth_child__2n() {
        verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre("div:nth-child(2n)", "d2", "d4", "d6");
    }
    @Test
    public void nth_child__even() {
        verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre("div:nth-child(even)", "d2", "d4", "d6");
    }
    @Test
    public void nth_child__0n_1() {
        verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre("div:nth-child(0n+1)", "d1");
    }
    @Test
    public void nth_child__1() {
        verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre("div:nth-child(1)", "d1");
    }
    @Test
    public void nth_child__MINUS_n_3() {
        verifySelectorGeneratesSeleniumQueryObjectWithElementsWhoseIdsAre("div:nth-child(-n+3)", "d1", "d2", "d3");
    }

}