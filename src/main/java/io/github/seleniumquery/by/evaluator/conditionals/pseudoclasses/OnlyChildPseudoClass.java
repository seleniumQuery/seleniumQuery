package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class OnlyChildPseudoClass implements PseudoClass {
	
	private static final OnlyChildPseudoClass instance = new OnlyChildPseudoClass();
	public static OnlyChildPseudoClass getInstance() {
		return instance;
	}
	private OnlyChildPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "only-child".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		WebElement parent = SelectorUtils.parent(element);
		if (parent == null // parent is null when element is <HTML>
				|| parent.getTagName().equals("html")
				|| parent.getTagName().equals("body")
				|| parent.getTagName().equals("head")) {
			// I have tested and :only-child never worked direct children of those
			return false;
		}
		return SelectorUtils.itselfWithSiblings(element).size() == 1;
	}
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return new CompiledSelector(":only-child", "ONLYCHILD PSEUDO");
	}
	
}