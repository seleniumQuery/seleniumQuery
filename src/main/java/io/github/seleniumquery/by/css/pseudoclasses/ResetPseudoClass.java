package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.XPathComponent;
import io.github.seleniumquery.by.xpath.XPathComponentFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/reset-selector/
 * 
 * @author acdcjunior
 * @since 0.9.0
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
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathComponentFactory.createNoFilter("[(local-name() = 'input' or local-name() = 'button') and @type = 'reset']");
	}
	
}