package io.github.seleniumquery.by.css.conditionals;

import io.github.seleniumquery.by.css.CssConditionalSelector;
import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorMatcherService;
import io.github.seleniumquery.by.xpath.XPathComponentCompilerService;
import io.github.seleniumquery.by.xpath.component.ConditionComponent;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

import java.util.Map;


public class ConditionalCssSelector implements CssSelector<ConditionalSelector, TagComponent> {

    private final ConditionalCssSelectorFactory conditionalCssSelectorFactory = new ConditionalCssSelectorFactory(this);

    @Override
	public boolean is(WebDriver driver, WebElement element, Map<String, String> stringMap, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();
		return CssSelectorMatcherService.elementMatchesSelector(driver, element, stringMap, simpleSelector)
				&& isCondition(driver, element, stringMap, simpleSelector, condition);
	}
	
	@Override
	public TagComponent toXPath(Map<String, String> stringMap, ConditionalSelector conditionalSelector) {
		Condition condition = conditionalSelector.getCondition();
		SimpleSelector simpleSelector = conditionalSelector.getSimpleSelector();
		TagComponent tagComponent = XPathComponentCompilerService.compileSelector(stringMap, simpleSelector);
		ConditionComponent compiledCondition = conditionToXPath(stringMap, simpleSelector, condition);
		return tagComponent.cloneAndCombineTo(compiledCondition);
	}
	
	/**
	 * Gets the given condition's CssSelector and tests if the element matches it. 
	 */
	boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector simpleSelector, Condition condition) {
		@SuppressWarnings("unchecked")
		CssConditionalSelector<Condition, ConditionComponent> evaluator = (CssConditionalSelector<Condition, ConditionComponent>) conditionalCssSelectorFactory.getSelector(condition);
		return evaluator.isCondition(driver, element, stringMap, simpleSelector, condition);
	}

	ConditionComponent conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, Condition condition) {
		@SuppressWarnings("unchecked")
		CssConditionalSelector<Condition, ConditionComponent> evaluator = (CssConditionalSelector<Condition, ConditionComponent>) conditionalCssSelectorFactory.getSelector(condition);
		return evaluator.conditionToXPath(stringMap, simpleSelector, condition);
	}

}