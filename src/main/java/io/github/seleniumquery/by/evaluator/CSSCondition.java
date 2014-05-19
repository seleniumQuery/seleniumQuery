package io.github.seleniumquery.by.evaluator;

import java.util.Map;

import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;

public interface CSSCondition<T extends Condition> {

	boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selector, T condtition);

	CompiledSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector selector, T condition);
	
}