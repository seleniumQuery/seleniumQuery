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

package io.github.seleniumquery.by.firstgen.filter;

import io.github.seleniumquery.by.common.elementfilter.ElementFilter;
import io.github.seleniumquery.by.firstgen.css.CssSelectorMatcherService;
import io.github.seleniumquery.functions.jquery.traversing.treetraversal.ClosestFunction;
import io.github.seleniumquery.utils.SelectorUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class CompiledCssSelector {
	public String getCssSelector() { return null; }
	public List<WebElement> filter(WebDriver driver, List<WebElement> elements) { return null; }
}

class OldGeneralAdjacentFilter implements ElementFilter {
	private final CompiledCssSelector siblingsCompiledSelector;
	private final CompiledCssSelector elementCompiledSelector;
	
	private OldGeneralAdjacentFilter(CompiledCssSelector previousElementCompiled, CompiledCssSelector siblingElementCompiled) {
		this.siblingsCompiledSelector = previousElementCompiled;
		this.elementCompiledSelector = siblingElementCompiled;
	}
	
	@Override
	public List<WebElement> filterElements(WebDriver driver, List<WebElement> elements) {
		elements = elementCompiledSelector.filter(driver, elements);
		
		outerFor:for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
			WebElement element = iterator.next();
			
			List<WebElement> previousSiblings = SelectorUtils.getPreviousSiblings(element);
			
			previousSiblings = siblingsCompiledSelector.filter(driver, previousSiblings);
			
			for (WebElement previousSibling : previousSiblings) {
				boolean previousSiblingMatchesSelectorFirstPart = CssSelectorMatcherService.elementMatchesStringSelector(driver, previousSibling,
																	siblingsCompiledSelector.getCssSelector());
				if (previousSiblingMatchesSelectorFirstPart) {
					// found a mathing sibling, dont remove the element from the list, continue to next element
					continue outerFor;
				}
			}
			// no matching sibling was found, remove element
			iterator.remove();
			
		}
		return elements;
	}
}

class OldDirectAdjacentFilter implements ElementFilter {
	private final CompiledCssSelector previousElementCompiled;
	private final CompiledCssSelector siblingElementCompiled;
	
	private OldDirectAdjacentFilter(CompiledCssSelector previousElementCompiled, CompiledCssSelector siblingElementCompiled) {
		this.previousElementCompiled = previousElementCompiled;
		this.siblingElementCompiled = siblingElementCompiled;
	}
	
	@Override
	public List<WebElement> filterElements(WebDriver driver, List<WebElement> elements) {
		elements = siblingElementCompiled.filter(driver, elements);
		
		for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
			WebElement element = iterator.next();
			
			WebElement previousSibling = SelectorUtils.getPreviousSibling(element);
				
			List<WebElement> psf = previousElementCompiled.filter(driver, new ArrayList<>(Arrays.asList(previousSibling)));
			boolean previousSiblingMatchesTheFilter = !psf.isEmpty();
			if (!previousSiblingMatchesTheFilter) {
				// this element's previous sibling is NOT ok, dont keep it
				iterator.remove();
			}
		}
		return elements;
	}
}

class OldChildSelectorFilter implements ElementFilter {
	private final CompiledCssSelector parentCompiledSelector;
	private final CompiledCssSelector elementCompiledSelector;
	
	private OldChildSelectorFilter(CompiledCssSelector parentCompiledSelector, CompiledCssSelector elementCompiledSelector) {
		this.parentCompiledSelector = parentCompiledSelector;
		this.elementCompiledSelector = elementCompiledSelector;
	}
	
	@Override
	public List<WebElement> filterElements(WebDriver driver, List<WebElement> elements) {
		elements = elementCompiledSelector.filter(driver, elements);
		
		for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
			WebElement element = iterator.next();
			
			WebElement parent = SelectorUtils.parent(element);
				
			List<WebElement> pf = parentCompiledSelector.filter(driver, new ArrayList<>(Arrays.asList(parent)));
			boolean parentMatchesTheFilter = !pf.isEmpty();
			if (!parentMatchesTheFilter) {
				// this element's parent is NOT ok, dont keep it
				iterator.remove();
			}
		}
		return elements;
	}
}

class OldDescendantFilter implements ElementFilter {
	private final CompiledCssSelector childrenCompiled;
	private final CompiledCssSelector ancestorCompiled;
	
	private OldDescendantFilter(CompiledCssSelector childrenCompiled, CompiledCssSelector ancestorCompiled) {
		this.childrenCompiled = childrenCompiled;
		this.ancestorCompiled = ancestorCompiled;
	}
	
	@Override
	public List<WebElement> filterElements(WebDriver driver, List<WebElement> elements) {
		elements = childrenCompiled.filter(driver, elements);
		
		outerFor:for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
			WebElement element = iterator.next();
			
			// closest() starts in the element, we dont want that because we are testing the parent on the descendant selector
			WebElement startingElement = SelectorUtils.parent(element);
			
			WebElement matchingAncestor = ClosestFunction.closest(driver, startingElement, ancestorCompiled.getCssSelector());
			while (matchingAncestor != null) {
				
				List<WebElement> mas = ancestorCompiled.filter(driver, new ArrayList<>(Arrays.asList(matchingAncestor)));
				boolean theMatchedAncestorMatchesTheFilter = !mas.isEmpty();
				if (theMatchedAncestorMatchesTheFilter) {
					continue outerFor; // this element's ancestor is ok, keep it, continue to next element
				}
				
				// walks up one step, otherwise closest will match the same element again
				matchingAncestor = SelectorUtils.parent(matchingAncestor);
				
				matchingAncestor = ClosestFunction.closest(driver, matchingAncestor, ancestorCompiled.getCssSelector());
			}
			iterator.remove();
		}
		return elements;
	}
}