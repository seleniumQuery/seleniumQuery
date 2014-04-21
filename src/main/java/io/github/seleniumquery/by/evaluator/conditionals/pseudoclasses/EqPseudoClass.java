package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.SeleniumQueryBy;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class EqPseudoClass implements PseudoClass {
	
	private static final EqPseudoClass instance = new EqPseudoClass();
	public static EqPseudoClass getInstance() {
		return instance;
	}
	private EqPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("eq\\(.*\\)");
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		String eqIndex = pseudoClassValue.substring(3, pseudoClassValue.length() - 1);
		if (!eqIndex.matches("\\d+")) {
			throw new RuntimeException("The :eq() pseudo-class requires an integer but got: " + pseudoClassValue);
		}
		Integer index = Integer.valueOf(eqIndex);
		List<WebElement> elements = driver.findElements(SeleniumQueryBy.byEnhancedSelector(selectorThisConditionShouldApply.toString()));
		return elements.size() > index && elements.get(index).equals(element);
	}
	
}