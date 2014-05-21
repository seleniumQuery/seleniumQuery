package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

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
	
	private static final SqCSSFilter enabledPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:enabled
		if (DriverSupportMap.getInstance().supportsNatively(driver, ENABLED_PSEUDO_CLASS)) {
			return CompiledSelector.createNoFilterSelector(ENABLED_PSEUDO_CLASS);
		}
		return CompiledSelector.createFilterOnlySelector(enabledPseudoClassFilter);
	}
	
}