package io.github.seleniumquery.by.evaluator.conditionals;

import io.github.seleniumquery.by.evaluator.CSSCondition;

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
	public boolean is(WebDriver driver, WebElement element, Selector selectorUpToThisPoint, T condition) {
		throw new RuntimeException("CSS condition "+condition.getClass().getSimpleName()+" of type "+type+" is invalid or not supported!");
	}
	
}