package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/reset-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class ResetPseudoClass implements PseudoClass {
	
	private static final ResetPseudoClass instance = new ResetPseudoClass();
	public static ResetPseudoClass getInstance() {
		return instance;
	}
	private ResetPseudoClass() { }
	
	private static final String RESET = "reset";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return RESET.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return ("input".equals(element.getTagName()) || "button".equals(element.getTagName()))
				&& RESET.equalsIgnoreCase(element.getAttribute("type"));
	}
	
	private static final ElementFilter resetPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :reset is an extension selector, nobody implements it natively
		return CompiledCssSelector.createFilterOnlySelector(resetPseudoClassFilter);
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[(name() = 'input' or name() = 'button') and @type = 'reset']");
	}
	
}