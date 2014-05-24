package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;
import io.github.seleniumquery.selector.DriverSupportService;
import io.github.seleniumquery.selector.SelectorUtils;

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
	
	private static final String OPTGROUP = "optgroup";
	private static final String OPTION = "option";
	public static final List<String> ENABLEABLE_TAGS = Arrays.asList("input", "button", OPTGROUP, OPTION, "select", "textarea");
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ENABLED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		// #Cross-Driver
		// When there is a not disabled <option> under a disabled <optgroup>, HtmlUnitDriver considers
		// the <option> to be enabled, when it is not
		if (DriverSupportService.isHtmlUnitDriver(driver) && OPTION.equals(element.getTagName())) {
			WebElement optionParent = SelectorUtils.parent(element);
			if (OPTGROUP.equals(optionParent.getTagName()) && !optionParent.isEnabled()) {
				return false;
			}
		}
		return element.isEnabled() && ENABLEABLE_TAGS.contains(element.getTagName());
	}
	
	private static final CssFilter enabledPseudoClassFilter = new PseudoClassFilter(getInstance());
	
	// #Cross-Driver
	// HtmlUnitDriver has problems with :enabled, so we consider it can never be handler by the browser
	// by "problems" we mean it is inconsistent, changing depending on what browser it is attempting to emulate
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:enabled
		if (DriverSupportService.isNotHtmlUnitDriver(driver) &&
				DriverSupportService.getInstance().supportsNatively(driver, ENABLED_PSEUDO_CLASS)) {
			return CompiledCssSelector.createNoFilterSelector(ENABLED_PSEUDO_CLASS);
		}
		return CompiledCssSelector.createFilterOnlySelector(enabledPseudoClassFilter);
	}
	
}