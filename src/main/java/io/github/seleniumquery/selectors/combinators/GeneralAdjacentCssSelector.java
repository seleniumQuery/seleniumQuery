package io.github.seleniumquery.selectors.combinators;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorcss.CssSelector;
import io.github.seleniumquery.selectorcss.CssSelectorCompilerService;
import io.github.seleniumquery.selectorcss.CssSelectorMatcherService;
import io.github.seleniumquery.selectorxpath.SqSelectorKind;
import io.github.seleniumquery.selectorxpath.SqXPathSelector;
import io.github.seleniumquery.selectorxpath.XPathSelectorCompilerService;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.SiblingSelector;

public class GeneralAdjacentCssSelector implements CssSelector<SiblingSelector> {

	private static final GeneralAdjacentCssSelector instance = new GeneralAdjacentCssSelector();

	public static GeneralAdjacentCssSelector getInstance() {
		return instance;
	}
	
	private GeneralAdjacentCssSelector() { }

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
		boolean elementMatchesSelectorSecondPart = CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, siblingSelector.getSiblingSelector());
		if (!elementMatchesSelectorSecondPart) {
			return false;
		}
		
		List<WebElement> previousSiblings = SelectorUtils.getPreviousSiblings(element);
		for (WebElement previousSibling : previousSiblings) {
			boolean previousSiblingMatchesSelectorFirstPart = CssSelectorMatcherService.elementMatchesSelector(driver, previousSibling, stringMap, siblingSelector.getSelector());
			if (previousSiblingMatchesSelectorFirstPart) {
				return true;
			}
		}
		return false;
	}

	@Override
	public CompiledCssSelector compile(WebDriver driver, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		CompiledCssSelector previousElementCompiled = CssSelectorCompilerService.compileSelector(driver, stringMap, siblingSelector.getSelector());
		CompiledCssSelector siblingElementCompiled = CssSelectorCompilerService.compileSelector(driver, stringMap, siblingSelector.getSiblingSelector());
		
		ElementFilter generalAdjacentFilter = new GeneralAdjacentFilter(previousElementCompiled, siblingElementCompiled);
		return new CompiledCssSelector(previousElementCompiled.getCssSelector()+"~"+siblingElementCompiled.getCssSelector(),
										generalAdjacentFilter);
	}
	
	private static final class GeneralAdjacentFilter implements ElementFilter {
		private final CompiledCssSelector siblingsCompiledSelector;
		private final CompiledCssSelector elementCompiledSelector;
		
		private GeneralAdjacentFilter(CompiledCssSelector previousElementCompiled, CompiledCssSelector siblingElementCompiled) {
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

	@Override
	public SqXPathSelector toXPath(WebDriver driver, Map<String, String> stringMap, SiblingSelector siblingSelector) {
		SqXPathSelector previousElementCompiled = XPathSelectorCompilerService.compileSelector(driver, stringMap, siblingSelector.getSelector());
		SqXPathSelector siblingElementCompiled = XPathSelectorCompilerService.compileSelector(driver, stringMap, siblingSelector.getSiblingSelector());
		
		siblingElementCompiled.kind = SqSelectorKind.ADJACENT;
		return previousElementCompiled.combine(siblingElementCompiled);
	}

}