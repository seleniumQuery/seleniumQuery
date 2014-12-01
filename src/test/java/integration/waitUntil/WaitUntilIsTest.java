package integration.waitUntil;

import infrastructure.junitrule.JavaScriptOnly;
import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.by.DriverVersionUtils.isHtmlUnitDriver;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WaitUntilIsTest {
	
	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(WaitUntilIsMoreTest.class);
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

	@Test
	public void queryUntil_not_enabled() {
		assertThat($("input.disabledInput").waitUntil().is(":not(:enabled)").then().size(), is(1));
	}
	
	@Test
	public void queryUntil_disabled() {
		assertThat($("input.disabledInput").waitUntil().is(":disabled").then().size(), is(1));
	}

	@Test
	public void queryUntil_visible() {
		assertThat($("input.enabledInput").waitUntil().is(":visible").then().size(), is(1));
	}

	@Test
	public void queryUntil_enabled() {
		assertThat($("input.enabledInput").waitUntil().is(":enabled").then().size(), is(1));
	}

	@Test
	public void queryUntil_present() {
		assertThat($("input.enabledInput").waitUntil().is(":present").then().size(), is(1));
	}

	@Test
	public void queryUntil_visible_enabled() {
		assertThat($("input.enabledInput").waitUntil().is(":visible:enabled").then().size(), is(1));
	}
	
	@Test @JavaScriptOnly
	public void queryUntil_hidden() {
		// if JS is ON, it works everywhere
		assertThat($("div.invisibleDiv").waitUntil().is(":hidden").then().size(), is(1));
	}

	@Test
	public void queryUntil_hidden_2() {
		// without JS only if it is not HtmlUnit
		if (!isHtmlUnitDriver($.driver().get())) {
			assertThat($("div.invisibleDiv").waitUntil().is(":hidden").then().size(), is(1));
		}
	}

}