package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:root
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class RootPseudoClass implements PseudoClass {
	
	private static final String ROOT_PSEUDO_CLASS_NO_COLON = "root";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return ROOT_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return element.getTagName().equals("html");
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[local-name() = 'html']");
	}
	
}