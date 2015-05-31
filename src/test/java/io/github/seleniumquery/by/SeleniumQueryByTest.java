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

package io.github.seleniumquery.by;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SeleniumQueryByTest {

	@Test
	public void isXPathExpression__should_guess_XPath_expressions_correctly() {
		assertIsXPathExpression("(//table//a)[1]");
		assertIsXPathExpression("   ((   (   (   (			(			(/html");
		assertIsXPathExpression("ancestor::*");
		assertIsXPathExpression("   ((  (ancestor::*");
		assertIsXPathExpression("ancestor-or-self::*");
		assertIsXPathExpression("   ((  (ancestor-or-self::*");
		assertIsXPathExpression("attribute::*");
		assertIsXPathExpression("   ((  (attribute::*");
		assertIsXPathExpression("child::*");
		assertIsXPathExpression("   ((  (child::*");
		assertIsXPathExpression("descendant::*");
		assertIsXPathExpression("   ((  (descendant::*");
		assertIsXPathExpression("descendant-or-self::*");
		assertIsXPathExpression("   ((  (descendant-or-self::*");
		assertIsXPathExpression("following::*");
		assertIsXPathExpression("   ((  (following::*");
		assertIsXPathExpression("following-sibling::*");
		assertIsXPathExpression("   ((  (following-sibling::*");
		assertIsXPathExpression("parent::*");
		assertIsXPathExpression("   ((  (parent::*");
		assertIsXPathExpression("preceding::*");
		assertIsXPathExpression("   ((  (preceding::*");
		assertIsXPathExpression("preceding-sibling::*");
		assertIsXPathExpression("   ((  (preceding-sibling::*");
		assertIsXPathExpression("self::*");
		assertIsXPathExpression("   ((  (self::*");
	}

	private void assertIsXPathExpression(String selector) {
		assertTrue("Selector \""+selector+"\" should be considered an XPathExpression.", SeleniumQueryBy.isXPathExpression(selector));
	}

	@Test
	public void isXPathExpression__should_not_confuse_CSS_selectors_with_XPath_expressions() {
		assertIsNotXPathExpression("   #id");
		assertIsNotXPathExpression("   .class");
		assertIsNotXPathExpression("*");
		assertIsNotXPathExpression("tag");
		assertIsNotXPathExpression("ancestor"); // XPath axis without :: wont be a XPath axis
		assertIsNotXPathExpression("tag::xyz");
	}

	private void assertIsNotXPathExpression(String selector) {
		assertFalse("Selector \"" + selector + "\" should NOT be considered an XPathExpression.", SeleniumQueryBy.isXPathExpression(selector));
	}

}