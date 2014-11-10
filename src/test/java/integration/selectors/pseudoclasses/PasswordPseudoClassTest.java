package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class PasswordPseudoClassTest {

	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());

	@Test
	public void passwordPseudoClass() {
		assertThat($("[type='password']").size(), is(4));
		assertThat($(":password").size(), is(1));
		assertThat($("*:password").size(), is(1));
		assertThat($("input:password").size(), is(1));
		assertThat($("div:password").size(), is(0));
		assertThat($("span:password").size(), is(0));

		assertThat($("#i1").is(":password"), is(true));
		assertThat($("#i1").is("*:password"), is(true));
		assertThat($("#i1").is("input:password"), is(true));

		assertThat($("#i2").is(":password"), is(false));
		assertThat($("#i3").is(":password"), is(false));
		assertThat($("#i4").is(":password"), is(false));
	}

}