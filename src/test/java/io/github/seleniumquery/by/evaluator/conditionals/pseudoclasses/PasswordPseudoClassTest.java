package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class PasswordPseudoClassTest {

	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

	@Test
	public void passwordPseudo() {
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