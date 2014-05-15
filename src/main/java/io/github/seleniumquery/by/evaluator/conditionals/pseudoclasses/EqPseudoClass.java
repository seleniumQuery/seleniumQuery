package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.SeleniumQueryBy;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

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
		if (!eqIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :eq() pseudo-class requires an integer but got: " + pseudoClassValue);
		}
		if (eqIndex.charAt(0) == '+') {
			eqIndex = eqIndex.substring(1);
		}
		int index = Integer.valueOf(eqIndex);
		
		return EqPseudoClass.isEq(driver, element, selectorThisConditionShouldApply, index);
	}
	
	static boolean isEq(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, int index) {
		List<WebElement> elements = driver.findElements(SeleniumQueryBy.byEnhancedSelector(selectorThisConditionShouldApply.toString()));
		if (index < 0) {
			return elements.size() >= -index && elements.get(elements.size() + index).equals(element);
		}
		return elements.size() > index && elements.get(index).equals(element);
	}

	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		SqCSSFilter eqPseudoClassFilter = new PseudoClassFilter(getInstance(), selectorThisConditionShouldApply, pseudoClassValue);
		return CompiledSelector.createFilterOnlySelector(eqPseudoClassFilter);
	}

}