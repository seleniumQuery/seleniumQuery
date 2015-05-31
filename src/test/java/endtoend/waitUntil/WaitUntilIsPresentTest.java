package endtoend.waitUntil;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import testinfrastructure.junitrule.JavaScriptOnly;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class WaitUntilIsPresentTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;
	
	@Test @JavaScriptOnly
	public void isPresent__should_not_throw_an_exception_when_the_element_becomes_present__and__return_the_elements_val() throws Exception {
		// given
		// when
		$("div.clickable").click();
		// then
		assertEquals(0, $("input.ball").size());
		assertEquals("generated input starting value", $("input.ball").waitUntil().is(":present").then().val());
	}
	
	@Test
	public void queryUntil_not_present() {
		$(".whatever").waitUntil().is(":not(:present)");
	}
	
	@Test
	public void queryUntil_enabled_not_present() {
		$(".whatever").waitUntil().is(":enabled:not(:present)");
	}
	
	@Test
	public void queryUntil_not_present_enabled() {
		$(".whatever").waitUntil().is(":not(:present):enabled");
	}
	
	@Test
	public void queryUntil_not_not_not_present_with_others() {
		$(".whatever").waitUntil().is(".xyz:enabled:not(:not(:not(:present))):disabled");
	}
	
	@Test
	public void is_not_not_not_present() {
		assertThat($(".whatever").is(".xyz:enabled:disabled"), is(false));
		assertThat($(".whatever").is(".xyz:enabled:not(:not(:not(:present))):disabled"), is(true));
	}

}