package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RootPseudoClass implements PseudoClass {
	
	private static final RootPseudoClass instance = new RootPseudoClass();
	public static RootPseudoClass getInstance() {
		return instance;
	}
	private RootPseudoClass() { }
	
	private static final String ROOT_PSEUDO_CLASS_NO_COLON = "root";
	private static final String ROOT_PSEUDO_CLASS = ":"+ROOT_PSEUDO_CLASS_NO_COLON;
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ROOT_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return element.getTagName().equals("html");
	}
	
	private static final SqCSSFilter rootPseudoClassFilter = new PseudoClassFilter(getInstance());

	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:root
		if (DriverSupportMap.getInstance().supportsNatively(driver, ROOT_PSEUDO_CLASS)) {
			return CompiledSelector.createNoFilterSelector(ROOT_PSEUDO_CLASS);
		}
		return CompiledSelector.createFilterOnlySelector(rootPseudoClassFilter);
	}
	
}