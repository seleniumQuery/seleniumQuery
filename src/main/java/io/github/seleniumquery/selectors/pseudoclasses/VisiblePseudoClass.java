package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

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
	
	private static final SqCSSFilter VisiblePseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		return CompiledSelector.createFilterOnlySelector(VisiblePseudoClassFilter);
	}
	
}