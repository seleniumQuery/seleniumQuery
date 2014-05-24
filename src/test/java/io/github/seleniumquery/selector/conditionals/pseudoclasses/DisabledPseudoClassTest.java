package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class DisabledPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
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