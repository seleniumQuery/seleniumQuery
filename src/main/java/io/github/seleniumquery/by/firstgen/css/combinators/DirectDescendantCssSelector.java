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

package io.github.seleniumquery.by.firstgen.css.combinators;

import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.firstgen.css.CssSelector;
import io.github.seleniumquery.by.firstgen.css.CssSelectorMatcherService;
import io.github.seleniumquery.by.firstgen.xpath.XPathComponentCompilerService;
import io.github.seleniumquery.by.firstgen.xpath.component.DescendantDirectComponent;
import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;
import io.github.seleniumquery.utils.SelectorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.DescendantSelector;

/**
 * {@code PARENT > ELEMENT }
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class DirectDescendantCssSelector implements CssSelector<DescendantSelector, TagComponent> {
	
	@Override
	public boolean is(WebDriver driver, WebElement element, ArgumentMap argumentMap, DescendantSelector descendantSelector) {
		WebElement parent = SelectorUtils.parent(element);
		//noinspection SimplifiableIfStatement
		if (parent == null || parent.getTagName().equals("html")) {
			return false;
		}
		return elementMatchesDescendantSelector(driver, element, descendantSelector, argumentMap)
			   && parentMatchesAncestorSelector(driver, parent, descendantSelector, argumentMap);
	}

	private boolean parentMatchesAncestorSelector(WebDriver driver, WebElement parent, DescendantSelector descendantSelector, ArgumentMap argumentMap) {
		return CssSelectorMatcherService.elementMatchesSelector(driver, parent, argumentMap, descendantSelector.getAncestorSelector());
	}

	private boolean elementMatchesDescendantSelector(WebDriver driver, WebElement element, DescendantSelector descendantSelector, ArgumentMap argumentMap) {
		return CssSelectorMatcherService.elementMatchesSelector(driver, element, argumentMap, descendantSelector.getSimpleSelector());
	}

	@Override
	public TagComponent toXPath(ArgumentMap argumentMap, DescendantSelector descendantSelector) {
		TagComponent parentComponent = XPathComponentCompilerService.compileSelector(argumentMap, descendantSelector.getAncestorSelector());
		TagComponent childComponent = XPathComponentCompilerService.compileSelector(argumentMap, descendantSelector.getSimpleSelector());

		return DescendantDirectComponent.combine(parentComponent, childComponent);
	}

}