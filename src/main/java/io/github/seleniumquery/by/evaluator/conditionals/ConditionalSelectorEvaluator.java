package io.github.seleniumquery.by.evaluator.conditionals;

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
	public boolean is(WebDriver driver, WebElement element, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();
		return SelectorEvaluator.is(driver, element, simpleSelector)
				&& isCondition(driver, element, simpleSelector, condition);
	}
	
	@SuppressWarnings("unchecked")
	boolean isCondition(WebDriver driver, WebElement element, Selector simpleSelector, Condition condition) {
		CSSCondition<Condition> evaluator = (CSSCondition<Condition>) ConditionalEvaluatorFactory.getInstance().getSelector(condition);
		return evaluator.is(driver, element, simpleSelector, condition);
	}

	@Override
	public CompiledSelector compile(WebDriver driver, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();
		CompiledSelector compiledSelector = SeleniumQueryCssCompiler.compileSelector(driver, simpleSelector);
		CompiledSelector compiledCondition = compileCondition(driver, simpleSelector, condition);
		return CSSFilterUtils.combine(compiledSelector, compiledCondition);
	}

	@SuppressWarnings("unchecked")
	CompiledSelector compileCondition(WebDriver driver, Selector simpleSelector, Condition condition) {
		CSSCondition<Condition> evaluator = (CSSCondition<Condition>) ConditionalEvaluatorFactory.getInstance().getSelector(condition);
		return evaluator.compile(driver, simpleSelector, condition);
	}

}