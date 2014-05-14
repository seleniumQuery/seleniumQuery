package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;
import io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class VisiblePseudoClassTest {
	
	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
	@Test
	public void hidden() {
		List<WebElement> elements = SeleniumQueryCssCompilerIntegrationTest.compileAndExecute(":visible");
		
		assertThat(elements, hasSize(6));
		assertThat(elements.get(0).getTagName(), is("html"));
		assertThat(elements.get(1).getTagName(), is("body"));
		assertThat(elements.get(2).getTagName(), is("div"));
		assertThat(elements.get(3).getTagName(), is("h1"));
		assertThat(elements.get(4).getTagName(), is("button"));
		assertThat(elements.get(5).getTagName(), is("span"));
	}
	
}