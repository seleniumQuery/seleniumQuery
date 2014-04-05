package org.openqa.selenium.seleniumquery.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryWaitException;

public class AllFunctionsTest {

	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.location.href(TestInfrastructure.getHtmlTestFileUrl(AllFunctionsTest.class));
	}
	
	@Test
	public void isPresent() {
		assertEquals("!visibleDiv!", $(".visibleDiv").waitUntil().is().present().text());
	}

	@Test
	public void isNotPresent() {
		assertNull($(".nonExistingDiv").waitUntil().is().not().present().text());
	}

	@Test
	public void isVisible() {
		assertEquals("!visibleDiv!", $(".visibleDiv").waitUntil().is().visible().text());
	}

	@Test
	public void isNotVisible() {
		assertEquals("", $(".invisibleDiv").waitUntil().is().not().visible().text());
	}

	@Test
	public void isVisibleAndEnabled() {
		assertEquals("!enabledInput!", $(".enabledInput").waitUntil().is().visibleAndEnabled().val());
	}

	@Test
	public void containsText() {
		assertEquals("!visibleDiv!", $(".visibleDiv").waitUntil().has().textContaining("isibleDix").val());
	}
	
	@Test(expected=SeleniumQueryWaitException.class)
	public void waitUntil_has_textContaininig__should_throw_an_exception_after_waiting_for_div_without_the_desired_text() {
		$(".visibleDiv").waitUntil().has().textContaining("CRAZY TEXT THAT IT DOES NOT CONTAIN");
	}
	
	public void queryUntil() {
		$(".myDivs").queryUntil().atLeastOneElement().is().present();
		$(".myDivs").queryUntil().atLeastOneElement().is().enabled();
		$(".myDivs").queryUntil().atLeastOneElement().is().visible();
		$(".myDivs").queryUntil().atLeastOneElement().is().visibleAndEnabled();
		$(".myDivs").queryUntil().atLeastOneElement().has().val("");
		
		$(".myDivs").queryUntil().atLeast(5).have().textContaining("yo!");

		$(".myDivs").queryUntil().atLeastOneElement().is().not().visible();
		$(".myDivs").queryUntil().atLeastOneElement().is().not().visible();
		
		$(".myDivs").queryUntil().everyElement().is().not().visible();
		$(".myDivs").queryUntil().noElement().is().not().visible();
		$(".myDivs").queryUntil().atLeast(7).is().not().visible();
		$(".myDivs").queryUntil().atLeast(7).are().not().visible();
		$(".myDivs").queryUntil().atMost(7).is().not().visible();
		$(".myDivs").queryUntil().exactlyOneElement().is().not().visible();
		$(".myDivs").queryUntil().exactly(7).is().not().visible();
		$(".myDivs").queryUntil().atLeastOneElement().is().not().visible();
		$(".myDivs").queryUntil().everyElement().is().not().present();
		$(".myDivs").queryUntil().noElement().is().visible();
		$(".myDivs").queryUntil().atLeastOneElement().is().present();
		$(".myDivs").queryUntil().atLeastOneElement().is().visible();
		$(".myDivs").queryUntil().atLeastOneElement().is().visibleAndEnabled();
		$(".enabledInputs").queryUntil().has().val("John");
		$(".enabledInputs").queryUntil().has().not().val("Smith");
	}

}