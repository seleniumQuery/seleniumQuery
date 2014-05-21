package io.github.seleniumquery.selectors.conditionals;

import java.util.Map;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssConditionalSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;

/**
 * Represents an unknown condition type.
 */
public class UnknownConditionalCssSelector<T extends Condition> implements CssConditionalSelector<T> {
	
	private short type;
	
	public UnknownConditionalCssSelector(short type) {
		this.type = type;
	}

	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, T condition) {
		throw new RuntimeException("CSS condition "+condition.getClass().getSimpleName()+" of type "+type+" is invalid or not supported!");
	}

	@Override
	public CompiledCssSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, T condition) {
		// we dont know what to do, just pass along hoping the browser will
		return CompiledCssSelector.createNoFilterSelector(condition.toString());
	}
	
}