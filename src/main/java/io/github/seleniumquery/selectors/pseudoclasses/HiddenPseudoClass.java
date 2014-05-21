package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;
import io.github.seleniumquery.selector.SelectorUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HiddenPseudoClass implements PseudoClass {
	
	private static final HiddenPseudoClass instance = new HiddenPseudoClass();
	public static HiddenPseudoClass getInstance() {
		return instance;
	}
	private HiddenPseudoClass() { }
	
	private static final String HIDDEN_PSEUDO_CLASS_NO_COLON = "hidden";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return HIDDEN_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return !SelectorUtils.isVisible(element);
	}
	
	private static final CssFilter hiddenPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :hidden is an extension selector, no browser implements it natively
		return CompiledCssSelector.createFilterOnlySelector(hiddenPseudoClassFilter);
	}
	
}