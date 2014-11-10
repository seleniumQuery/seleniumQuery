package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class HasPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());
	
	@Test
	public void hasPseudoClass() {
		assertThat($("div").size(), is(4));
		assertThat($("div:has(p)").size(), is(1));
		assertThat($("div:contains(qwea)").size(), is(1));
		assertThat($("div:has(:contains('a\"b'))").size(), is(1));
		assertThat($("div:has(h1)").size(), is(1));
		assertThat($("div:has(h2)").size(), is(0));
	}
	
}