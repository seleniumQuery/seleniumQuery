package integration.selectors.attributes;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class IdAttributeCssSelectorTest {

	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());

    @Test
    public void visiblePseudoClass() throws Exception {
    	assertThat($("#myId").size(), is(1));
    	assertThat($("#myId").text(), is("abc"));
    }

}