package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;
import io.github.seleniumquery.selectors.pseudoclasses.UnsupportedXPathPseudoClassException;

import org.junit.Rule;
import org.junit.Test;

public class VisibleSelectorTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());

	@Test(expected = UnsupportedXPathPseudoClassException.class)
    public void visiblePseudoClass() throws Exception {
    	assertThat($(":visible").size(), is(99999));
    }
	
    @Test
    public void is_visiblePseudoClass() throws Exception {
    	assertThat($("#visibleDiv").is(":visible"), is(true));
    	assertThat($("#visibleDiv2").is(":visible"), is(true));
    	assertThat($("#invisibleDiv").is(":visible"), is(false));
    	
    	assertThat($("#invisibleParentDiv").is(":visible"), is(false));
    	assertThat($("#almostVisibleDiv").is(":visible"), is(false));
    }
    
}