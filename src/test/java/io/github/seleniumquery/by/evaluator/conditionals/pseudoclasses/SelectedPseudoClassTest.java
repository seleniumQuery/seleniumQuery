package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest.assertSelectorMatchedSetSize;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class SelectedPseudoClassTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
	@Test
	public void checkedPseudo_with_tag_option() {
		assertSelectorMatchedSetSize("option:selected", 2);
	}
	
	@Test
	public void checkedPseudo_with_tag_input() {
		assertSelectorMatchedSetSize("input:selected", 0);
	}
	
	@Test
	public void checkedPseudo_with_tag_input_checkbox() {
		assertSelectorMatchedSetSize("input[type=checkbox]:selected", 0);
	}
	
	@Test
	public void checkedPseudo_with_tag_input_radio() {
		assertSelectorMatchedSetSize("input[type=radio]:selected", 0);
	}
	
	@Test
	public void checkedPseudo() {
		assertSelectorMatchedSetSize(":selected", 2);
	}
	
}