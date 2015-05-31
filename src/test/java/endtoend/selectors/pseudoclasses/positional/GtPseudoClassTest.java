package endtoend.selectors.pseudoclasses.positional;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class GtPseudoClassTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;
	
	// http://jsbin.com/laganusi/1/edit
	@Test
	public void gtPseudoClass_on_everyone() {
		assertThat($(":gt(3)").size(), is(20-2)); // 20 in the jsbin, 18 here
		assertThat($("*:gt(3)").size(), is(20-2));
		assertThat($(":gt(-3)").size(), is(2));
		assertThat($("*:gt(-3)").size(), is(2));
	}
	
	@Test
	public void gtPseudoClass__on_tag() {
		assertThat($("td:gt(4)").size(), is(4));
		assertThat($("td:gt(-2)").size(), is(1));

		assertThat($("div:gt(-4)").size(), is(3));
		assertThat($("div:gt(-3)").size(), is(2));
		assertThat($("div:gt(-2)").size(), is(1));
		assertThat($("div:gt(-1)").size(), is(0));
		assertThat($("div:gt(0)").size(), is(2));
		assertThat($("div:gt(1)").size(), is(1));
		assertThat($("div:gt(2)").size(), is(0));
		assertThat($("div:gt(3)").size(), is(0));
		assertThat($("div:gt(4)").size(), is(0));
	}
	
	@Test
	public void gtPseudoClass__stacked() {
		assertThat($("div:gt(0):gt(0)").size(), is(1));
		assertThat($("div:gt(0):gt(0):gt(0)").size(), is(0));

		assertThat($("div:gt(-1):gt(-1)").size(), is(0));
		assertThat($("div:gt(-1):gt(-1):gt(-1)").size(), is(0));
	}
	
	@Test
	public void gtPseudoClass__on_specific_elements() {
		assertThat($("#d1").is("div:gt(0)"), is(false));
		assertThat($("#d1").is("div:gt(1)"), is(false));
		assertThat($("#d1").is("div:gt(2)"), is(false));
		assertThat($("#d1").is("div:gt(3)"), is(false));
		assertThat($("#d1").is("div:gt(4)"), is(false));
		assertThat($("#d1").is("div:gt(-1)"), is(false));
		assertThat($("#d1").is("div:gt(-2)"), is(false));
		assertThat($("#d1").is("div:gt(-3)"), is(false));
		assertThat($("#d1").is("div:gt(-4)"), is(true));
		assertThat($("#d1").is("div:gt(-5)"), is(true));

		assertThat($("#d2").is("div:gt(0)"), is(true));
		assertThat($("#d2").is("div:gt(1)"), is(false));
		assertThat($("#d2").is("div:gt(2)"), is(false));
		assertThat($("#d2").is("div:gt(3)"), is(false));
		assertThat($("#d2").is("div:gt(4)"), is(false));
		assertThat($("#d2").is("div:gt(-1)"), is(false));
		assertThat($("#d2").is("div:gt(-2)"), is(false));
		assertThat($("#d2").is("div:gt(-3)"), is(true));
		assertThat($("#d2").is("div:gt(-4)"), is(true));
		assertThat($("#d2").is("div:gt(-5)"), is(true));

		assertThat($("#d3").is("div:gt(0)"), is(true));
		assertThat($("#d3").is("div:gt(1)"), is(true));
		assertThat($("#d3").is("div:gt(2)"), is(false));
		assertThat($("#d3").is("div:gt(3)"), is(false));
		assertThat($("#d3").is("div:gt(4)"), is(false));
		assertThat($("#d3").is("div:gt(-1)"), is(false));
		assertThat($("#d3").is("div:gt(-2)"), is(true));
		assertThat($("#d3").is("div:gt(-3)"), is(true));
		assertThat($("#d3").is("div:gt(-4)"), is(true));
		assertThat($("#d3").is("div:gt(-5)"), is(true));
	}
	
	@Test
	public void gtPseudoClass__on_empty_set() {
		// This is too slow! wonder why...
		assertThat($("span:gt(-4)").size(), is(0));
		assertThat($("span:gt(-3)").size(), is(0));
		assertThat($("span:gt(-2)").size(), is(0));
		assertThat($("span:gt(-1)").size(), is(0));
		assertThat($("span:gt(0)").size(), is(0));
		assertThat($("span:gt(1)").size(), is(0));
		assertThat($("span:gt(2)").size(), is(0));
		assertThat($("span:gt(3)").size(), is(0));
		assertThat($("span:gt(4)").size(), is(0));
	}
	
}