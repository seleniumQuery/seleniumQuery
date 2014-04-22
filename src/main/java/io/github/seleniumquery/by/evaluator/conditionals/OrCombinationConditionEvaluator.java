package io.github.seleniumquery.by.evaluator.conditionals;

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
	public boolean is(WebDriver driver, WebElement element, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		throw new RuntimeException("IT IS USED!");
//		ConditionalSelectorEvaluator conditionalEvaluator = ConditionalSelectorEvaluator.getInstance();
//		return conditionalEvaluator.isCondition(driver, element, selectorUpToThisPoint, combinatorCondition.getFirstCondition())
//		    || conditionalEvaluator.isCondition(driver, element, selectorUpToThisPoint, combinatorCondition.getSecondCondition());
	}

	@Override
	public CompiledSelector compile(WebDriver driver, Selector simpleSelector, CombinatorCondition condition) {
		throw new RuntimeException("IT IS USED!");
//		return new CompiledSelector(condition.toString(), CSSFilter.FILTER_NOTHING);
	}

}