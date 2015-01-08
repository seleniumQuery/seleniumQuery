package io.github.seleniumquery.by.css;

import io.github.seleniumquery.by.xpath.component.ConditionComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;

import java.util.Map;

public interface CssConditionalSelector<T extends Condition, C extends ConditionComponent> {

	boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selector, T condtition);

	C conditionToXPath(Map<String, String> stringMap, Selector simpleSelector, T condition);
	
}