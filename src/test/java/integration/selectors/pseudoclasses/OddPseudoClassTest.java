package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class OddPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());
	
	// http://jsbin.com/vinip/1/edit
	@Test
	public void evenPseudoClass() {
		assertThat($("#d1").is(":odd"), is(false));
		assertThat($("#d2").is(":odd"), is(true));
		assertThat($("#d3").is(":odd"), is(false));
		assertThat($("#d4").is(":odd"), is(true));
		assertThat($("#d5").is(":odd"), is(false));
		assertThat($("#d6").is(":odd"), is(true));
		assertThat($("#d7").is(":odd"), is(false));
		assertThat($("#d8").is(":odd"), is(true));

		assertThat($("#d1").is(".one-amount:odd"), is(false));
		assertThat($("#d2").is(".one-amount:odd"), is(false));
		assertThat($("#d3").is(".one-amount:odd"), is(false));
		assertThat($("#d4").is(".one-amount:odd"), is(false));
		assertThat($("#d5").is(".one-amount:odd"), is(false));
		assertThat($("#d6").is(".one-amount:odd"), is(false));
		assertThat($("#d7").is(".one-amount:odd"), is(false));
		assertThat($("#d8").is(".one-amount:odd"), is(false));

		assertThat($("#d1").is(".even-amount:odd"), is(false));
		assertThat($("#d2").is(".even-amount:odd"), is(false));
		assertThat($("#d3").is(".even-amount:odd"), is(true));
		assertThat($("#d4").is(".even-amount:odd"), is(false));
		assertThat($("#d5").is(".even-amount:odd"), is(true));
		assertThat($("#d6").is(".even-amount:odd"), is(false));
		assertThat($("#d7").is(".even-amount:odd"), is(false));
		assertThat($("#d8").is(".even-amount:odd"), is(false));

		assertThat($("#d1").is(".odd-amount:odd"), is(false));
		assertThat($("#d2").is(".odd-amount:odd"), is(false));
		assertThat($("#d3").is(".odd-amount:odd"), is(false));
		assertThat($("#d4").is(".odd-amount:odd"), is(false));
		assertThat($("#d5").is(".odd-amount:odd"), is(false));
		assertThat($("#d6").is(".odd-amount:odd"), is(false));
		assertThat($("#d7").is(".odd-amount:odd"), is(true));
		assertThat($("#d8").is(".odd-amount:odd"), is(false));

		assertThat($("div:odd").size(), is(4));
		assertThat($(".one-amount:odd").size(), is(0));
		assertThat($(".even-amount:odd").size(), is(2));
		assertThat($(".odd-amount:odd").size(), is(1));
		assertThat($(".empty-amount:odd").size(), is(0));
	}
	
}