package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class FirstPseudoClass implements PseudoClass {

	private static final FirstPseudoClass instance = new FirstPseudoClass();
	public static FirstPseudoClass getInstance() {
		return instance;
	}
	private FirstPseudoClass() { }

	private static final String FIRST_PSEUDO_CLASS_NO_COLON = "first";
	
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FIRST_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return EqPseudoClass.isEq(driver, element, selectorThisConditionShouldApply, 0);
	}

	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		SqCSSFilter firstPseudoClassFilter = new PseudoClassFilter(getInstance(), selectorThisConditionShouldApply, pseudoClassValue);
		return CompiledSelector.createFilterOnlySelector(firstPseudoClassFilter);
	}

}