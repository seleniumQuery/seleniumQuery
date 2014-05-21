package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

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

	private static final SqCSSFilter checkedPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:checked

		// if it is HtmlUnit, we don't let it take it, as :checked is not consistent in it
		if (DriverSupportMap.isNotHtmlUnitDriver(driver) &&
				DriverSupportMap.getInstance().supportsNatively(driver, CHECKED_PSEUDO_CLASS)) {
			return CompiledSelector.createNoFilterSelector(CHECKED_PSEUDO_CLASS);
		}
		return CompiledSelector.createFilterOnlySelector(checkedPseudoClassFilter);
	}

}