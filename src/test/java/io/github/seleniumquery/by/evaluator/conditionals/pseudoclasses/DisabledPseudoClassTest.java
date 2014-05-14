package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest.assertSelectorMatchedSetSize;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DisabledPseudoClassTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
	@Test
	public void disabledPseudo_with_tag_button() {
		assertSelectorMatchedSetSize("button:disabled", 2);
	}
	
	@Test
	public void disabledPseudo_with_tag_input() {
		assertSelectorMatchedSetSize("input:disabled", 48);
	}
	
	@Test
	public void disabledPseudo_with_tag_select() {
		assertSelectorMatchedSetSize("select:disabled", 2);
	}
	
	@Test
	public void disabledPseudo_with_tag_option() {
		if ($.browser.getDefaultDriver() instanceof HtmlUnitDriver) {
			// TODO this is a known HtmlUnit bug! See issue #3
			assertSelectorMatchedSetSize("option:disabled", 18);
		}
		else {
			assertSelectorMatchedSetSize("option:disabled", 24);
		}
	}
	
	@Test
	public void disabledPseudo_with_tag_optgroup() {
		assertSelectorMatchedSetSize("optgroup:disabled", 6);
	}
	
	@Test
	public void disabledPseudo_with_tag_textarea() {
		assertSelectorMatchedSetSize("textarea:disabled", 2);
	}
	
	@Test
	public void disabledPseudo() {
		if ($.browser.getDefaultDriver() instanceof HtmlUnitDriver) {
			// TODO see disabledPseudo_with_tag_option()
			assertSelectorMatchedSetSize(":disabled", 78);
		}
		else {
			assertSelectorMatchedSetSize(":disabled", 84);
		}
	}

}