package io.github.seleniumquery.by.evaluator.conditionals;

import java.util.Map;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.evaluator.SelectorEvaluator;
import io.github.seleniumquery.by.selector.CSSFilterUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SeleniumQueryCssCompiler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

public class ConditionalSelectorEvaluator implements CSSSelector<ConditionalSelector> {
	
	private static final ConditionalSelectorEvaluator instance = new ConditionalSelectorEvaluator();

	public static ConditionalSelectorEvaluator getInstance() {
		return instance;
	}
	
	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();
		return SelectorEvaluator.elementMatchesSelector(driver, element, stringMap, simpleSelector)
				&& isCondition(driver, element, stringMap, simpleSelector, condition);
	}
	
	boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector simpleSelector, Condition condition) {
		@SuppressWarnings("unchecked")
		CSSCondition<Condition> evaluator = (CSSCondition<Condition>) ConditionalEvaluatorFactory.getInstance().getSelector(condition);
		return evaluator.isCondition(driver, element, stringMap, simpleSelector, condition);
	}

	@Override
	public CompiledSelector compile(WebDriver driver, Map<String, String> stringMap, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();
		CompiledSelector compiledSelector = SeleniumQueryCssCompiler.compileSelector(driver, stringMap, simpleSelector);
		CompiledSelector compiledCondition = compileCondition(driver, stringMap, simpleSelector, condition);
		return CSSFilterUtils.combine(compiledSelector, compiledCondition);
	}

	CompiledSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, Condition condition) {
		@SuppressWarnings("unchecked")
		CSSCondition<Condition> evaluator = (CSSCondition<Condition>) ConditionalEvaluatorFactory.getInstance().getSelector(condition);
		return evaluator.compileCondition(driver, stringMap, simpleSelector, condition);
	}

}