package io.github.seleniumquery.by.evaluator.conditionals;

import java.util.Map;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;

public class UnknownConditionType<T extends Condition> implements CSSCondition<T> {
	
	private short type;
	
	public UnknownConditionType(short type) {
		this.type = type;
	}

	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, T condition) {
		throw new RuntimeException("CSS condition "+condition.getClass().getSimpleName()+" of type "+type+" is invalid or not supported!");
	}

	@Override
	public CompiledSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, T condition) {
		// we dont know what to do, just pass along hoping the browser will
		return CompiledSelector.createNoFilterSelector(condition.toString());
	}
	
}