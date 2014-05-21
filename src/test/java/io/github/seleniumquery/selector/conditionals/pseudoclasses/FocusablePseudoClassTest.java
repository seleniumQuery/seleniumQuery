package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class FocusablePseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test
	public void focusablePseudoClass() {
		assertThat($("body > *").size(), is(6));
		assertThat($(":focusable").size(), is(3));
		
		assertThat($("input:focusable").size(), is(1));
		assertThat($("a:focusable").size(), is(1));
		assertThat($("p:focusable").size(), is(1));
		
		assertThat($("#i1").is(":focusable"), is(false));
		assertThat($("#i2").is(":focusable"), is(true));
		assertThat($("#a1").is(":focusable"), is(false));
		assertThat($("#a2").is(":focusable"), is(true));
		assertThat($("#p1").is(":focusable"), is(false));
		assertThat($("#p2").is(":focusable"), is(true));
	}
	
}