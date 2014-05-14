package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.SelectorEvaluator;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class NotPseudoClass implements PseudoClass {
	
	private static final NotPseudoClass instance = new NotPseudoClass();
	public static NotPseudoClass getInstance() {
		return instance;
	}
	private NotPseudoClass() { }
	
	private static final int NOT_BRACE_LENGTH = "not(".length();
	private static final int END_BRACE_LENGTH = ")".length();
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("not\\(.*\\)");
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		String selector = pseudoClassValue.substring(NOT_BRACE_LENGTH, pseudoClassValue.length() - END_BRACE_LENGTH);
		return !SelectorEvaluator.is(driver, element, selector);
	}
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:not

		// we never consider not to be supported natively, as it may contains not supported selectors in it
		// until we find a way to check if those selectors inside it are supported, we filter.
		
//		if (DriverSupportMap.getInstance().supportsNatively(driver, ":not(div)")) {
//			return CompiledSelector.createNoFilterSelector(":not("+pseudoClassValue+")");
//		}
		SqCSSFilter notPseudoClassFilter = new PseudoClassFilter(getInstance(), selectorThisConditionShouldApply,
															pseudoClassValue);
		return CompiledSelector.createFilterOnlySelector(notPseudoClassFilter);
	}
	
}