package integration.selectors.pseudoclasses.form;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class TextPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
	// http://jsbin.com/vabes/1/edit
	@Test
	public void textPseudoClass() {
		assertThat($("#i1").is(":text"), is(true));
		assertThat($("#d1").is(":text"), is(false));
		assertThat($("#i2").is(":text"), is(false));
		assertThat($("#i3").is(":text"), is(true));
		assertThat($("#xyz").is(":text"), is(false));
	}
	
}