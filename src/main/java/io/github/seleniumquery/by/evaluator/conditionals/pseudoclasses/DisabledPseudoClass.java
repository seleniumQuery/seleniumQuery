package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class DisabledPseudoClass implements PseudoClass {
	
	private static final DisabledPseudoClass instance = new DisabledPseudoClass();
	public static DisabledPseudoClass getInstance() {
		return instance;
	}
	private DisabledPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "disabled".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return !element.isEnabled();
	}
	
}