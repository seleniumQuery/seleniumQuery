package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class HiddenSelectorTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());

    @Test
    public void hiddenPseudoClass() throws Exception {
    	assertThat($("#visibleDiv").is(":hidden"), is(false));
    	assertThat($("#visibleDiv2").is(":hidden"), is(false));
    	assertThat($("#invisibleDiv").is(":hidden"), is(true));
    	
    	assertThat($("#invisibleParentDiv").is(":hidden"), is(true));
    	assertThat($("#almostVisibleDiv").is(":hidden"), is(true));
    }

}