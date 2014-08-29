package io.github.seleniumquery.selectors.combinators;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssSelector;
import io.github.seleniumquery.selector.CssSelectorCompilerService;
import io.github.seleniumquery.selector.CssSelectorMatcherService;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selector.SqSelectorKind;
import io.github.seleniumquery.selector.SqXPathSelector;
import io.github.seleniumquery.selector.XPathSelectorCompilerService;
import io.github.seleniumquery.selector.XPathSelectorFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.SiblingSelector;

public class DirectAdjacentCssSelector implements CssSelector<SiblingSelector> {

	private static final DirectAdjacentCssSelector instance = new DirectAdjacentCssSelector();
	public static DirectAdjacentCssSelector getInstance() {
		return instance;
	}
	private DirectAdjacentCssSelector() { }

	/**
	 * E + F
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		WebElement previousElement = SelectorUtils.getPreviousSibling(element);
		return CssSelectorMatcherService.elementMatchesSelector(driver, previousElement, stringMap, siblingSelector.getSelector())
				&& CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, siblingSelector.getSiblingSelector());
	}

	@Override
	public CompiledCssSelector compile(WebDriver driver, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		CompiledCssSelector previousElementCompiled = CssSelectorCompilerService.compileSelector(driver, stringMap, siblingSelector.getSelector());
		CompiledCssSelector siblingElementCompiled = CssSelectorCompilerService.compileSelector(driver, stringMap, siblingSelector.getSiblingSelector());
		
		ElementFilter directAdjacentFilter = new DirectAdjacentFilter(previousElementCompiled, siblingElementCompiled);
		return new CompiledCssSelector(previousElementCompiled.getCssSelector()+"+"+siblingElementCompiled.getCssSelector(),
										directAdjacentFilter);
	}
	
	private static final class DirectAdjacentFilter implements ElementFilter {
		private final CompiledCssSelector previousElementCompiled;
		private final CompiledCssSelector siblingElementCompiled;
		
		private DirectAdjacentFilter(CompiledCssSelector previousElementCompiled, CompiledCssSelector siblingElementCompiled) {
			this.previousElementCompiled = previousElementCompiled;
			this.siblingElementCompiled = siblingElementCompiled;
		}
		
		@Override
		public List<WebElement> filterElements(WebDriver driver, List<WebElement> elements) {
			elements = siblingElementCompiled.filter(driver, elements);
			
			for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
				WebElement element = iterator.next();
				
				WebElement previousSibling = SelectorUtils.getPreviousSibling(element);
					
				List<WebElement> psf = previousElementCompiled.filter(driver, new ArrayList<WebElement>(Arrays.asList(previousSibling)));
				boolean previousSiblingMatchesTheFilter = !psf.isEmpty();
				if (!previousSiblingMatchesTheFilter) {
					// this element's previous sibling is NOT ok, dont keep it
					iterator.remove();
				}
			}
			return elements;
		}
	}

	@Override
	public SqXPathSelector toXPath(WebDriver driver, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		SqXPathSelector previousElementCompiled = XPathSelectorCompilerService.compileSelector(driver, stringMap, siblingSelector.getSelector());
		SqXPathSelector siblingElementCompiled = XPathSelectorCompilerService.compileSelector(driver, stringMap, siblingSelector.getSiblingSelector());
		
		SqXPathSelector positionOne = XPathSelectorFactory.createNoFilterSelector("[position() = 1]");
		siblingElementCompiled.combine(positionOne).kind = SqSelectorKind.ADJACENT;
		
		return previousElementCompiled.combine(siblingElementCompiled);
	}

}