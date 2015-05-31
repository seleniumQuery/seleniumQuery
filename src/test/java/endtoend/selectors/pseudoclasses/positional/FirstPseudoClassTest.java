package endtoend.selectors.pseudoclasses.positional;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class FirstPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(EqPseudoClassTest.class);
	
	@Test
	public void firstPseudoClass() {
		assertThat($("div:first").text(), is("Batman"));
		assertThat($("div.c2:first").text(), is("Spider Man"));
		
		assertThat($("div:first.c2").text(), is(""));
		assertThat($("hr:first").text(), is(""));
    }

}