package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.SeleniumQueryBy;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class LtPseudoClass implements PseudoClass {

	private static final LtPseudoClass instance = new LtPseudoClass();
	public static LtPseudoClass getInstance() {
		return instance;
	}
	private LtPseudoClass() { }

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("lt\\(.*\\)");
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		String ltIndex = pseudoClassValue.substring(3, pseudoClassValue.length() - 1);
		if (!ltIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :lt() pseudo-class requires an integer but got: " + pseudoClassValue);
		}
		if (ltIndex.charAt(0) == '+') {
			ltIndex = ltIndex.substring(1);
		}
		int index = Integer.valueOf(ltIndex);
		
		return LtPseudoClass.isLt(driver, element, selectorThisConditionShouldApply, index);
	}
	
	private static boolean isLt(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, int wantedIndex) {
		if (wantedIndex == 0) {
			return false;
		}
		List<WebElement> elements = driver.findElements(SeleniumQueryBy.byEnhancedSelector(selectorThisConditionShouldApply.toString()));
		if (elements.isEmpty()) {
			return false;
		}
		int actuallyWantedIndex = wantedIndex;
		if (wantedIndex < 0) {
			actuallyWantedIndex = elements.size() + wantedIndex;
		}
		
		if (elements.size() <= actuallyWantedIndex) {
			return true;
		}
		int indexFound = elements.indexOf(element);
		if (indexFound == -1) {
			return false;
		}
		return indexFound < actuallyWantedIndex;
	}

	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		SqCSSFilter ltPseudoClassFilter = new PseudoClassFilter(getInstance(), selectorThisConditionShouldApply, pseudoClassValue);
		return CompiledSelector.createFilterOnlySelector(ltPseudoClassFilter);
	}

}