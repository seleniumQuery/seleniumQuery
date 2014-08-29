package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.DriverSupportService;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorxpath.SqXPathSelector;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

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
	
	private static final ElementFilter rootPseudoClassFilter = new PseudoClassFilter(getInstance());

	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:root
		if (DriverSupportService.getInstance().supportsNatively(driver, ROOT_PSEUDO_CLASS)) {
			return CompiledCssSelector.createNoFilterSelector(ROOT_PSEUDO_CLASS);
		}
		return CompiledCssSelector.createFilterOnlySelector(rootPseudoClassFilter);
	}
	
	@Override
	public SqXPathSelector pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[name() = 'html']");
	}
	
}