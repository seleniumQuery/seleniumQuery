package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class EnabledPseudoClass implements PseudoClass {
	
	private static final EnabledPseudoClass instance = new EnabledPseudoClass();
	public static EnabledPseudoClass getInstance() {
		return instance;
	}
	private EnabledPseudoClass() { }
	
	private static final String ENABLED_PSEUDO_CLASS_NO_COLON = "enabled";
	private static final String ENABLED_PSEUDO_CLASS = ":"+ENABLED_PSEUDO_CLASS_NO_COLON;
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ENABLED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return element.isEnabled();
	}
	
	private static final SqCSSFilter enabledPseudoClassFilter = new PseudoClassFilter(getInstance(),
																		SELECTOR_NOT_USED, PSEUDO_CLASS_VALUE_NOT_USED);
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:enabled
		if (DriverSupportMap.getInstance().supportsNatively(driver, ENABLED_PSEUDO_CLASS)) {
			return CompiledSelector.createNoFilterSelector(ENABLED_PSEUDO_CLASS);
		}
		return CompiledSelector.createFilterOnlySelector(enabledPseudoClassFilter);
	}
	
}