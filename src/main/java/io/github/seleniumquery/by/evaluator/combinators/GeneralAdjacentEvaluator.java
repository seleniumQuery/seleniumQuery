package io.github.seleniumquery.by.evaluator.combinators;

import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.evaluator.SelectorEvaluator;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SeleniumQueryCssCompiler;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.SiblingSelector;

public class GeneralAdjacentEvaluator implements CSSSelector<SiblingSelector> {

	private static final GeneralAdjacentEvaluator instance = new GeneralAdjacentEvaluator();

	public static GeneralAdjacentEvaluator getInstance() {
		return instance;
	}
	
	private GeneralAdjacentEvaluator() { }

	/**
	 * http://www.w3.org/TR/css3-selectors/#general-sibling-combinators
	 * <div class="example">
	 *		<p>Example:</p>
	 *    
	 *		<pre>h1 ~ pre</pre>
	 *       
	 *      <p>represents a <code>pre</code> element following an <code>h1</code>. It is a correct and valid, but partial, description of:</p>
	 *      <pre>&lt;h1&gt;Definition of the function a&lt;/h1&gt;
	 *      &lt;p&gt;Function a(x) has to be applied to all figures in the table.&lt;/p&gt;
	 *      &lt;pre&gt;function a(x) = 12x/13.5&lt;/pre&gt;</pre>
	 * </div>
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		boolean elementMatchesSelectorSecondPart = SelectorEvaluator.elementMatchesSelector(driver, element, stringMap, siblingSelector.getSiblingSelector());
		if (!elementMatchesSelectorSecondPart) {
			return false;
		}
		
		List<WebElement> previousSiblings = SelectorUtils.getPreviousSiblings(element);
		for (WebElement previousSibling : previousSiblings) {
			boolean previousSiblingMatchesSelectorFirstPart = SelectorEvaluator.elementMatchesSelector(driver, previousSibling, stringMap, siblingSelector.getSelector());
			if (previousSiblingMatchesSelectorFirstPart) {
				return true;
			}
		}
		return false;
	}

	@Override
	public CompiledSelector compile(WebDriver driver, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		CompiledSelector previousElementCompiled = SeleniumQueryCssCompiler.compileSelector(driver, stringMap, siblingSelector.getSelector());
		CompiledSelector siblingElementCompiled = SeleniumQueryCssCompiler.compileSelector(driver, stringMap, siblingSelector.getSiblingSelector());
		
		SqCSSFilter generalAdjacentFilter = new GeneralAdjacentFilter(previousElementCompiled, siblingElementCompiled);
		return new CompiledSelector(previousElementCompiled.getCssSelector()+"~"+siblingElementCompiled.getCssSelector(),
										generalAdjacentFilter);
	}
	
	private static final class GeneralAdjacentFilter implements SqCSSFilter {
		private final CompiledSelector siblingsCompiledSelector;
		private final CompiledSelector elementCompiledSelector;
		
		private GeneralAdjacentFilter(CompiledSelector previousElementCompiled, CompiledSelector siblingElementCompiled) {
			this.siblingsCompiledSelector = previousElementCompiled;
			this.elementCompiledSelector = siblingElementCompiled;
		}
		
		@Override
		public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
			elements = elementCompiledSelector.filter(driver, elements);
			
			outerFor:for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
				WebElement element = iterator.next();
				
				List<WebElement> previousSiblings = SelectorUtils.getPreviousSiblings(element);
				
				previousSiblings = siblingsCompiledSelector.filter(driver, previousSiblings);
				
				for (WebElement previousSibling : previousSiblings) {
					boolean previousSiblingMatchesSelectorFirstPart = SelectorEvaluator.elementMatchesStringSelector(driver, previousSibling,
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

}