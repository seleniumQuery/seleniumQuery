package integration.selectors.pseudoclasses.form;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.by.selector.DriverVersionUtils.isHtmlUnitDriverEmulatingIE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ButtonPseudoClassTest {
	
	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(ButtonPseudoClassTest.class);
	
	// http://jsbin.com/yacerelo/1/edit
	@Test
	public void buttonPseudoClass_selector() {
		assertThat($("[type='button']").size(), is(4));
		assertThat($(":button").size(), is(5));
		assertThat($("*:button").size(), is(5));
		assertThat($("input:button").size(), is(1));
		assertThat($("div:button").size(), is(0));
		assertThat($("span:button").size(), is(0));
	}
	
	@Test
	public void buttonPseudoClass_is() {
		assertThat($("#i1").is(":button"), is(true));
		assertThat($("#i1").is("*:button"), is(true));
		assertThat($("#i1").is("input:button"), is(true));

		assertThat($("#i2").is(":button"), is(false));
		assertThat($("#i3").is(":button"), is(false));
		assertThat($("#i4").is(":button"), is(false));

		assertThat($("#b1").is(":button"), is(true));

		// #Cross-Driver
		// Not a workaround, just documenting a difference.
		// Latest HtmlUnit, when emulating IE 8 abd 9, considers <button></button> to be <button type="button"></button>
		// not really a problem here, as it doesn't affect :button's behavior.
		// :submit, though, is affected. Check its implementation if you're curious about how it solves this
		// problem (and, yes, it is nasty, it uses reflection and stuff).
		if (isHtmlUnitDriverEmulatingIE($.browser.getDefaultDriver())) {
			assertThat($("#b1").is("[type='button']"), is(true));
		} else {
			assertThat($("#b1").is("[type='button']"), is(false));
		}
		// #failure above, inside if, probbly HU IE 11

		assertThat($("#b2").is(":button"), is(true));
		assertThat($("#b2").is("[type='button']"), is(true));
	}

}