package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DisabledPseudoClass implements PseudoClass {

	private static final DisabledPseudoClass instance = new DisabledPseudoClass();
	public static DisabledPseudoClass getInstance() {
		return instance;
	}
	private DisabledPseudoClass() { }
	
	private static final String DISABLED_PSEUDO_CLASS_NO_COLON = "disabled";
	private static final String DISABLED_PSEUDO_CLASS = ":"+DISABLED_PSEUDO_CLASS_NO_COLON;
	
	public static final List<String> DISABLEABLE_TAGS = Arrays.asList("input", "button", "optgroup", "option", "select", "textarea");

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return DISABLED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return !element.isEnabled() && DISABLEABLE_TAGS.contains(element.getTagName());
	}

	private static final SqCSSFilter disabledPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:enabled
		if (DriverSupportMap.getInstance().supportsNatively(driver, DISABLED_PSEUDO_CLASS)) {
			return CompiledSelector.createNoFilterSelector(DISABLED_PSEUDO_CLASS);
		}
		return CompiledSelector.createFilterOnlySelector(disabledPseudoClassFilter);
	}

}