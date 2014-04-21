package io.github.seleniumquery.by.evaluator.combinators;

import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.evaluator.SelectorEvaluator;
import io.github.seleniumquery.by.evaluator.SelectorUtils;

import java.util.List;

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
	public boolean is(WebDriver driver, WebElement element, SiblingSelector siblingSelector) {
		boolean elementMatchesSelectorSecondPart = SelectorEvaluator.is(driver, element, siblingSelector.getSiblingSelector());
		if (!elementMatchesSelectorSecondPart) {
			return false;
		}
		
		List<WebElement> previousSiblings = SelectorUtils.getPreviousSiblings(element);
		for (WebElement previousSibling : previousSiblings) {
			boolean previousSiblingMatchesSelectorFirstPart = SelectorEvaluator.is(driver, previousSibling, siblingSelector.getSelector());
			if (previousSiblingMatchesSelectorFirstPart) {
				return true;
			}
		}
		return false;
	}

}