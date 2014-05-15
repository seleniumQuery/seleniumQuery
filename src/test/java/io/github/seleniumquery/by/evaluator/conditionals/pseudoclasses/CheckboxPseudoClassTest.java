package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class CheckboxPseudoClassTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
	@Test
	public void checkboxPseudo() {
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