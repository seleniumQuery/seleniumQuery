package io.github.seleniumquery.wait.queryuntil;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class IsPresentTest {

	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test
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

}