package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;
import io.github.seleniumquery.selector.DriverSupportService;

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

	private static final CssFilter disabledPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:enabled
		if (DriverSupportService.getInstance().supportsNatively(driver, DISABLED_PSEUDO_CLASS)) {
			return CompiledCssSelector.createNoFilterSelector(DISABLED_PSEUDO_CLASS);
		}
		return CompiledCssSelector.createFilterOnlySelector(disabledPseudoClassFilter);
	}

}