package integration.selectors.pseudoclasses.positional;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class EqBugPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());
	
	@Test
	public void eqPseudo_with_descendant() {
		assertThat($("//*[@id='areaCentral']/table/tbody/tr[1]/td[2]/a").size(), is(1));
//		$.browser.sleep(600*1000);
//		assertThat($(".rich-table tr:nth-child(1) a").size(), is(1));
		assertThat($(".rich-table a:eq(0)").size(), is(1));
	}
	
}