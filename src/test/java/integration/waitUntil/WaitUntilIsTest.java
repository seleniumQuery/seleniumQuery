package integration.waitUntil;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WaitUntilIsTest {
	
	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(WaitUntilIsMoreTest.class);

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
	
	@Test
	public void queryUntil_hidden() {
		assertThat($("div.invisibleDiv").waitUntil().is(":hidden").then().size(), is(1));
	}

}