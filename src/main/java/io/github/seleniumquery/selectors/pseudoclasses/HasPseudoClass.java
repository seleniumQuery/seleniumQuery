package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CompiledCssSelectorList;
import io.github.seleniumquery.selector.CssSelectorCompilerService;
import io.github.seleniumquery.selector.SqXPathSelector;
import io.github.seleniumquery.selector.XPathSelectorCompilerService;
import io.github.seleniumquery.selector.XPathSelectorFactory;

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
		
		CompiledCssSelectorList compileSelectorList = CssSelectorCompilerService.compileSelectorList(driver, hasSelector);
		List<WebElement> elements = compileSelectorList.execute(element);
		
		return !elements.isEmpty();
	}
	
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// we never consider :has to be supported natively
		ElementFilter hasPseudoClassFilter = new PseudoClassFilter(getInstance(), pseudoClassSelector);
		return CompiledCssSelector.createFilterOnlySelector(hasPseudoClassFilter);
	}
	
	@Override
	public SqXPathSelector pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		String notSelector = pseudoClassSelector.getPseudoClassContent();
		String insideHasXPath = XPathSelectorCompilerService.compileSelectorList(driver, notSelector).toXPath();
		insideHasXPath = insideHasXPath.substring(1, insideHasXPath.length()-1);
		return XPathSelectorFactory.createNoFilterSelector("[."+insideHasXPath+"]");
	}
	
}