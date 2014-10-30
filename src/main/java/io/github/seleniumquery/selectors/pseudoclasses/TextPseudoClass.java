package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/text-selector/
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
class TextPseudoClass implements PseudoClass {
	
	private static final TextPseudoClass instance = new TextPseudoClass();
	public static TextPseudoClass getInstance() {
		return instance;
	}
	private TextPseudoClass() { }
	
	private static final String TEXT_PSEUDO_CLASS_NO_COLON = "text";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return TEXT_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return "input".equals(element.getTagName()) &&
				(element.getAttribute("type") == null || "text".equalsIgnoreCase(element.getAttribute("type")));
	}
	
	private static final ElementFilter textPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// we never consider :text to be supported natively
		return CompiledCssSelector.createFilterOnlySelector(textPseudoClassFilter);
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[local-name() = 'input' and (@type = 'text' or not([@type]))]");
	}
	
}