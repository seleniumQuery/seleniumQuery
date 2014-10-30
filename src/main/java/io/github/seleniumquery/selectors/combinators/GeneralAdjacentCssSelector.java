package io.github.seleniumquery.selectors.combinators;

import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selector.xpath.SqSelectorKind;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorCompilerService;
import io.github.seleniumquery.selectorcss.CssSelector;
import io.github.seleniumquery.selectorcss.CssSelectorMatcherService;

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
	public XPathExpression toXPath(Map<String, String> stringMap, SiblingSelector siblingSelector) {
		XPathExpression previousElementCompiled = XPathSelectorCompilerService.compileSelector(stringMap, siblingSelector.getSelector());
		XPathExpression siblingElementCompiled = XPathSelectorCompilerService.compileSelector(stringMap, siblingSelector.getSiblingSelector());
		
		siblingElementCompiled.kind = SqSelectorKind.ADJACENT;
		return previousElementCompiled.combine(siblingElementCompiled);
	}

}