package integration.selectors.attributes;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class IdAttributeCssSelectorTest {

	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());

    @Test
    public void visiblePseudoClass() throws Exception {
    	assertThat($("#myId").size(), is(1));
    	assertThat($("#myId").text(), is("abc"));
    }

}