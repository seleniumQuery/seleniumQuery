package io.github.seleniumquery.locator.filter;

import io.github.seleniumquery.functions.ClosestFunction;
import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorxpath.XPathExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * It will need:
 * - the parent CSS selector, so it matches using closest(): at least one parent must match it
 * - the CONTEXT (root element) of the search, as it can't go after it (if you don't stop at it, its parent may
 *  match and then be included in the result set when it shouldn't)
 */
public class DescendantGeneralCombinatorFilter implements ElementFilter {

	private final ElementFilterList ancestorFilters;
	private final ElementFilterList childrenFilters;
	
	private DescendantGeneralCombinatorFilter(WebElement context,
										String ancestorXPathExpression,
										ElementFilterList ancestorFilters,
									 	 String childrenExpression,
									 	 ElementFilterList childrenFilters) {
		this.ancestorFilters = ancestorFilters;
		this.childrenFilters = childrenFilters;
	}
	
	/**
	 * Every element MUST abide to the CHILDREN filter.
	 * 
	 * Then, every remaining, must have AT LEAST ONE ANCESTOR that matches the ANCESTOR FILTER.
	 */
	@Override
	public List<WebElement> filterElements(WebDriver driver, List<WebElement> elements) {
		List<WebElement> filteredChildren = childrenFilters.filter(driver, elements);
		
		outerFor:for (Iterator<WebElement> iterator = filteredChildren.iterator(); iterator.hasNext();) {
			WebElement currentChild = iterator.next();
			
			// closest() starts in the element, we don't want that because we are testing the parent on the descendant selector
			WebElement startingElement = SelectorUtils.parent(currentChild);
			
			String cssSelector = null;// ancestorCompiled.getCssSelector();
			WebElement matchingAncestor = ClosestFunction.closest(driver, startingElement, cssSelector);
			while (matchingAncestor != null) {
				
				List<WebElement> mas = ancestorFilters.filter(driver, new ArrayList<WebElement>(Arrays.asList(matchingAncestor)));
				boolean theMatchedAncestorMatchesTheFilter = !mas.isEmpty();
				if (theMatchedAncestorMatchesTheFilter) {
					continue outerFor; // this element's ancestor is OK, keep it, continue to next element
				}
				
				// walks up one step, otherwise closest will match the same element again
				matchingAncestor = SelectorUtils.parent(matchingAncestor);
				
				matchingAncestor = ClosestFunction.closest(driver, matchingAncestor, cssSelector);
			}
			iterator.remove();
		}
		return filteredChildren;
	}
	
	public static List<WebElement> parents(WebElement element, XPathExpression selector) {
		return parentz(element, selector.toXPathCondition());
	}
	
	private static List<WebElement> parentz(WebElement element, String xPathCondition) {
		return element.findElements(By.xpath("ancestor::node()[count(ancestor-or-self::html) > 0 and " + xPathCondition.substring(1)));
	}
}