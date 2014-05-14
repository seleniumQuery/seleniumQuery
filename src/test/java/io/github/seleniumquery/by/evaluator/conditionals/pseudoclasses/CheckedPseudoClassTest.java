package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest.assertSelectorMatchedSetSize;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;

public class CheckedPseudoClassTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
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