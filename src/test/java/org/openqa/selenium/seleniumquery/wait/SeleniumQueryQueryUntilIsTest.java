package org.openqa.selenium.seleniumquery.wait;

import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;
import org.openqa.selenium.seleniumquery.example.AllFunctionsTest;

public class SeleniumQueryQueryUntilIsTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.location.href(TestInfrastructure.getHtmlTestFileUrl(AllFunctionsTest.class));
	}

	@Test
	public void testNot() {
		$("input.disabledInput").queryUntil().is().not().enabled();
	}

	@Test
	public void testVisible() {
		$("input.enabledInput").queryUntil().is().visible();
	}

	@Test
	public void testEnabled() {
		$("input.enabledInput").queryUntil().is().enabled();
	}

	@Test
	public void testPresent() {
		$("input.enabledInput").queryUntil().is().present();
	}

	@Test
	public void testVisibleAndEnabled() {
		$("input.enabledInput").queryUntil().is().visibleAndEnabled();
	}

}