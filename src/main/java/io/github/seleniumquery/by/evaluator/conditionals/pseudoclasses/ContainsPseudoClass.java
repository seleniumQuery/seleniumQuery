package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class ContainsPseudoClass implements PseudoClass {
	
	private static final ContainsPseudoClass instance = new ContainsPseudoClass();
	public static ContainsPseudoClass getInstance() {
		return instance;
	}
	private ContainsPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("contains\\(.*\\)");
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		String textToContain = pseudoClassValue.substring(9, pseudoClassValue.length() - 1);
		return element.getText().contains(textToContain);
	}
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		SqCSSFilter containsPseudoClassFilter = new PseudoClassFilter(getInstance(), PseudoClassFilter.SELECTOR_NOT_USED,
																				pseudoClassValue);
		return CompiledSelector.createFilterOnlySelector(containsPseudoClassFilter);
	}
	
}