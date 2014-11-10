package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class SelectedSelectorTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());

    @Test
    public void selectedPseudoClass() {
        assertThat($("option").size(), is(6));
        assertThat($("option:selected").size(), is(2));
        assertThat($("option:selected").get(0).getText(), is("Shrubs"));
    }

}