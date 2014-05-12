package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;

import org.apache.commons.lang3.StringEscapeUtils;
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
		String escapedTextToContain = StringEscapeUtils.unescapeJava(textToContain);
		return element.getText().contains(escapedTextToContain);
	}
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return new CompiledSelector(":contains("+pseudoClassValue+")", "CONTAINS PSEUDO");
	}
	
}