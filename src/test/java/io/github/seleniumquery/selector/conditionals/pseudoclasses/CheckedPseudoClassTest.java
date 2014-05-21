package io.github.seleniumquery.selector.conditionals.pseudoclasses;

import static io.github.seleniumquery.selector.CssSelectorCompilerServiceTest.assertSelectorMatchedSetSize;
import io.github.seleniumquery.SetUpAndTearDownDriver;

import org.junit.Rule;
import org.junit.Test;

public class CheckedPseudoClassTest {
	
	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	
	@Test
	public void checkedPseudo_with_tag_option() {
		assertSelectorMatchedSetSize("option:checked", 2);
	}
	
	@Test
	public void checkedPseudo_with_tag_input() {
		assertSelectorMatchedSetSize("input:checked", 2);
	}
	
	@Test
	public void checkedPseudo_with_tag_input_checkbox() {
		assertSelectorMatchedSetSize("input[type=checkbox]:checked", 1);
	}
	
	@Test
	public void checkedPseudo_with_tag_input_radio() {
		assertSelectorMatchedSetSize("input[type=radio]:checked", 1);
	}
	
	@Test
	public void checkedPseudo() {
		assertSelectorMatchedSetSize(":checked", 4);
	}
	
}