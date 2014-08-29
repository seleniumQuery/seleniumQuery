package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selector.SelectorUtils;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VisiblePseudoClass implements PseudoClass {
	
	public static final String NOT_DISPLAY_NONE_XPATH = "not(" + HiddenPseudoClass.HIDDEN_XPATH_MUST_FILTER + ")";
	
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
		return SelectorUtils.isVisible(driver, element);
	}
	
	private static final ElementFilter VisiblePseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		return CompiledCssSelector.createFilterOnlySelector(VisiblePseudoClassFilter);
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		if (!Object.class.equals("always run")) {
			throw new UnsupportedPseudoClassException(":visible");
		}
		// #no-xpath
		System.err.println(":visible is not fully XPath supported (if the style is in a class, it won't know)!!!");
		return XPathSelectorFactory.createNoFilterSelector("[" + NOT_DISPLAY_NONE_XPATH + "]");
	}
	
}