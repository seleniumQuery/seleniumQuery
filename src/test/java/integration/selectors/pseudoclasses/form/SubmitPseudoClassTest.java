package integration.selectors.pseudoclasses.form;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.by.selector.DriverVersionUtils.isHtmlUnitDriverEmulatingIEBelow11;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SubmitPseudoClassTest {
	
	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SubmitPseudoClassTest.class);

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

		// #Cross-Driver
		// Not a workaround, just documenting a difference.
		// Latest HtmlUnit, when emulating IE 8 abd 9, considers <button></button> to be <button type="button"></button>
		// :submit and this example are affected. Check :submit implementation if you're curious about how it solves this
		// problem (and, yes, it is nasty, it uses reflection and stuff).
		if (isHtmlUnitDriverEmulatingIEBelow11($.browser.getDefaultDriver())) {
			assertThat($("#b1").is("[type='submit']"), is(false));
		} else {
			assertThat($("#b1").is("[type='submit']"), is(true));
		}

		assertThat($("#b2").is(":submit"), is(true));
		assertThat($("#b2").is("[type='submit']"), is(true));
		
		assertThat($(".clz:submit").size(), is(1));
		assertThat($("#i1c").is(".clz:submit"), is(true));
	}

}