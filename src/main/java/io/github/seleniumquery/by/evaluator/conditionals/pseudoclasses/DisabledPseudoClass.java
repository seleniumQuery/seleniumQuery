package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class DisabledPseudoClass implements PseudoClass {

	private static final DisabledPseudoClass instance = new DisabledPseudoClass();
	public static DisabledPseudoClass getInstance() {
		return instance;
	}
	private DisabledPseudoClass() { }
	
	private static final String DISABLED_PSEUDO_CLASS_NO_COLON = "disabled";
	private static final String DISABLED_PSEUDO_CLASS = ":"+DISABLED_PSEUDO_CLASS_NO_COLON;

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return DISABLED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return !element.isEnabled();
	}

	private static final SqCSSFilter disabledPseudoClassFilter = new PseudoClassFilter(getInstance(), SELECTOR_NOT_USED,
			PSEUDO_CLASS_VALUE_NOT_USED);
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:enabled
		if (DriverSupportMap.getInstance().supportsNatively(driver, DISABLED_PSEUDO_CLASS)) {
			return CompiledSelector.createNoFilterSelector(DISABLED_PSEUDO_CLASS);
		}
		return CompiledSelector.createFilterOnlySelector(disabledPseudoClassFilter);
	}

}