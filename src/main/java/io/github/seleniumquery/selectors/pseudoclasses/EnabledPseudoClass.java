package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;
import io.github.seleniumquery.selector.DriverSupportService;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EnabledPseudoClass implements PseudoClass {
	
	private static final EnabledPseudoClass instance = new EnabledPseudoClass();
	public static EnabledPseudoClass getInstance() {
		return instance;
	}
	private EnabledPseudoClass() { }
	
	private static final String ENABLED_PSEUDO_CLASS_NO_COLON = "enabled";
	private static final String ENABLED_PSEUDO_CLASS = ":"+ENABLED_PSEUDO_CLASS_NO_COLON;
	
	private static final List<String> ALLOWED_TAGS = Arrays.asList("input", "button", "optgroup", "option", "select", "textarea");
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ENABLED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return element.isEnabled() && ALLOWED_TAGS.contains(element.getTagName());
	}
	
	private static final CssFilter enabledPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:enabled
		if (DriverSupportService.getInstance().supportsNatively(driver, ENABLED_PSEUDO_CLASS)) {
			return CompiledCssSelector.createNoFilterSelector(ENABLED_PSEUDO_CLASS);
		}
		return CompiledCssSelector.createFilterOnlySelector(enabledPseudoClassFilter);
	}
	
}