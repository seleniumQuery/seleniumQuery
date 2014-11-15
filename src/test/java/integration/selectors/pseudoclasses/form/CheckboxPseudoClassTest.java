package integration.selectors.pseudoclasses.form;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class CheckboxPseudoClassTest {
	
	@ClassRule
	public static SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(CheckboxPseudoClassTest.class);
	
	@Test
	public void checkboxPseudoClass() {
		assertThat($("[type='checkbox']").size(), is(4));
		assertThat($(":checkbox").size(), is(1));
		assertThat($("*:checkbox").size(), is(1));
		assertThat($("input:checkbox").size(), is(1));
		assertThat($("div:checkbox").size(), is(0));
		assertThat($("span:checkbox").size(), is(0));

		assertThat($("#i1").is(":checkbox"), is(true));
		assertThat($("#i1").is("*:checkbox"), is(true));
		assertThat($("#i1").is("input:checkbox"), is(true));

		assertThat($("#i2").is(":checkbox"), is(false));
		assertThat($("#i3").is(":checkbox"), is(false));
		assertThat($("#i4").is(":checkbox"), is(false));
	}
	
}