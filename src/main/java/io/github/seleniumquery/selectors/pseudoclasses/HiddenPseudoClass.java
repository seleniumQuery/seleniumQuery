package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

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
		return !SelectorUtils.isVisible(driver, element);
	}
	
	private static final ElementFilter hiddenPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :hidden is an extension selector, no browser implements it natively
		return CompiledCssSelector.createFilterOnlySelector(hiddenPseudoClassFilter);
	}
	
	public static final String HIDDEN_XPATH_MUST_FILTER = "("
			// we consider to be hidden when...
			
			// element or ancestor has 'display: none'
			+ " ancestor-or-self::*[contains(normalize-space(@style),'display: none') or contains(normalize-space(@style),'display:none')] "
			// element or ancestor has a class (the style of the class will be checked by the filter)
			+ " or ancestor-or-self::*[@class] "
			// it is not under body (and is not <html> itself)
			+ " or (count(ancestor-or-self::body) = 0 and name() != 'html')"
			+ ")";
	
	@Override
	public XPathExpression pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		if (!Object.class.equals("always run")) {
			throw new UnsupportedPseudoClassException(":hidden");
		}
		// #not-pure-xpath // it is not pure because XPath cant see the styles declared in the classes declared
		return XPathSelectorFactory.create("[" + HIDDEN_XPATH_MUST_FILTER + "]", hiddenPseudoClassFilter);
	}
	
}