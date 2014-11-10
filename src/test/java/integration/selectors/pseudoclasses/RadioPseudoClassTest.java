package integration.selectors.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class RadioPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());
	
	@Test
	public void radioPseudoClass() {
		assertThat($("[type='radio']").size(), is(4));
		assertThat($(":radio").size(), is(1));
		assertThat($("*:radio").size(), is(1));
		assertThat($("input:radio").size(), is(1));
		assertThat($("div:radio").size(), is(0));
		assertThat($("span:radio").size(), is(0));

		assertThat($("#i1").is(":radio"), is(true));
		assertThat($("#i1").is("*:radio"), is(true));
		assertThat($("#i1").is("input:radio"), is(true));

		assertThat($("#i2").is(":radio"), is(false));
		assertThat($("#i3").is(":radio"), is(false));
		assertThat($("#i4").is(":radio"), is(false));
	}
	
}