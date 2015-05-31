package endtoend.selectors.pseudoclasses.form;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static testinfrastructure.IntegrationTestUtils.t;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DisabledPseudoClassTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;
	
	// http://jsbin.com/guqef/3/edit
	@Test
	public void disabledPseudo_with_tag_button() {
		assertThat($("button:disabled").size(), is(2));
	}
	
	@Test
	public void disabledPseudo_with_tag_input() {
		assertThat($("input:disabled").size(), is(48));
	}
	
	@Test
	public void disabledPseudo_with_tag_select() {
		assertThat($("select:disabled").size(), is(2));
	}
	
	@Test
	public void disabledPseudo_with_tag_option() {
		assertThat($("option:disabled").size(), is(24));
	}
	
	@Test
	public void disabledPseudo_with_tag_optgroup() {
		t(":disabled <optgroup>s", "optgroup:disabled", "optgroup-2", "optgroup-3", "optgroup-5", "optgroup-6", "optgroup-8", "optgroup-9");
	}
	
	@Test
	public void disabledPseudo_with_tag_textarea() {
		assertThat($("textarea:disabled").size(), is(2));
	}
	
	@Test
	public void disabledPseudo() {
		assertThat($(":disabled").size(), is(84));
	}

}