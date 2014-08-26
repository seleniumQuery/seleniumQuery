package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class SelectedPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test
	public void selectedPseudoClass_with_tag_option() {
		assertThat($("option:selected").size(), is(2));
	}
	
	@Test
	public void selectedPseudoClass_with_tag_input() {
		assertThat($("input:selected").size(), is(0));
	}
	
	@Test
	public void selectedPseudoClass_with_tag_input_checkbox() {
		assertThat($("input[type=checkbox]:selected").size(), is(0));
	}
	
	@Test
	public void selectedPseudoClass_with_tag_input_radio() {
		assertThat($("input[type=radio]:selected").size(), is(0));
	}
	
	@Test
	public void selectedPseudoClass() {
		assertThat($(":selected").size(), is(2));
	}
	
}