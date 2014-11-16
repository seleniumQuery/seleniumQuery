package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.by.selector.xpath.XPathExpression;
import io.github.seleniumquery.by.selector.xpath.XPathExpressionFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/reset-selector/
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class ResetPseudoClass implements PseudoClass {
	
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
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathExpressionFactory.createNoFilterSelector("[(local-name() = 'input' or local-name() = 'button') and @type = 'reset']");
	}
	
}