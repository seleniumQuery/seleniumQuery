package io.github.seleniumquery.selectors.conditionals;

import java.util.Map;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssConditionalSelector;
import io.github.seleniumquery.selector.CssFilterUtils;
import io.github.seleniumquery.selector.SqXPathSelector;
import io.github.seleniumquery.selector.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

import com.steadystate.css.parser.selectors.ConditionalSelectorImpl;

public class AndConditionalCssSelector implements CssConditionalSelector<CombinatorCondition> {

	private static final AndConditionalCssSelector instance = new AndConditionalCssSelector();

	public static AndConditionalCssSelector getInstance() {
		return instance;
	}

	private ConditionalCssSelector conditionalEvaluator = ConditionalCssSelector.getInstance();
	
	/**
	 * E.firstCondition.secondCondition
	 * 
	 * @see {@link Condition#SAC_AND_CONDITION}
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
																					(SimpleSelector) selectorUpToThisPoint,
																						combinatorCondition.getFirstCondition());
		
		return conditionalEvaluator.isCondition(driver, element, stringMap, selectorUpToThisPoint, combinatorCondition.getFirstCondition())
		    && conditionalEvaluator.isCondition(driver, element, stringMap, selectorUpToThisPointPlusFirstCondition, combinatorCondition.getSecondCondition());
	}

	@Override
	public CompiledCssSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
																					(SimpleSelector) selectorUpToThisPoint,
																						combinatorCondition.getFirstCondition());

		CompiledCssSelector compiledFirst = conditionalEvaluator.compileCondition(driver, stringMap, selectorUpToThisPoint, combinatorCondition.getFirstCondition());
		CompiledCssSelector compiledSecond = conditionalEvaluator.compileCondition(driver, stringMap, selectorUpToThisPointPlusFirstCondition, combinatorCondition.getSecondCondition());
		return CssFilterUtils.combine(compiledFirst, compiledSecond);
	}
	
	@Override
	public SqXPathSelector conditionToXPath(WebDriver driver, Map<String, String> stringMap, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
				(SimpleSelector) selectorUpToThisPoint,
				combinatorCondition.getFirstCondition());
		
		SqXPathSelector compiledFirst = conditionalEvaluator.conditionToXPath(driver, stringMap, selectorUpToThisPoint, combinatorCondition.getFirstCondition());
		SqXPathSelector compiledSecond = conditionalEvaluator.conditionToXPath(driver, stringMap, selectorUpToThisPointPlusFirstCondition, combinatorCondition.getSecondCondition());
		return compiledFirst.combine(compiledSecond);
	}

}