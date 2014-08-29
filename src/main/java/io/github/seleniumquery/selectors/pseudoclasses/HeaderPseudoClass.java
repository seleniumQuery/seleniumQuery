package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.locator.ElementFilter;
import io.github.seleniumquery.selectorcss.CompiledCssSelector;
import io.github.seleniumquery.selectorxpath.SqXPathSelector;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/header-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class HeaderPseudoClass implements PseudoClass {
	
	private static final HeaderPseudoClass instance = new HeaderPseudoClass();
	public static HeaderPseudoClass getInstance() {
		return instance;
	}
	private HeaderPseudoClass() { }
	
	private static final List<String> HEADER_TAGS = Arrays.asList("h0", "h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9");
	
	private static final String HEADER = "header";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return HEADER.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return HEADER_TAGS.contains(element.getTagName());
	}
	
	private static final ElementFilter headerPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :header is an extension selector, no browser implements it natively
		return CompiledCssSelector.createFilterOnlySelector(headerPseudoClassFilter);
	}
	
	@Override
	public SqXPathSelector pseudoClassToXPath(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[(name() = 'h0' or name() = 'h1' or name() = 'h2' or name() = 'h3' or name() = 'h4'"
				+ " or name() = 'h5' or name() = 'h6' or name() = 'h7' or name() = 'h8' or name() = 'h9')]");
	}
	
}