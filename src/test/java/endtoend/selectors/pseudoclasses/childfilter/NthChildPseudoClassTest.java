package endtoend.selectors.pseudoclasses.childfilter;

import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NthChildPseudoClassTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void nth_child() {
        SeleniumQueryObject divs = $("div:nth-child(2n+2)");
        assertThat(divs.size(), is(3));
        assertThat(divs.get(0).getAttribute("id"), is("d2"));
        assertThat(divs.get(1).getAttribute("id"), is("d4"));
        assertThat(divs.get(2).getAttribute("id"), is("d6"));
    }

}