package org.openqa.selenium.seleniumquery.wait;

import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class SeleniumQueryElementWaitTest {

	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.location.href(TestInfrastructure.getHtmlTestFileUrl(SeleniumQueryElementWaitTest.class));
	}
	
	@Test
	public void not_eq__should_return_the_element_where_eq_is_applied_to_the_result_of_not() throws Exception {
		// given
		// when
		$("div.clickable").click();
		System.out.println($("input.ball").waitUntil.isPresent().val());
		// then
	}

}