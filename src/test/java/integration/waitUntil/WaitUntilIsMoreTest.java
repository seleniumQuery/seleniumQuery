package integration.waitUntil;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.wait.SeleniumQueryTimeoutException;
import org.junit.ClassRule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;

public class WaitUntilIsMoreTest {

	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(WaitUntilIsMoreTest.class);
	
	@Test
	public void isPresent() {
		assertEquals("!visibleDiv!", $(".visibleDiv").waitUntil().is(":present").then().text());
	}

	@Test
	public void isNotPresent() {
		assertEquals("", $(".nonExistingDiv").waitUntil().is(":not(:present)").then().text());
	}

	@Test
	public void isVisible() {
		assertEquals("!visibleDiv!", $(".visibleDiv").waitUntil().is(":visible").then().text());
	}

	@Test
	public void isNotVisible() {
		assertEquals("", $(".invisibleDiv").waitUntil().is(":not(:visible)").then().text());
	}

	@Test
	public void isVisibleAndEnabled() {
		assertEquals("!enabledInput!", $(".enabledInput").waitUntil().is(":visible:enabled").then().val());
	}

	@Test
	public void containsText() {
		assertEquals("!visibleDiv!", $(".visibleDiv").waitUntil().text().contains("isibleDi").then().text());
	}
	
	@Test(expected=SeleniumQueryTimeoutException.class)
	public void waitUntil_has_textContaininig__should_throw_an_exception_after_waiting_for_div_without_the_desired_text() {
		$(".visibleDiv").waitUntil().text().contains("CRAZY TEXT THAT IT DOES NOT CONTAIN");
	}
	
	public void queryUntil() {
		
		
		// .queryUntil() will requery the DOM every time
		// you can use .is()
		$(".aDivDiv").waitUntil().is(":present").then().click();
		$(".myInput").waitUntil().is(":enabled");
		$(".aDivDiv").waitUntil().is(":visible");
		$(".myInput").waitUntil().is(":visible:enabled");
		// .val()
		$(".myInput").waitUntil().val().isEqualTo("expectedValue");
		// text()
		$(".aDivDiv").waitUntil().text().contains("expectedText");
		// both .is() and .has() can use .not()
		$(".myInput").waitUntil().is(":not(:enabled)");
//		$(".myInput").queryUntil().val().not().isEqualTo("expectedValue");
		
		// .waitUntil() will work only on the already matched set, and have the exact same set of functions
		
		$(".myDivs").waitUntil().is(":present").and().size().isGreaterThan(7).then().click();
		
		// .is() functions
		$(".aDivDiv").waitUntil().is(":present");
		$(".myInput").waitUntil().is(":enabled");
		$(".aDivDiv").waitUntil().is(":visible");
		$(".myInput").waitUntil().is(":visible:enabled");
		// .has() functions
		$(".myInput").waitUntil().val().isEqualTo("expectedValue");
//		$(".aDivDiv").waitUntil().has().textContaining("expectedText");
//		// both .is() and .has() can use .not()
//		$(".myInput").waitUntil().is().not().enabled();
//		$(".myInput").waitUntil().has().not().valEqualTo("expectedValue");
		
	}

}