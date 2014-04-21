package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class HiddenPseudoClass implements PseudoClass {
	
	private static final HiddenPseudoClass instance = new HiddenPseudoClass();
	public static HiddenPseudoClass getInstance() {
		return instance;
	}
	private HiddenPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "hidden".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return !element.isDisplayed();
	}
	
}