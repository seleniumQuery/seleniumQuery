package endtoend.selectors.pseudoclasses.positional;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class EvenPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
	// http://jsbin.com/naguh/4/edit
	@Test
	public void evenPseudoClass() {
		assertThat($("#d1").is(":even"), is(true));
		assertThat($("#d2").is(":even"), is(false));
		assertThat($("#d3").is(":even"), is(true));
		assertThat($("#d4").is(":even"), is(false));
		assertThat($("#d5").is(":even"), is(true));
		assertThat($("#d6").is(":even"), is(false));
		assertThat($("#d7").is(":even"), is(true));
		assertThat($("#d8").is(":even"), is(false));

		assertThat($("#d1").is(".one-amount:even"), is(true));
		assertThat($("#d2").is(".one-amount:even"), is(false));
		assertThat($("#d3").is(".one-amount:even"), is(false));
		assertThat($("#d4").is(".one-amount:even"), is(false));
		assertThat($("#d5").is(".one-amount:even"), is(false));
		assertThat($("#d6").is(".one-amount:even"), is(false));
		assertThat($("#d7").is(".one-amount:even"), is(false));
		assertThat($("#d8").is(".one-amount:even"), is(false));

		assertThat($("#d1").is(".even-amount:even"), is(false));
		assertThat($("#d2").is(".even-amount:even"), is(true));
		assertThat($("#d3").is(".even-amount:even"), is(false));
		assertThat($("#d4").is(".even-amount:even"), is(true));
		assertThat($("#d5").is(".even-amount:even"), is(false));
		assertThat($("#d6").is(".even-amount:even"), is(false));
		assertThat($("#d7").is(".even-amount:even"), is(false));
		assertThat($("#d8").is(".even-amount:even"), is(false));

		assertThat($("#d1").is(".odd-amount:even"), is(false));
		assertThat($("#d2").is(".odd-amount:even"), is(false));
		assertThat($("#d3").is(".odd-amount:even"), is(false));
		assertThat($("#d4").is(".odd-amount:even"), is(false));
		assertThat($("#d5").is(".odd-amount:even"), is(false));
		assertThat($("#d6").is(".odd-amount:even"), is(true));
		assertThat($("#d7").is(".odd-amount:even"), is(false));
		assertThat($("#d8").is(".odd-amount:even"), is(true));

		assertThat($("div:even").size(), is(4));
		assertThat($(".one-amount:even").size(), is(1));
		assertThat($(".even-amount:even").size(), is(2));
		assertThat($(".odd-amount:even").size(), is(2));
		assertThat($(".empty-amount:even").size(), is(0));
	}
	
}