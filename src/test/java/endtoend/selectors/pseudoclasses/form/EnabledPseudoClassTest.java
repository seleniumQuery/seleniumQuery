package endtoend.selectors.pseudoclasses.form;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class EnabledPseudoClassTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

	@Test
	public void enabledPseudo_with_tag_button() {
		assertThat($("button:enabled").size(), is(1));
	}
	
	@Test
	public void enabledPseudo_with_tag_input() {
		assertThat($("input:enabled").size(), is(24));
	}
	
	@Test
	public void enabledPseudo_with_tag_select() {
		assertThat($("select:enabled").size(), is(1));
	}
	
	@Test
	public void enabledPseudo_with_tag_option() {
		assertThat($("option:enabled").size(), is(3));
	}
	
	@Test
	public void enabledPseudo_with_tag_optgroup() {
		assertThat($("optgroup:enabled").size(), is(3));
	}
	
	@Test
	public void enabledPseudo_with_tag_textarea() {
		assertThat($("textarea:enabled").size(), is(1));
	}
	
	@Test
	public void enabledPseudo() {
		assertThat($(":enabled").size(), is(33));
	}
	
	@Test
	public void enabledPseudo_with_class() {
		assertThat($(".c").size(), is(3));
		assertThat($(".c:enabled").size(), is(1));
		assertThat($(".c:not(:enabled)").size(), is(2));
	}

}