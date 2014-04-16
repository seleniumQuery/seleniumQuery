package io.github.seleniumquery.wait.queryuntil;

import static org.junit.Assert.assertEquals;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;

public class IsPresentTest {

	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(IsPresentTest.class));
	}
	
	@Test
	public void isPresent__should_not_throw_an_exception_when_the_element_becomes_present__and__return_the_elements_val() throws Exception {
		// given
		// when
		$("div.clickable").click();
		// then
		assertEquals(0, $("input.ball").size());
		assertEquals("generated input starting value", $("input.ball").queryUntil().is(":present").then().val());
	}
	
	@Test
	public void queryUntil_not_present() {
		$(".whatever").queryUntil().is(":not(:present)");
	}
	
	@Test
	public void queryUntil_enabled_not_present() {
		$(".whatever").queryUntil().is(":enabled:not(:present)");
	}
	
	@Test
	public void queryUntil_not_present_enabled() {
		$(".whatever").queryUntil().is(":not(:present):enabled");
	}

}