package integration.selectors.pseudoclasses.form;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DisabledPseudoClassTest {
	
	@ClassRule
	public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(DisabledPseudoClassTest.class);
	
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
		assertThat($("optgroup:disabled").size(), is(6));
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