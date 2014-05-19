package io.github.seleniumquery.by.evaluator.conditionals;

import java.util.Map;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;

public class OrCombinationConditionEvaluator implements CSSCondition<CombinatorCondition> {

	private static final OrCombinationConditionEvaluator instance = new OrCombinationConditionEvaluator();

	public static OrCombinationConditionEvaluator getInstance() {
		return instance;
	}

	/**
	 * .firstCondition,.secondCondition
	 * 
	 * @see {@link Condition#SAC_OR_CONDITION}
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		// i dont think the CSS parser uses this. I kept it just in case...
		throw new RuntimeException("OrCombinationConditionEvaluator - IT IS USED!");
//		ConditionalSelectorEvaluator conditionalEvaluator = ConditionalSelectorEvaluator.getInstance();
//		return conditionalEvaluator.isCondition(driver, element, selectorUpToThisPoint, combinatorCondition.getFirstCondition())
//		    || conditionalEvaluator.isCondition(driver, element, selectorUpToThisPoint, combinatorCondition.getSecondCondition());
	}

	@Override
	public CompiledSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, CombinatorCondition condition) {
		// i dont think the CSS parser uses this. I kept it just in case...
		throw new RuntimeException("OrCombinationConditionEvaluator - IT IS USED!");
//		return new CompiledSelector(condition.toString(), CSSFilter.FILTER_NOTHING);
	}

}