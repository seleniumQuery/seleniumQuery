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
import org.w3c.css.sac.DescendantSelector;

public class ChildSelectorEvaluator implements CSSSelector<DescendantSelector> {
	
	private static final ChildSelectorEvaluator instance = new ChildSelectorEvaluator();
	public static ChildSelectorEvaluator getInstance() {
		return instance;
	}
	private ChildSelectorEvaluator() { }
	
	/**
	 * PARENT > ELEMENT
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, DescendantSelector descendantSelector) {
		WebElement parent = SelectorUtils.parent(element);
		if (parent.getTagName().equals("html")) {
			return false;
		}
		return SelectorEvaluator.elementMatchesSelector(driver, element, stringMap, descendantSelector.getSimpleSelector())
				&& SelectorEvaluator.elementMatchesSelector(driver, parent, stringMap, descendantSelector.getAncestorSelector());
	}
	
	@Override
	public CompiledSelector compile(WebDriver driver, Map<String, String> stringMap, DescendantSelector descendantSelector) {
		CompiledSelector elementCompiledSelector = SeleniumQueryCssCompiler.compileSelector(driver, stringMap, descendantSelector.getSimpleSelector());
		CompiledSelector parentCompiledSelector = SeleniumQueryCssCompiler.compileSelector(driver, stringMap, descendantSelector.getAncestorSelector());
		
		SqCSSFilter childSelectorFilter = new ChildSelectorFilter(parentCompiledSelector, elementCompiledSelector);
		return new CompiledSelector(parentCompiledSelector.getCssSelector()+">"+elementCompiledSelector.getCssSelector(),
										childSelectorFilter);
	}
	
	private static final class ChildSelectorFilter implements SqCSSFilter {
		private final CompiledSelector parentCompiledSelector;
		private final CompiledSelector elementCompiledSelector;
		
		private ChildSelectorFilter(CompiledSelector parentCompiledSelector, CompiledSelector elementCompiledSelector) {
			this.parentCompiledSelector = parentCompiledSelector;
			this.elementCompiledSelector = elementCompiledSelector;
		}
		
		@Override
		public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
			elements = elementCompiledSelector.filter(driver, elements);
			
			for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
				WebElement element = iterator.next();
				
				WebElement parent = SelectorUtils.parent(element);
					
				List<WebElement> pf = parentCompiledSelector.filter(driver, new ArrayList<WebElement>(Arrays.asList(parent)));
				boolean parentMatchesTheFilter = !pf.isEmpty();
				if (!parentMatchesTheFilter) {
					// this element's parent is NOT ok, dont keep it
					iterator.remove();
				}
			}
			return elements;
		}
	}

}