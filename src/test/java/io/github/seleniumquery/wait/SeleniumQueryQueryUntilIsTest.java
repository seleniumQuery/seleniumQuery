package io.github.seleniumquery.wait;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Before;
import org.junit.Test;
import io.github.seleniumquery.TestInfrastructure;
import io.github.seleniumquery.example.AllFunctionsTest;

public class SeleniumQueryQueryUntilIsTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.location.href(TestInfrastructure.getHtmlTestFileUrl(AllFunctionsTest.class));
	}

	@Test
	public void queryUntil_not_enabled() {
		assertThat($("input.disabledInput").queryUntil().is(":not(:enabled)").then().size(), is(1));
	}
	
	@Test
	public void queryUntil_disabled() {
		assertThat($("input.disabledInput").queryUntil().is(":disabled").then().size(), is(1));
	}

	@Test
	public void queryUntil_visible() {
		assertThat($("input.enabledInput").queryUntil().is(":visible").then().size(), is(1));
	}

	@Test
	public void queryUntil_enabled() {
		assertThat($("input.enabledInput").queryUntil().is(":enabled").then().size(), is(1));
	}

	@Test
	public void queryUntil_present() {
		assertThat($("input.enabledInput").queryUntil().is(":present").then().size(), is(1));
	}

	@Test
	public void queryUntil_visible_enabled() {
		assertThat($("input.enabledInput").queryUntil().is(":visible:enabled").then().size(), is(1));
	}
	
	@Test
	public void queryUntil_hidden() {
		assertThat($("div.invisibleDiv").queryUntil().is(":hidden").then().size(), is(1));
	}

}