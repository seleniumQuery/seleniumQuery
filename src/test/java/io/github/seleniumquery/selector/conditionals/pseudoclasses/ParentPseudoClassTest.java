package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class ParentPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	// http://jsbin.com/lutim/6/edit
	@Test
	public void parentPseudoClass() {
		assertThat($("#d1").is(":parent"), is(true));
		assertThat($("#d2").is(":parent"), is(false));
		assertThat($("#d3").is(":parent"), is(true));
		assertThat($("#d4").is(":parent"), is(true));
		assertThat($("#d5").is(":parent"), is(true));

		assertThat($("#d10").is(":parent"), is(true));
		assertThat($("#d11").is(":parent"), is(false));
		assertThat($("#d12").is(":parent"), is(true));
		assertThat($("#d13").is(":parent"), is(false));
		assertThat($("#d14").is(":parent"), is(true));
	}
	
}