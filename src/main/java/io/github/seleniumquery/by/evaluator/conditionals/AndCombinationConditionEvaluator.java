package io.github.seleniumquery.by.evaluator.conditionals;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.selector.CSSFilterUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.CombinatorCondition;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

import com.steadystate.css.parser.selectors.ConditionalSelectorImpl;

public class AndCombinationConditionEvaluator implements CSSCondition<CombinatorCondition> {

	private static final AndCombinationConditionEvaluator instance = new AndCombinationConditionEvaluator();

	public static AndCombinationConditionEvaluator getInstance() {
		return instance;
	}

	private ConditionalSelectorEvaluator conditionalEvaluator = ConditionalSelectorEvaluator.getInstance();
	
	/**
	 * E.firstCondition.secondCondition
	 * 
	 * @see {@link Condition#SAC_AND_CONDITION}
	 */
	@Override
	public boolean is(WebDriver driver, WebElement element, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
																					(SimpleSelector) selectorUpToThisPoint,
																						combinatorCondition.getFirstCondition());
		
		return conditionalEvaluator.isCondition(driver, element, selectorUpToThisPoint, combinatorCondition.getFirstCondition())
		    && conditionalEvaluator.isCondition(driver, element, selectorUpToThisPointPlusFirstCondition, combinatorCondition.getSecondCondition());
	}

	@Override
	public CompiledSelector compile(WebDriver driver, Selector selectorUpToThisPoint, CombinatorCondition combinatorCondition) {
		ConditionalSelectorImpl selectorUpToThisPointPlusFirstCondition = new ConditionalSelectorImpl(
																					(SimpleSelector) selectorUpToThisPoint,
																						combinatorCondition.getFirstCondition());

		CompiledSelector compiledFirst = conditionalEvaluator.compileCondition(driver, selectorUpToThisPoint, combinatorCondition.getFirstCondition());
		CompiledSelector compiledSecond = conditionalEvaluator.compileCondition(driver, selectorUpToThisPointPlusFirstCondition, combinatorCondition.getSecondCondition());
		return CSSFilterUtils.combine(compiledFirst, compiledSecond);
	}
	
}