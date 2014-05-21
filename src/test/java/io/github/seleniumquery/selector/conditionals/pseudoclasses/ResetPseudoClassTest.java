package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class ResetPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	// http://jsbin.com/kecoruga/1/edit
	@Test
	public void resetPseudoClass() {
		assertThat($("[type='reset']").size(), is(5));
		assertThat($(":reset").size(), is(2));
		assertThat($("*:reset").size(), is(2));
		assertThat($("input:reset").size(), is(1));
		assertThat($("div:reset").size(), is(0));
		assertThat($("span:reset").size(), is(0));

		assertThat($("#i1").is(":reset"), is(true));
		assertThat($("#i1").is("*:reset"), is(true));
		assertThat($("#i1").is("input:reset"), is(true));

		assertThat($("#i2").is(":reset"), is(false));
		assertThat($("#i3").is(":reset"), is(false));
		assertThat($("#i4").is(":reset"), is(false));

		assertThat($("#b1").is(":reset"), is(false));
		assertThat($("#b1").is("[type='reset']"), is(false));
		assertThat($("#b2").is(":reset"), is(true));
		assertThat($("#b2").is("[type='reset']"), is(true));
	}
	
}