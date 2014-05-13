package io.github.seleniumquery.by.evaluator.conditionals.attributes;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;
import io.github.seleniumquery.by.selector.SeleniumQueryCssCompilerIntegrationTest;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class EndsWithAttributeEvaluatorTest {

	@Before
	public void setUp() {
		$.browser.setDefaultDriver(TestInfrastructure.getDriver());
		$.browser.openUrl(TestInfrastructure.getHtmlTestFileUrl(getClass()));
	}
	
	@Test
	public void endsWithAttributeEvaluator() {
		assertSelectorFindsIds("[title$=abc]", "d1", "d2", "d3");
		assertSelectorFindsIds("[title$='abc']", "d1", "d2", "d3");
		assertSelectorFindsIds("[title$=\"abc\"]", "d1", "d2", "d3");
		
		if (!($.browser.getDefaultDriver() instanceof HtmlUnitDriver)) {
			// TODO The HtmlUnit driver does not handle this correctly!
			// <div id="d4" title='a"bc'></div>
			assertSelectorFindsIds("[title$=\"a\\\"bc\"]", "d4");
		}
		
		assertSelectorFindsIds("[title$=\"a\\'bc\"]", "d5");
		assertSelectorFindsIds("[title$=\"a'bc\"]", "d5");
		
		assertSelectorFindsIds("[title$=\"a\\tc\"]");
		assertSelectorFindsIds("[title$=\"a\\\\tc\"]", "d6");
		
		assertSelectorFindsIds("[title$='a	c']", "d7");
		assertSelectorFindsIds("[title$=\"a	c\"]", "d7");
		assertSelectorFindsIds("[title$=\"a\tc\"]", "d7");
		
		assertSelectorFindsIds("[\\31 a2b$=\"xyz\"]", "d8");
		assertSelectorFindsIds("[-a2b$=\"xyz\"]", "d9");
		assertSelectorFindsIds("[\\--$=\"xyz\"]", "d10");
	}
	
	public void assertSelectorFindsIds(String selector, String... ids) {
		List<WebElement> elements = SeleniumQueryCssCompilerIntegrationTest.compileAndExecute(selector);
		assertThat(elements, hasSize(ids.length));
		
		Iterator<WebElement> iterator = elements.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			WebElement webElement = iterator.next();
			String id = ids[i++];
			assertThat(webElement.getAttribute("id"), is(id));
		}
	}

}