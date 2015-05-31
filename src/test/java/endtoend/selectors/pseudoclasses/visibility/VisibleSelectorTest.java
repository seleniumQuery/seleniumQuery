package endtoend.selectors.pseudoclasses.visibility;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.by.css.pseudoclasses.UnsupportedXPathPseudoClassException;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class VisibleSelectorTest {
	
	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

	@Test(expected = UnsupportedXPathPseudoClassException.class)
    public void visiblePseudoClass() throws Exception {
    	assertThat($(":visible").size(), is(99999));
    }

	@Test
	public void is_visiblePseudoClass() {
		assertThat($("#visibleDiv").is(":visible"), is(true));
		assertThat($("#visibleDiv2").is(":visible"), is(true));
		assertThat($("#invisibleDiv").is(":visible"), is(false));
    	assertThat($("#invisibleParentDiv").is(":visible"), is(false));
    	assertThat($("#almostVisibleDiv").is(":visible"), is(false));
    }

}