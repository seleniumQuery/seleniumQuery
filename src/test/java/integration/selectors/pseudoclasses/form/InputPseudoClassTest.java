package integration.selectors.pseudoclasses.form;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class InputPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
	// http://jsbin.com/nuhefeqi/1/edit
	@Test
	public void inputPseudoClass() {
		assertThat($("[type='input']").size(), is(2));
		assertThat($(":input").size(), is(82));
		assertThat($("*:input").size(), is(82));

		assertThat($("input:input").size(), is(73));
		assertThat($("button:input").size(), is(3));
		assertThat($("select:input").size(), is(3));
		assertThat($("textarea:input").size(), is(3));
		assertThat($("option:input").size(), is(0));
		assertThat($("optgroup:input").size(), is(0));

		assertThat($("div:input").size(), is(0));
		assertThat($("span:input").size(), is(0));

		assertThat($("#i1").is(":input"), is(true));
		assertThat($("#i1").is("*:input"), is(true));
		assertThat($("#i1").is("input:input"), is(true));

		assertThat($("#i3").is(":input"), is(false));
		assertThat($("#i4").is(":input"), is(false));

		assertThat($("#b1").is(":input"), is(true));
	}
	
}