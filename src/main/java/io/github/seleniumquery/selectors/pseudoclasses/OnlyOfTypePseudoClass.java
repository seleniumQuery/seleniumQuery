package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;
import io.github.seleniumquery.selector.DriverSupportService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OnlyOfTypePseudoClass implements PseudoClass {
	
	private static final OnlyOfTypePseudoClass instance = new OnlyOfTypePseudoClass();
	public static OnlyOfTypePseudoClass getInstance() {
		return instance;
	}
	private OnlyOfTypePseudoClass() { }
	
	private static final String ONLY_OF_TYPE_PSEUDO_CLASS_NO_COLON = "only-of-type";
	private static final String ONLY_OF_TYPE_PSEUDO_CLASS = ":"+ONLY_OF_TYPE_PSEUDO_CLASS_NO_COLON;
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ONLY_OF_TYPE_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String tagName = element.getTagName();
		return driver.findElements(By.tagName(tagName)).size() == 1;
	}
	
	private static final CssFilter onlyOfTypePseudoClassFilter = new PseudoClassFilter(getInstance());

	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:only-of-type
		if (DriverSupportService.getInstance().supportsNatively(driver, ONLY_OF_TYPE_PSEUDO_CLASS)) {
			return CompiledCssSelector.createNoFilterSelector(ONLY_OF_TYPE_PSEUDO_CLASS);
		}
		return CompiledCssSelector.createFilterOnlySelector(onlyOfTypePseudoClassFilter);
	}
	
}