package io.github.seleniumquery.selector_old_should_move.conditionals.attributes;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.selector.xpath.XPathExpressionList;
import io.github.seleniumquery.selector.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.selectors.attributes.ContainsWordAttributeCssSelector;
import io.github.seleniumquery.selectors.attributes.EndsWithAttributeCssSelector;
import io.github.seleniumquery.selectors.attributes.EqualsOrHasAttributeCssSelector;
import io.github.seleniumquery.selectors.attributes.StartsWithAttributeCssSelector;

import java.util.Iterator;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class AttributeEvaluatorTest {

	@Rule
	public SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(getClass());
	
	@Test
	public void equalsOrHasAttributeEvaluator() {
		assertAll(EqualsOrHasAttributeCssSelector.EQUALS_ATTRIBUTE_SELECTOR_SYMBOL, "c");
	}
	
	@Test
	public void containsPrefixAttributeEvaluator() {
		// TODO maybe there's a problem here on HtmlUnitDriver, check warnings
		assertAll("|=", "c");
	}
	
	@Test
	public void containsSubstringAttributeEvaluator() {
		assertAll("*=", "c");
	}
	
	@Test
	public void containsWordAttributeEvaluator() {
		// TODO maybe there's a problem here on HtmlUnitDriver, check warnings
		assertAll(ContainsWordAttributeCssSelector.CONTAINS_WORD_ATTRIBUTE_SELECTOR_SYMBOL, "c");
	}
	
	@Test
	public void startsWithAttributeEvaluator() {
		assertAll(StartsWithAttributeCssSelector.STARTS_WITH_ATTRIBUTE_SELECTOR_SYMBOL, "");
	}
	
	@Test
	public void endsWithAttributeEvaluator() {
		assertAll(EndsWithAttributeCssSelector.ENDS_WITH_ATTRIBUTE_SELECTOR_SYMBOL, "c");
	}

	public void assertAll(String attr, String suffix) {
		assertSelectorFindsIds("[title" + attr + "ab" + suffix + "]", "d1", "d2", "d3");
		assertSelectorFindsIds("[title" + attr + "'ab" + suffix + "']", "d1", "d2", "d3");
		assertSelectorFindsIds("[title" + attr + "\"ab" + suffix + "\"]", "d1", "d2", "d3");
		
		if ($.browser.getDefaultDriver() == null) {
			// TODO This is not being escaped properly! see #attribute_escaping__maybe_should_change()
			// checked: $= (doesn't work)
			// <div id="d4" title='a"bc'></div>
			assertSelectorFindsIds("[title" + attr + "\"a\\\"b" + suffix + "\"]", "d4");
		}
		
		assertSelectorFindsIds("[title" + attr + "\"a\\'b" + suffix + "\"]", "d5");
		assertSelectorFindsIds("[title" + attr + "\"a'b" + suffix + "\"]", "d5");
		
		assertSelectorFindsIds("[title" + attr + "\"a\\t" + suffix + "\"]");
		assertSelectorFindsIds("[title" + attr + "\"a\\\\t" + suffix + "\"]", "d6");
		
		if (!ContainsWordAttributeCssSelector.CONTAINS_WORD_ATTRIBUTE_SELECTOR_SYMBOL.equals(attr)) {
			assertSelectorFindsIds("[title" + attr + "'a	" + suffix + "']", "d7");
			assertSelectorFindsIds("[title" + attr + "\"a	" + suffix + "\"]", "d7");
			assertSelectorFindsIds("[title" + attr + "\"a\t" + suffix + "\"]", "d7");
		}
		
		assertSelectorFindsIds("[\\31 a2b" + attr + "\"ab" + suffix + "\"]", "d8");
		assertSelectorFindsIds("[-a2b" + attr + "\"ab" + suffix + "\"]", "d9");
		assertSelectorFindsIds("[\\--" + attr + "\"ab" + suffix + "\"]", "d10");
	}
	
	public void assertSelectorFindsIds(String selector, String... ids) {
		List<WebElement> elements = $(selector).get();
		assertThat(elements, hasSize(ids.length));
		
		Iterator<WebElement> iterator = elements.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			WebElement webElement = iterator.next();
			String id = ids[i++];
			assertThat(webElement.getAttribute("id"), is(id));
		}
	}
	
	@Test
	public void attribute_escaping__maybe_should_change() {
		String selector = "[attr=\"a\\\"bc\"]"; // [attr="a\"bc"]
		XPathExpressionList compileSelectorList = XPathSelectorCompilerService.compileSelectorList(selector);
		String xPath = compileSelectorList.toXPath();
		assertThat(xPath, is("(.//*[@attr = 'a\\\"bc'])")); // (.//*[@attr = 'a\"bc'])
	}

}