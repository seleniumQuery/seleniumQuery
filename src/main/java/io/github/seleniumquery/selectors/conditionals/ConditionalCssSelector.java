package io.github.seleniumquery.selectors.conditionals;

import io.github.seleniumquery.selectorcss.CssConditionalSelector;
import io.github.seleniumquery.selectorcss.CssSelector;
import io.github.seleniumquery.selectorcss.CssSelectorMatcherService;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorCompilerService;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

public class ConditionalCssSelector implements CssSelector<ConditionalSelector> {
	
	private static final ConditionalCssSelector instance = new ConditionalCssSelector();
	public static ConditionalCssSelector getInstance() {
		return instance;
	}
	
	@Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();
		return CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, simpleSelector)
				&& isCondition(driver, element, stringMap, simpleSelector, condition);
	}
	
	@Override
	public XPathExpression toXPath(Map<String, String> stringMap, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();
		XPathExpression compiledSelector = XPathSelectorCompilerService.compileSelector(stringMap, simpleSelector);
		XPathExpression compiledCondition = conditionToXPath(stringMap, simpleSelector, condition);
		return compiledSelector.combine(compiledCondition);
	}
	
	/**
	 * Gets the given condition's CssSelector and tests if the element matches it. 
	 */
	boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector simpleSelector, Condition condition) {
		@SuppressWarnings("unchecked")
		CssConditionalSelector<Condition> evaluator = (CssConditionalSelector<Condition>) ConditionalCssSelectorFactory.getInstance().getSelector(condition);
		return evaluator.isCondition(driver, element, stringMap, simpleSelector, condition);
	}

	XPathExpression conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, Condition condition) {
		@SuppressWarnings("unchecked")
		CssConditionalSelector<Condition> evaluator = (CssConditionalSelector<Condition>) ConditionalCssSelectorFactory.getInstance().getSelector(condition);
		return evaluator.conditionToXPath(stringMap, simpleSelector, condition);
	}

}