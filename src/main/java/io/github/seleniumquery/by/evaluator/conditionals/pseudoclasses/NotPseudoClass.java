package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.SelectorEvaluator;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class NotPseudoClass implements PseudoClass {
	
	private static final NotPseudoClass instance = new NotPseudoClass();
	public static NotPseudoClass getInstance() {
		return instance;
	}
	private NotPseudoClass() { }
	
	private static final int NOT_BRACE_LENGTH = "not(".length();
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("not\\(.*\\)");
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		String selector = pseudoClassValue.substring(NOT_BRACE_LENGTH, pseudoClassValue.length() - 1);
		return !SelectorEvaluator.is(driver, element, selector);
	}
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return new CompiledSelector(":not("+pseudoClassValue+")", "NOT PSEUDO");
	}
	
}