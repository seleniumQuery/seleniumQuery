package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class SubmitPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	// http://jsbin.com/mopireya/6/edit
	@Test
	public void submitPseudoClass() {
		assertThat($("[type='submit']").size(), is(6));
		assertThat($(":submit").size(), is(4));
		assertThat($("*:submit").size(), is(4));
		assertThat($("input:submit").size(), is(2));
		assertThat($("div:submit").size(), is(0));
		assertThat($("span:submit").size(), is(0));

		assertThat($("#i1").is(":submit"), is(true));
		assertThat($("#i1").is("*:submit"), is(true));
		assertThat($("#i1").is("input:submit"), is(true));

		assertThat($("#i2").is(":submit"), is(false));
		assertThat($("#i3").is(":submit"), is(false));
		assertThat($("#i4").is(":submit"), is(false));

		assertThat($("#b1").is(":submit"), is(true));
		
		// #Cross-Driver (not a workaround, just documenting a difference)
		// the line below depends on the browser
		// firefox (only when through selenium!!!) considers that element's attribute type is
		// actually submit, even though it is empty.
		// via usual firefox this does not happen! Curious!
		// we can let this pass (so we comment it) because this uncertainty is
		// stated in the docs!
//		assertThat($("#b1").is("[type='submit']"), is(false));
		
		
		assertThat($("#b2").is(":submit"), is(true));
		assertThat($("#b2").is("[type='submit']"), is(true));
		
		assertThat($(".clz:submit").size(), is(1));
		assertThat($("#i1c").is(".clz:submit"), is(true));
	}
	
}