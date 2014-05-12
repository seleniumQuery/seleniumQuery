package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class OnlyOfTypePseudoClass implements PseudoClass {
	
	private static final OnlyOfTypePseudoClass instance = new OnlyOfTypePseudoClass();
	public static OnlyOfTypePseudoClass getInstance() {
		return instance;
	}
	private OnlyOfTypePseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "only-of-type".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		String tagName = element.getTagName();
		return driver.findElements(By.tagName(tagName)).size() == 1;
	}
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return new CompiledSelector(":only-of-type", "ONLY OF TYPE PSEUDO");
	}
	
}