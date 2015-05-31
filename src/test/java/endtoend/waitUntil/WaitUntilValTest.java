package endtoend.waitUntil;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.wait.SeleniumQueryTimeoutException;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WaitUntilValTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

	@Before
	public void setUp() throws Exception {
		$("#i1").val("abc");
		$("#i2").val("abcdef");
	}

	@Test
	public void waitUntil_val() {
		assertThat($("#i1").waitUntil().val().isEqualTo("abc").then().size(), is(1));
		assertThat($("#i2").waitUntil().val().isEqualTo("abcdef").then().size(), is(1));
		assertThat($("#i1").waitUntil().val().not().isEqualTo("xyz").then().size(), is(1));
		assertThat($("#i2").waitUntil().val().not().isEqualTo("xyz").then().size(), is(1));
	}

	@Test(expected = SeleniumQueryTimeoutException.class)
	public void waitUntil_val__not_everyone_meet() {
		assertThat($("input").waitUntil(500).val().isEqualTo("abcdef").then().size(), is(1));
	}

	@Test
	public void waitUntil_val__changing() {
		$("#i1").val("abcdef");
		assertThat($("input").waitUntil(500).val().isEqualTo("abcdef").then().size(), is(2));
	}
	
}