package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class RootPseudoClass implements PseudoClass {
	
	private static final RootPseudoClass instance = new RootPseudoClass();
	public static RootPseudoClass getInstance() {
		return instance;
	}
	private RootPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "root".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return element.getTagName().equals("html");
	}
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return new CompiledSelector(":root", "ROOT PSEUDO");
	}
	
}