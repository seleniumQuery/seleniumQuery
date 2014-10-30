package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/header-selector/
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class HeaderPseudoClass implements PseudoClass {
	
	private static final String HX_XPATH = "[(local-name() = 'h0' or "
											+ "local-name() = 'h1' or "
											+ "local-name() = 'h2' or "
											+ "local-name() = 'h3' or "
											+ "local-name() = 'h4' or "
											+ "local-name() = 'h5' or "
											+ "local-name() = 'h6' or "
											+ "local-name() = 'h7' or "
											+ "local-name() = 'h8' or "
											+ "local-name() = 'h9')]";

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
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector(HX_XPATH);
	}
	
}