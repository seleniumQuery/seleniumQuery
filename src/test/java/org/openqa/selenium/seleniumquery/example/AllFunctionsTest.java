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
		assertEquals("!visibleDiv!", $(".visibleDiv").waitUntil().has().textContaining("isibleDi").val());
	}
	
	@Test(expected=SeleniumQueryWaitException.class)
	public void waitUntil_has_textContaininig__should_throw_an_exception_after_waiting_for_div_without_the_desired_text() {
		$(".visibleDiv").waitUntil().has().textContaining("CRAZY TEXT THAT IT DOES NOT CONTAIN");
	}
	
	public void queryUntil() {
		// .queryUntil() will requery the DOM every time
		// .is() functions
		$(".aDivDiv").queryUntil().is().present();
		$(".myInput").queryUntil().is().enabled();
		$(".aDivDiv").queryUntil().is().visible();
		$(".myInput").queryUntil().is().visibleAndEnabled();
		// .has() functions
		$(".myInput").queryUntil().has().valEqualTo("expectedValue");
		$(".aDivDiv").queryUntil().has().textContaining("expectedText");
		// both .is() and .has() can use .not()
		$(".myInput").queryUntil().is().not().enabled();
		$(".myInput").queryUntil().has().not().valEqualTo("expectedValue");
		
		// .waitUntil() will work only on the already matched set, and have the exact same set of functions
		
		// .is() functions
		$(".aDivDiv").waitUntil().is().present();
		$(".myInput").waitUntil().is().enabled();
		$(".aDivDiv").waitUntil().is().visible();
		$(".myInput").waitUntil().is().visibleAndEnabled();
		// .has() functions
		$(".myInput").waitUntil().has().valEqualTo("expectedValue");
		$(".aDivDiv").waitUntil().has().textContaining("expectedText");
		// both .is() and .has() can use .not()
		$(".myInput").waitUntil().is().not().enabled();
		$(".myInput").waitUntil().has().not().valEqualTo("expectedValue");
		
		
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
		$(".enabledInputs").queryUntil().has().valEqualTo("John");
		$(".enabledInputs").queryUntil().has().not().valEqualTo("Smith");
	}

}