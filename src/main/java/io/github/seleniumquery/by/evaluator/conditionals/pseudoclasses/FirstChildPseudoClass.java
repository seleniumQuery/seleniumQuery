package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.SelectorUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class FirstChildPseudoClass implements PseudoClass {
	
	private static final FirstChildPseudoClass instance = new FirstChildPseudoClass();
	public static FirstChildPseudoClass getInstance() {
		return instance;
	}
	private FirstChildPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "first-child".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		WebElement parent = SelectorUtils.parent(element);
		// parent is null when element is <HTML>
		if (parent == null) {
			return false;
		}
		return SelectorUtils.itselfWithSiblings(element).get(0).equals(element);
	}
	
}