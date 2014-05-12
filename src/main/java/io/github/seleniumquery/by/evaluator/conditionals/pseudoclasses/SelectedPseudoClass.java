package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class SelectedPseudoClass implements PseudoClass {
	
	private static final SelectedPseudoClass instance = new SelectedPseudoClass();
	public static SelectedPseudoClass getInstance() {
		return instance;
	}
	private SelectedPseudoClass() { }
	
	private static final String OPTION_TAG = "option";

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "selected".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return isSelected(element);
	}
	
	public boolean isSelected(WebElement element) {
		return element.getTagName().equals(OPTION_TAG) && element.isSelected();
	}
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return new CompiledSelector(":selected", "SELECTED PSEUDO");
	}

}