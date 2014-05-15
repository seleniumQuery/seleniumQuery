package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class ImagePseudoClassTest {

	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}

	@Test
	public void imagePseudo() {
		assertThat($("[type='image']").size(), is(4));
		assertThat($(":image").size(), is(1));
		assertThat($("*:image").size(), is(1));
		assertThat($("input:image").size(), is(1));
		assertThat($("div:image").size(), is(0));
		assertThat($("span:image").size(), is(0));

		assertThat($("#i1").is(":image"), is(true));
		assertThat($("#i1").is("*:image"), is(true));
		assertThat($("#i1").is("input:image"), is(true));

		assertThat($("#i2").is(":image"), is(false));
		assertThat($("#i3").is(":image"), is(false));
		assertThat($("#i4").is(":image"), is(false));
	}

}