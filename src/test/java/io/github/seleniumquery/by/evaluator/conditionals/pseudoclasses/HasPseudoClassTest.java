package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class HasPseudoClassTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
	@Test
	public void hasPseudoClass() {
		assertThat($("div").size(), is(4));
		assertThat($("div:has(p)").size(), is(1));
		assertThat($("div:contains(qwea)").size(), is(1));
		assertThat($("div:has(:contains('a\"b'))").size(), is(1));
		assertThat($("div:has(h1)").size(), is(1));
		assertThat($("div:has(h2)").size(), is(0));
	}
	
}