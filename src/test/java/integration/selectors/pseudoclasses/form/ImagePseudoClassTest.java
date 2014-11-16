package integration.selectors.pseudoclasses.form;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class ImagePseudoClassTest {

	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());

	@Test
	public void imagePseudoClass() {
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