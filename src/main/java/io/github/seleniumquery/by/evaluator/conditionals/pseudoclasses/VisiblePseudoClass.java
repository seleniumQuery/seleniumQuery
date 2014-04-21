package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class VisiblePseudoClass implements PseudoClass {
	
	private static final VisiblePseudoClass instance = new VisiblePseudoClass();
	public static VisiblePseudoClass getInstance() {
		return instance;
	}
	private VisiblePseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "visible".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return element.isDisplayed();
	}
	
}