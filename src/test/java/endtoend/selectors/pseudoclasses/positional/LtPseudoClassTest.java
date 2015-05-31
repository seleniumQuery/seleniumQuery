package endtoend.selectors.pseudoclasses.positional;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class LtPseudoClassTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;
	
	// http://jsbin.com/bavidofo/1/edit
	@Test
	public void ltPseudoClass() {
		assertThat($(":lt(3)").size(), is(3));
		assertThat($("*:lt(3)").size(), is(3));
		assertThat($(":lt(-3)").size(), is(19)); // in jsbin is 21, due to script tag and probably something else
		assertThat($("*:lt(-3)").size(), is(19));
	}
	
	@Test
	public void ltPseudoClass__on_tag() {
		assertThat($("td:lt(4)").size(), is(4));
		assertThat($("td:lt(-2)").size(), is(7));

		assertThat($("div:lt(-4)").size(), is(0));
		assertThat($("div:lt(-3)").size(), is(0));
		assertThat($("div:lt(-2)").size(), is(1));
		assertThat($("div:lt(-1)").size(), is(2));
		assertThat($("div:lt(0)").size(), is(0));
		assertThat($("div:lt(1)").size(), is(1));
		assertThat($("div:lt(2)").size(), is(2));
		assertThat($("div:lt(3)").size(), is(3));
		assertThat($("div:lt(4)").size(), is(3));
	}
	
	@Test
	public void ltPseudoClass__stacked() {
		assertThat($("div:lt(1):lt(1)").size(), is(1));
		assertThat($("div:lt(1):lt(1):lt(1)").size(), is(1));

		assertThat($("div:lt(-1):lt(-1)").size(), is(1));
		assertThat($("div:lt(-1):lt(-1):lt(-1)").size(), is(0));
	}
	
	@Test
	public void ltPseudoClass__on_specific_elements() {
		assertThat($("#d1").is("div:lt(0)"), is(false));
		assertThat($("#d1").is("div:lt(1)"), is(true));
		assertThat($("#d1").is("div:lt(2)"), is(true));
		assertThat($("#d1").is("div:lt(3)"), is(true));
		assertThat($("#d1").is("div:lt(4)"), is(true));
		assertThat($("#d1").is("div:lt(-1)"), is(true));
		assertThat($("#d1").is("div:lt(-2)"), is(true));
		assertThat($("#d1").is("div:lt(-3)"), is(false));
		assertThat($("#d1").is("div:lt(-4)"), is(false));
		assertThat($("#d1").is("div:lt(-5)"), is(false));

		assertThat($("#d2").is("div:lt(0)"), is(false));
		assertThat($("#d2").is("div:lt(1)"), is(false));
		assertThat($("#d2").is("div:lt(2)"), is(true));
		assertThat($("#d2").is("div:lt(3)"), is(true));
		assertThat($("#d2").is("div:lt(4)"), is(true));
		assertThat($("#d2").is("div:lt(-1)"), is(true));
		assertThat($("#d2").is("div:lt(-2)"), is(false));
		assertThat($("#d2").is("div:lt(-3)"), is(false));
		assertThat($("#d2").is("div:lt(-4)"), is(false));
		assertThat($("#d2").is("div:lt(-5)"), is(false));

		assertThat($("#d3").is("div:lt(0)"), is(false));
		assertThat($("#d3").is("div:lt(1)"), is(false));
		assertThat($("#d3").is("div:lt(2)"), is(false));
		assertThat($("#d3").is("div:lt(3)"), is(true));
		assertThat($("#d3").is("div:lt(4)"), is(true));
		assertThat($("#d3").is("div:lt(-1)"), is(false));
		assertThat($("#d3").is("div:lt(-2)"), is(false));
		assertThat($("#d3").is("div:lt(-3)"), is(false));
		assertThat($("#d3").is("div:lt(-4)"), is(false));
		assertThat($("#d3").is("div:lt(-5)"), is(false));
	}
	
	@Test
	public void ltPseudoClass__on_empty_sets() {
		assertThat($("span:lt(-4)").size(), is(0));
		assertThat($("span:lt(-3)").size(), is(0));
		assertThat($("span:lt(-2)").size(), is(0));
		assertThat($("span:lt(-1)").size(), is(0));
		assertThat($("span:lt(0)").size(), is(0));
		assertThat($("span:lt(1)").size(), is(0));
		assertThat($("span:lt(2)").size(), is(0));
		assertThat($("span:lt(3)").size(), is(0));
		assertThat($("span:lt(4)").size(), is(0));
	}
	
}