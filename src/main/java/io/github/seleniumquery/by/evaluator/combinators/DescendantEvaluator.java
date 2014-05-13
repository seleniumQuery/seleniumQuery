package io.github.seleniumquery.by.evaluator.combinators;

import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.evaluator.SelectorEvaluator;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SeleniumQueryCssCompiler;
import io.github.seleniumquery.by.selector.SqCSSFilter;
import io.github.seleniumquery.functions.ClosestFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.DescendantSelector;

public class DescendantEvaluator implements CSSSelector<DescendantSelector> {

	private static final DescendantEvaluator instance = new DescendantEvaluator();
	public static DescendantEvaluator getInstance() {
		return instance;
	}
	private DescendantEvaluator() { }
	
	/**
	 * E F
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, DescendantSelector descendantSelector) {
		if (SelectorEvaluator.is(driver, element, descendantSelector.getSimpleSelector())) {
	
			WebElement ancestor = SelectorUtils.parent(element);
	
			while (ancestor != null) {
				if (SelectorEvaluator.is(driver, ancestor, descendantSelector.getAncestorSelector())) {
					return true;
				}
				ancestor = SelectorUtils.parent(ancestor);
			}
		}
		return false;
	}

	@Override
	public CompiledSelector compile(WebDriver driver, DescendantSelector descendantSelector) {
		CompiledSelector childrenCompiled = SeleniumQueryCssCompiler.compileSelector(driver, descendantSelector.getSimpleSelector());
		CompiledSelector ancestorCompiled = SeleniumQueryCssCompiler.compileSelector(driver, descendantSelector.getAncestorSelector());
		
		SqCSSFilter descendantFilter = new DescendantFilter(childrenCompiled, ancestorCompiled);
		return new CompiledSelector(ancestorCompiled.getCssSelector()+" "+childrenCompiled.getCssSelector(), descendantFilter);
	}
	
	
	private static final class DescendantFilter implements SqCSSFilter {
		private final CompiledSelector childrenCompiled;
		private final CompiledSelector ancestorCompiled;
		
		private DescendantFilter(CompiledSelector childrenCompiled, CompiledSelector ancestorCompiled) {
			this.childrenCompiled = childrenCompiled;
			this.ancestorCompiled = ancestorCompiled;
		}
		
		@Override
		public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
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

}