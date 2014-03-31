package org.openqa.selenium.seleniumquery.example;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.seleniumquery.TestInfrastructure;

public class AllFunctionsTest {

	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.location.href(TestInfrastructure.getHtmlTestFileUrl(AllFunctionsTest.class));
	}
	
	@Test
	public void isPresent() {
		assertEquals("!visibleDiv!",		 $(".visibleDiv").waitUntil.isPresent().text()				);
	}
	
	@Test
	public void isNotPresent() {
		assertEquals(null,					 $(".nonExistingDiv").waitUntil.isNotPresent().text()		);
	}
	
	@Test
	public void isVisible() {
		assertEquals("!visibleDiv!",		 $(".visibleDiv").waitUntil.isVisible().text()				);
	}
	
	@Test
	public void isNotVisible() {
		$(".invisibleDiv").waitUntil.isNotVisible();
		//assertEquals("!invisibleDiv!",	 $(".invisibleDiv").waitUntil.isNotVisible().text()			);
	}
	
	@Test
	public void isVisibleAndEnabled() {
		assertEquals("!enabledInput!",		 $(".enabledInput").waitUntil.isVisibleAndEnabled().val()	);
	}
	
	@Test
	public void containsText() {
		assertEquals("!visibleDiv!",		 $(".visibleDiv").waitUntil.containsText("isibleDi").val()	);
	}
	
	public void queryUntil() {
		$(".myDivs").queryUntil.allAreNotPresent();
		$(".myDivs").queryUntil.allAreNotVisible();
		$(".myDivs").queryUntil.atLeastOneIsPresent();
		$(".myDivs").queryUntil.atLeastOneIsVisible();
		$(".myDivs").queryUntil.atLeastOneIsVisibleAndEnabled();
		$(".enabledInputs").queryUntil.elementsValuesAre("John");
		$(".enabledInputs").queryUntil.elementsValuesAreNot("Smith");
	}

}