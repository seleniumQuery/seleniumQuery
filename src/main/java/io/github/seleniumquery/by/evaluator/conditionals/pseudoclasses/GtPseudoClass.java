package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.SeleniumQueryBy;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class GtPseudoClass implements PseudoClass {

	private static final GtPseudoClass instance = new GtPseudoClass();
	public static GtPseudoClass getInstance() {
		return instance;
	}
	private GtPseudoClass() { }

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("gt\\(.*\\)");
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		String gtIndex = pseudoClassValue.substring(3, pseudoClassValue.length() - 1);
		if (!gtIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :gt() pseudo-class requires an integer but got: " + pseudoClassValue);
		}
		if (gtIndex.charAt(0) == '+') {
			gtIndex = gtIndex.substring(1);
		}
		int index = Integer.valueOf(gtIndex);
		
		return GtPseudoClass.isGt(driver, element, selectorThisConditionShouldApply, index);
	}
	
	private static boolean isGt(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, int wantedIndex) {
		List<WebElement> elements = driver.findElements(SeleniumQueryBy.byEnhancedSelector(selectorThisConditionShouldApply.toString()));
		if (elements.isEmpty()) {
			return false;
		}
		int actuallyWantedIndex = wantedIndex;
		if (wantedIndex < 0) {
			actuallyWantedIndex = elements.size() + wantedIndex;
		}
		
		if (elements.size() <= actuallyWantedIndex) {
			return false;
		}
		int indexFound = elements.indexOf(element);
		if (indexFound == -1) {
			return false;
		}
		return indexFound > actuallyWantedIndex;
	}

	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		SqCSSFilter gtPseudoClassFilter = new PseudoClassFilter(getInstance(), selectorThisConditionShouldApply, pseudoClassValue);
		return CompiledSelector.createFilterOnlySelector(gtPseudoClassFilter);
	}

}