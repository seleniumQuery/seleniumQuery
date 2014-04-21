package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class CheckedPseudoClass implements PseudoClass {
	
	private static final CheckedPseudoClass instance = new CheckedPseudoClass();
	public static CheckedPseudoClass getInstance() {
		return instance;
	}
	private CheckedPseudoClass() { }
	
	private static final List<String> CHECKED_ALLOWED_TAGS = Arrays.asList("input", "option");

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "checked".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return isChecked(element);
	}
	
	public boolean isChecked(WebElement element) {
		return CHECKED_ALLOWED_TAGS.contains(element.getTagName()) && element.isSelected();
	}

}