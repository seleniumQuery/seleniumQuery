package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;
import io.github.seleniumquery.selector.DriverSupportService;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckedPseudoClass implements PseudoClass {

	private static final CheckedPseudoClass instance = new CheckedPseudoClass();
	public static CheckedPseudoClass getInstance() {
		return instance;
	}
	private CheckedPseudoClass() { }

	private static final List<String> CHECKED_ALLOWED_TAGS = Arrays.asList("input", "option");
	
	private static final String CHECKED_PSEUDO_CLASS_NO_COLON = "checked";
	private static final String CHECKED_PSEUDO_CLASS = ":"+CHECKED_PSEUDO_CLASS_NO_COLON;

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return CHECKED_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return isChecked(element);
	}

	public boolean isChecked(WebElement element) {
		return CHECKED_ALLOWED_TAGS.contains(element.getTagName()) && element.isSelected();
	}

	private static final CssFilter checkedPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:checked

		// #Cross-Driver
		// In HtmlUnitDriver, :checked is not consistent, so we consider it as not supported
		// In PhantomJSDriver, :checked does not work for <option> tags, so we consider it as not supported as well
		if (DriverSupportService.isNotHtmlUnitDriver(driver) &&
				DriverSupportService.isNotPhantomJsDriver(driver) &&
				DriverSupportService.getInstance().supportsNatively(driver, CHECKED_PSEUDO_CLASS)) {
			return CompiledCssSelector.createNoFilterSelector(CHECKED_PSEUDO_CLASS);
		}
		return CompiledCssSelector.createFilterOnlySelector(checkedPseudoClassFilter);
	}

}