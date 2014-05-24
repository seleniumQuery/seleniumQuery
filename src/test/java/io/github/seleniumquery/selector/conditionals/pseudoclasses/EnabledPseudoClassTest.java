package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class EnabledPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
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

}