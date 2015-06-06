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

package io.github.seleniumquery.by.css.combinators;

import io.github.seleniumquery.by.SelectorUtils;
import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorMatcherService;
import io.github.seleniumquery.by.preparser.ArgumentMap;
import io.github.seleniumquery.by.xpath.XPathComponentCompilerService;
import io.github.seleniumquery.by.xpath.component.DescendantDirectComponent;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.DescendantSelector;

/**
 * PARENT > ELEMENT
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class DirectDescendantCssSelector implements CssSelector<DescendantSelector, TagComponent> {
	
	@Override
	public boolean is(WebDriver driver, WebElement element, ArgumentMap stringMap, DescendantSelector descendantSelector) {
		WebElement parent = SelectorUtils.parent(element);
		//noinspection SimplifiableIfStatement
		if (parent.getTagName().equals("html")) {
			return false;
		}
		return CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, descendantSelector.getSimpleSelector())
				&& CssSelectorMatcherService.elementMatchesSelector(driver, parent, stringMap, descendantSelector.getAncestorSelector());
	}
	
	@Override
	public TagComponent toXPath(ArgumentMap stringMap, DescendantSelector descendantSelector) {
		TagComponent parentComponent = XPathComponentCompilerService.compileSelector(stringMap, descendantSelector.getAncestorSelector());
		TagComponent childComponent = XPathComponentCompilerService.compileSelector(stringMap, descendantSelector.getSimpleSelector());

		return DescendantDirectComponent.combine(parentComponent, childComponent);
	}

}