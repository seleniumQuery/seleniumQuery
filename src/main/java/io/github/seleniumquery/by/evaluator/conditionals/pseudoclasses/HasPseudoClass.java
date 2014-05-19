package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.CompiledSelectorList;
import io.github.seleniumquery.by.selector.SeleniumQueryCssCompiler;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HasPseudoClass implements PseudoClass {
	
	private static final HasPseudoClass instance = new HasPseudoClass();
	public static HasPseudoClass getInstance() {
		return instance;
	}
	private HasPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("has\\(.*\\)");
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String hasSelector = pseudoClassSelector.getPseudoClassContent();
		
		CompiledSelectorList compileSelectorList = SeleniumQueryCssCompiler.compileSelectorList(driver, hasSelector);
		List<WebElement> elements = compileSelectorList.execute(element);
		
		return !elements.isEmpty();
	}
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// we never consider :has to be supported natively
		SqCSSFilter hasPseudoClassFilter = new PseudoClassFilter(getInstance(), pseudoClassSelector);
		return CompiledSelector.createFilterOnlySelector(hasPseudoClassFilter);
	}
	
}