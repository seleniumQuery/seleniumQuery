package io.github.seleniumquery.selectors.combinators;

import io.github.seleniumquery.functions.ClosestFunction;
import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorcss.CssSelector;
import io.github.seleniumquery.selectorcss.CssSelectorCompilerService;
import io.github.seleniumquery.selectorcss.CssSelectorMatcherService;
import io.github.seleniumquery.selectorxpath.SqSelectorKind;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorCompilerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.DescendantSelector;

public class DescendantCssSelector implements CssSelector<DescendantSelector> {

	private static final DescendantCssSelector instance = new DescendantCssSelector();
	public static DescendantCssSelector getInstance() {
		return instance;
	}
	private DescendantCssSelector() { }
	
	/**
	 * E F
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, DescendantSelector descendantSelector) {
		if (CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, descendantSelector.getSimpleSelector())) {
	
			WebElement ancestor = SelectorUtils.parent(element);
	
			while (ancestor != null) {
				if (CssSelectorMatcherService.elementMatchesSelector(driver, ancestor, stringMap, descendantSelector.getAncestorSelector())) {
					return true;
				}
				ancestor = SelectorUtils.parent(ancestor);
			}
		}
		return false;
	}

	@Override
	public CompiledCssSelector compile(WebDriver driver, Map<String, String> stringMap, DescendantSelector descendantSelector) {
		CompiledCssSelector childrenCompiled = CssSelectorCompilerService.compileSelector(driver, stringMap, descendantSelector.getSimpleSelector());
		CompiledCssSelector ancestorCompiled = CssSelectorCompilerService.compileSelector(driver, stringMap, descendantSelector.getAncestorSelector());
		
		ElementFilter descendantFilter = new DescendantFilter(childrenCompiled, ancestorCompiled);
		return new CompiledCssSelector(ancestorCompiled.getCssSelector()+" "+childrenCompiled.getCssSelector(), descendantFilter);
	}
	
	
	private static final class DescendantFilter implements ElementFilter {
		private final CompiledCssSelector childrenCompiled;
		private final CompiledCssSelector ancestorCompiled;
		
		private DescendantFilter(CompiledCssSelector childrenCompiled, CompiledCssSelector ancestorCompiled) {
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
					
					List<WebElement> mas = ancestorCompiled.filter(driver, new ArrayList<WebElement>(Arrays.asList(matchingAncestor)));
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

	@Override
	public XPathExpression toXPath(WebDriver driver, Map<String, String> stringMap, DescendantSelector descendantSelector) {
		XPathExpression ancestorCompiled = XPathSelectorCompilerService.compileSelector(driver, stringMap, descendantSelector.getAncestorSelector());
		XPathExpression childrenCompiled = XPathSelectorCompilerService.compileSelector(driver, stringMap, descendantSelector.getSimpleSelector());
		System.out.println("@# ANCESTOR XPATH: "+ancestorCompiled.toXPath());
		childrenCompiled.kind = SqSelectorKind.DESCENDANT_GENERAL;
		return ancestorCompiled.combine(childrenCompiled);
	}
	
	private static final class DescendantXPathFilter implements ElementFilter {
		private final XPathExpression ancestorCompiled;
		private final XPathExpression childrenCompiled;
		
		private DescendantXPathFilter(XPathExpression ancestorCompiled, XPathExpression childrenCompiled) {
			this.ancestorCompiled = ancestorCompiled;
			this.childrenCompiled = childrenCompiled;
		}
		
		@Override
		public List<WebElement> filterElements(WebDriver driver, List<WebElement> elements) {
			List<WebElement> filteredElements = childrenCompiled.filter(driver, elements);
			
			outerFor:for (Iterator<WebElement> iterator = filteredElements.iterator(); iterator.hasNext();) {
				WebElement element = iterator.next();
				
				// closest() starts in the element, we dont want that because we are testing the parent on the descendant selector
				WebElement startingElement = SelectorUtils.parent(element);
				
				String cssSelector = null;// ancestorCompiled.getCssSelector();
				WebElement matchingAncestor = ClosestFunction.closest(driver, startingElement, cssSelector);
				while (matchingAncestor != null) {
					
					List<WebElement> mas = ancestorCompiled.filter(driver, new ArrayList<WebElement>(Arrays.asList(matchingAncestor)));
					boolean theMatchedAncestorMatchesTheFilter = !mas.isEmpty();
					if (theMatchedAncestorMatchesTheFilter) {
						continue outerFor; // this element's ancestor is ok, keep it, continue to next element
					}
					
					// walks up one step, otherwise closest will match the same element again
					matchingAncestor = SelectorUtils.parent(matchingAncestor);
					
					matchingAncestor = ClosestFunction.closest(driver, matchingAncestor, cssSelector);
				}
				iterator.remove();
			}
			return filteredElements;
		}
	}
	
	public static List<WebElement> parents(WebElement element, XPathExpression selector) {
		return parentz(element, selector.toXPathCondition());
	}
	
	private static List<WebElement> parentz(WebElement element, String xPathCondition) {
		return element.findElements(By.xpath("ancestor::node()[count(ancestor-or-self::html) > 0 and " + xPathCondition.substring(1)));
	}

}