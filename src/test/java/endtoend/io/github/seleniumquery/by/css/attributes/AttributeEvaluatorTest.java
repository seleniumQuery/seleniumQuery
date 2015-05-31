/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package endtoend.io.github.seleniumquery.by.css.attributes;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.by.css.attributes.ContainsWordAttributeCssSelector;
import io.github.seleniumquery.by.css.attributes.EndsWithAttributeCssSelector;
import io.github.seleniumquery.by.css.attributes.EqualsOrHasAttributeCssSelector;
import io.github.seleniumquery.by.css.attributes.StartsWithAttributeCssSelector;
import io.github.seleniumquery.by.xpath.TagComponentList;
import io.github.seleniumquery.by.xpath.XPathComponentCompilerService;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AttributeEvaluatorTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;
	
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
		
		if ($.driver().get() == null) {
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
		TagComponentList compileSelectorList = XPathComponentCompilerService.compileSelectorList(selector);
		String xPath = compileSelectorList.toXPath();
		assertThat(xPath, is("(.//*[@*[translate(name(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'attr'] = 'a\\\"bc'])")); // (.//*[@attr = 'a\"bc'])
	}

}