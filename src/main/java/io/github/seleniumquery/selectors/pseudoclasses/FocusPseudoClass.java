package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.filter.ElementFilter;
import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:focus
 */
public class FocusPseudoClass implements PseudoClass {
	
	private static final FocusPseudoClass instance = new FocusPseudoClass();
	public static FocusPseudoClass getInstance() {
		return instance;
	}
	private FocusPseudoClass() { }
	
	private static final String FOCUS_PSEUDO_CLASS_NO_COLON = "focus";
	
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
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		UnsupportedXPathPseudoClassException.xPathFiltersAreNotImplementedYed(":focus");
		
		// #no-xpath
		return XPathSelectorFactory.createFilterOnlySelector(focusPseudoClassFilter);
	}
	
}