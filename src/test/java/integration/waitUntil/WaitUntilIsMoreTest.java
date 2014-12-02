package integration.waitUntil;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.wait.SeleniumQueryTimeoutException;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;

public class WaitUntilIsMoreTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

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
	public void waitUntil_has_textContaining__should_throw_an_exception_after_waiting_for_div_without_the_desired_text() {
		$(".visibleDiv").waitUntil(1200).text().contains("CRAZY TEXT THAT IT DOES NOT CONTAIN");
	}

}