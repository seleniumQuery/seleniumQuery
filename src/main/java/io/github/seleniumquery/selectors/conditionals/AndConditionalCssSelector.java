package io.github.seleniumquery.selectors.conditionals;

import io.github.seleniumquery.by.selector.xpath.XPathExpression;
import io.github.seleniumquery.by.css.CssConditionalSelector;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

import com.steadystate.css.parser.selectors.ConditionalSelectorImpl;

/**
 * E.firstCondition.secondCondition
 *
 * @see {@link Condition#SAC_AND_CONDITION}
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class AndConditionalCssSelector implements CssConditionalSelector<CombinatorCondition> {

	private ConditionalCssSelector conditionalEvaluator;
	
	public AndConditionalCssSelector(ConditionalCssSelector conditionalEvaluator) {
		this.conditionalEvaluator = conditionalEvaluator;
	}

	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
																					(SimpleSelector) selectorUpToThisPoint,
																						combinatorCondition.getFirstCondition());
		
		return conditionalEvaluator.isCondition(driver, element, stringMap, selectorUpToThisPoint, combinatorCondition.getFirstCondition())
		    && conditionalEvaluator.isCondition(driver, element, stringMap, selectorUpToThisPointPlusFirstCondition, combinatorCondition.getSecondCondition());
	}

	@Override
	public XPathExpression conditionToXPath(Map<String, String> stringMap, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
				(SimpleSelector) selectorUpToThisPoint,
				combinatorCondition.getFirstCondition());
		
		XPathExpression compiledFirst = conditionalEvaluator.conditionToXPath(stringMap, selectorUpToThisPoint, combinatorCondition.getFirstCondition());
		XPathExpression compiledSecond = conditionalEvaluator.conditionToXPath(stringMap, selectorUpToThisPointPlusFirstCondition, combinatorCondition.getSecondCondition());
		return compiledFirst.combine(compiledSecond);
	}

}