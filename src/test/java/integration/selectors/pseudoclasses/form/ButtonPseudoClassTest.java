package integration.selectors.pseudoclasses.form;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class ButtonPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());
	
	// http://jsbin.com/yacerelo/1/edit
	@Test
	public void buttonPseudoClass_selector() {
		assertThat($("[type='button']").size(), is(4));
		assertThat($(":button").size(), is(5));
		assertThat($("*:button").size(), is(5));
		assertThat($("input:button").size(), is(1));
		assertThat($("div:button").size(), is(0));
		assertThat($("span:button").size(), is(0));
	}
	
	@Test
	public void buttonPseudoClass_is() {
		assertThat($("#i1").is(":button"), is(true));
		assertThat($("#i1").is("*:button"), is(true));
		assertThat($("#i1").is("input:button"), is(true));

		assertThat($("#i2").is(":button"), is(false));
		assertThat($("#i3").is(":button"), is(false));
		assertThat($("#i4").is(":button"), is(false));

		assertThat($("#b1").is(":button"), is(true));
		assertThat($("#b1").is("[type='button']"), is(false));
		assertThat($("#b2").is(":button"), is(true));
		assertThat($("#b2").is("[type='button']"), is(true));
	}
	
}