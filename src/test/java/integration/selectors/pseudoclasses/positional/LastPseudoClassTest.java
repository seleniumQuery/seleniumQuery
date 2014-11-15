package integration.selectors.pseudoclasses.positional;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class LastPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(EqPseudoClassTest.class);
	
	@Test
	public void lastPseudoClass() {
		assertThat($("div:last").text(), is("Bozo"));
		assertThat($("div.c2:last").text(), is("Spider Man"));
		
		assertThat($("div:last.c2").text(), is(""));
		assertThat($("hr:last").text(), is(""));
	}
	
}