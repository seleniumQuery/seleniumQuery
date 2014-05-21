package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.CompiledCssSelector;
import io.github.seleniumquery.selector.CssFilter;

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
	
	private static final CssFilter headerPseudoClassFilter = new PseudoClassFilter(getInstance());
	@Override
	public CompiledCssSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :header is an extension selector, no browser implements it natively
		return CompiledCssSelector.createFilterOnlySelector(headerPseudoClassFilter);
	}
	
}