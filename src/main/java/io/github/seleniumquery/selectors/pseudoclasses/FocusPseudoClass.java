package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.DriverSupportService;
import io.github.seleniumquery.selector.SqXPathSelector;
import io.github.seleniumquery.selector.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FocusPseudoClass implements PseudoClass {
	
	private static final FocusPseudoClass instance = new FocusPseudoClass();
	public static FocusPseudoClass getInstance() {
		return instance;
	}
	private FocusPseudoClass() { }
	
	private static final String FOCUS_PSEUDO_CLASS_NO_COLON = "focus";
	private static final String FOCUS_PSEUDO_CLASS = ":"+FOCUS_PSEUDO_CLASS_NO_COLON;
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FOCUS_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		WebElement currentlyActiveElement = driver.switchTo().activeElement();
		return element.equals(currentlyActiveElement);
	}
	
	private static final ElementFilter focusPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:focus
		if (DriverSupportService.getInstance().supportsNatively(driver, FOCUS_PSEUDO_CLASS)) {
			return CompiledCssSelector.createNoFilterSelector(FOCUS_PSEUDO_CLASS);
		}
		return CompiledCssSelector.createFilterOnlySelector(focusPseudoClassFilter);
	}
	
	@Override
	public SqXPathSelector pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// #no-xpath
		return XPathSelectorFactory.createFilterOnlySelector(focusPseudoClassFilter);
	}
	
}