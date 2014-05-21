package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;
import io.github.seleniumquery.selector.SelectorUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VisiblePseudoClass implements PseudoClass {
	
	private static final VisiblePseudoClass instance = new VisiblePseudoClass();
	public static VisiblePseudoClass getInstance() {
		return instance;
	}
	private VisiblePseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "visible".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return SelectorUtils.isVisible(element);
	}
	
	private static final CssFilter VisiblePseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		return CompiledCssSelector.createFilterOnlySelector(VisiblePseudoClassFilter);
	}
	
}