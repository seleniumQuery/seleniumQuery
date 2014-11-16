package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class HeaderPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
	// http://jsbin.com/bemufole/1/edit
	@Test
	public void headerPseudoClass() {
		assertThat($(":header").size(), is(10));
		assertThat($("*:header").size(), is(10));

		assertThat($("h0:header").size(), is(1));
		assertThat($("h0").is(":header"), is(true));
		assertThat($("h1:header").size(), is(1));
		assertThat($("h1").is(":header"), is(true));
		assertThat($("h2:header").size(), is(1));
		assertThat($("h2").is(":header"), is(true));
		assertThat($("h3:header").size(), is(1));
		assertThat($("h3").is(":header"), is(true));
		assertThat($("h4:header").size(), is(1));
		assertThat($("h4").is(":header"), is(true));
		assertThat($("h5:header").size(), is(1));
		assertThat($("h5").is(":header"), is(true));
		assertThat($("h6:header").size(), is(1));
		assertThat($("h6").is(":header"), is(true));
		assertThat($("h7:header").size(), is(1));
		assertThat($("h7").is(":header"), is(true));
		assertThat($("h8:header").size(), is(1));
		assertThat($("h8").is(":header"), is(true));
		assertThat($("h9:header").size(), is(1));
		assertThat($("h9").is(":header"), is(true));
		assertThat($("h10:header").size(), is(0));
		assertThat($("h10").is(":header"), is(false));
	}
	
}