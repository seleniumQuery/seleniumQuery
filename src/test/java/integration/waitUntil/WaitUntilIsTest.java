package integration.waitUntil;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;
import io.github.seleniumquery.example.AllFunctionsTest;

import org.junit.Rule;
import org.junit.Test;

public class WaitUntilIsTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(AllFunctionsTest.class);

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