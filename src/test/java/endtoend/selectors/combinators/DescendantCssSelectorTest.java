package endtoend.selectors.combinators;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DescendantCssSelectorTest {

	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(DescendantCssSelectorTest.class);
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

    @Test
    public void visiblePseudoClass() throws Exception {
		// negative
    	assertThat($("div").size(), is(3));
    	assertThat($("span").size(), is(2));

		// the test:
    	assertThat($("div span").size(), is(1));
    	assertThat($("div span").get(0).getAttribute("id"), is("s2"));
    }

}