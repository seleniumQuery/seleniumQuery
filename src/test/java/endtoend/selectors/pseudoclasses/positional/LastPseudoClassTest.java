package endtoend.selectors.pseudoclasses.positional;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class LastPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(EqPseudoClassTest.class);
	
	@Test
	public void lastPseudoClass() {
		assertThat($("div:last").text(), is("Bozo"));
		assertThat($("div.c2:last").text(), is("Spider Man"));
		
		assertThat($("div:last.c2").text(), is(""));
		assertThat($("hr:last").text(), is(""));
	}
	
}