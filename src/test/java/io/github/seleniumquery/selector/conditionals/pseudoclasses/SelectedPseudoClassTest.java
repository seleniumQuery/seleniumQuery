package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest.assertSelectorMatchedSetSize;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class SelectedPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test
	public void selectedPseudoClass_with_tag_option() {
		assertSelectorMatchedSetSize("option:selected", 2);
	}
	
	@Test
	public void selectedPseudoClass_with_tag_input() {
		assertSelectorMatchedSetSize("input:selected", 0);
	}
	
	@Test
	public void selectedPseudoClass_with_tag_input_checkbox() {
		assertSelectorMatchedSetSize("input[type=checkbox]:selected", 0);
	}
	
	@Test
	public void selectedPseudoClass_with_tag_input_radio() {
		assertSelectorMatchedSetSize("input[type=radio]:selected", 0);
	}
	
	@Test
	public void selectedPseudoClass() {
		assertSelectorMatchedSetSize(":selected", 2);
	}
	
}