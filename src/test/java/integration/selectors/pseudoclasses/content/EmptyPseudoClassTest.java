package integration.selectors.pseudoclasses.content;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.selector.DriverSupportService.isHtmlUnitDriverEmulatingIE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownGivenDriver;

import org.junit.Rule;
import org.junit.Test;

public class EmptyPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownGivenDriver setUpAndTearDownGivenDriverRule = new SetUpAndTearDownGivenDriver(getClass());
	
	// http://jsbin.com/fuzuj/1/edit
	@Test
	public void emptyPseudoClass() {
		assertThat($("#d1").is(":empty"), is(false));
		assertThat($("#d2").is(":empty"), is(true));
		
		if (isHtmlUnitDriverEmulatingIE($.browser.getDefaultDriver())) {
			assertThat($("#d3").is(":empty"), is(true));
			assertThat($("#d4").is(":empty"), is(true));
		} else {
			assertThat($("#d3").is(":empty"), is(false));
			assertThat($("#d4").is(":empty"), is(false));
		}
		assertThat($("#d5").is(":empty"), is(false));

		assertThat($("#d10").is(":empty"), is(false));
		assertThat($("#d11").is(":empty"), is(true));
		assertThat($("#d12").is(":empty"), is(false));
		assertThat($("#d13").is(":empty"), is(true));
		
		if (isHtmlUnitDriverEmulatingIE($.browser.getDefaultDriver())) {
			assertThat($("#d14").is(":empty"), is(true));
		} else {
			assertThat($("#d14").is(":empty"), is(false));
		}
	}
	
}