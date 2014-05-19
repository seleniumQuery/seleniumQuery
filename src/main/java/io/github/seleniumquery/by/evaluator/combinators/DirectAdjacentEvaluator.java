package io.github.seleniumquery.by.evaluator.combinators;

import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.evaluator.SelectorEvaluator;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SeleniumQueryCssCompiler;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.SiblingSelector;

public class DirectAdjacentEvaluator implements CSSSelector<SiblingSelector> {

	private static final DirectAdjacentEvaluator instance = new DirectAdjacentEvaluator();
	public static DirectAdjacentEvaluator getInstance() {
		return instance;
	}
	private DirectAdjacentEvaluator() { }

	/**
	 * E + F
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		WebElement previousElement = SelectorUtils.getPreviousSibling(element);
		return SelectorEvaluator.elementMatchesSelector(driver, previousElement, stringMap, siblingSelector.getSelector())
				&& SelectorEvaluator.elementMatchesSelector(driver, element, stringMap, siblingSelector.getSiblingSelector());
	}

	@Override
	public CompiledSelector compile(WebDriver driver, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		CompiledSelector previousElementCompiled = SeleniumQueryCssCompiler.compileSelector(driver, stringMap, siblingSelector.getSelector());
		CompiledSelector siblingElementCompiled = SeleniumQueryCssCompiler.compileSelector(driver, stringMap, siblingSelector.getSiblingSelector());
		
		SqCSSFilter directAdjacentFilter = new DirectAdjacentFilter(previousElementCompiled, siblingElementCompiled);
		return new CompiledSelector(previousElementCompiled.getCssSelector()+"+"+siblingElementCompiled.getCssSelector(),
										directAdjacentFilter);
	}
	
	private static final class DirectAdjacentFilter implements SqCSSFilter {
		private final CompiledSelector previousElementCompiled;
		private final CompiledSelector siblingElementCompiled;
		
		private DirectAdjacentFilter(CompiledSelector previousElementCompiled, CompiledSelector siblingElementCompiled) {
			this.previousElementCompiled = previousElementCompiled;
			this.siblingElementCompiled = siblingElementCompiled;
		}
		
		@Override
		public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
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

}