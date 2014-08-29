package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.SqXPathSelector;
import io.github.seleniumquery.selector.XPathSelectorFactory;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PresentPseudoClass implements PseudoClass {
	
	private static final PresentPseudoClass instance = new PresentPseudoClass();
	public static PresentPseudoClass getInstance() {
		return instance;
	}
	private PresentPseudoClass() { }
	
	private static final String PRESENT_PSEUDO_CLASS_NO_COLON = "present";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return PRESENT_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return isPresent(element);
	}
	
	public boolean isPresent(WebElement webElement) {
		try {
			// calling ANY method forces a staleness check
			webElement.isEnabled();
			// passed staleness check, thus present
			return true;
		} catch (StaleElementReferenceException expected) {
			// failed staleness check, so not present
			return false;
		}
	}
	
	private static final ElementFilter presentPseudoClassFilter = new PseudoClassFilter(getInstance());

	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :present is a seleniumQuery extension selector, no browser implements it natively
		return CompiledCssSelector.createFilterOnlySelector(presentPseudoClassFilter);
	}
	
	@Override
	public SqXPathSelector pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("");
	}
	
}