package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.SelectorEvaluator;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NotPseudoClass implements PseudoClass {
	
	private static final NotPseudoClass instance = new NotPseudoClass();
	public static NotPseudoClass getInstance() {
		return instance;
	}
	private NotPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("not-sq\\(.*\\)");
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String notSelector = pseudoClassSelector.getPseudoClassContent();
		return !SelectorEvaluator.elementMatchesStringSelector(driver, element, notSelector);
	}
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:not

		// we never consider not to be supported natively, as it may contains not supported selectors in it
		// until we find a way to check if those selectors inside it are supported, we filter.
		
//		if (DriverSupportMap.getInstance().supportsNatively(driver, ":not(div)")) {
//			return CompiledSelector.createNoFilterSelector(":not("+pseudoClassValue+")");
//		}
		SqCSSFilter notPseudoClassFilter = new PseudoClassFilter(getInstance(), pseudoClassSelector);
		return CompiledSelector.createFilterOnlySelector(notPseudoClassFilter);
	}
	
}