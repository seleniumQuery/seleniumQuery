package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selectorxpath.XPathExpression;
import io.github.seleniumquery.selectorxpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This represents the pseudoclasses that check for the type attribute, such as
 * <code>:password</code>, that is equivalent to <code>[type="password"]</code>.
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
class InputTypeAttributePseudoClass implements PseudoClass {
	
	private String typeAttributeValue;
	
	public InputTypeAttributePseudoClass(String typeAttributeValue) {
		this.typeAttributeValue = typeAttributeValue;
	}
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return typeAttributeValue.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return "input".equals(element.getTagName()) && typeAttributeValue.equalsIgnoreCase(element.getAttribute("type"));
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[local-name() = 'input' and @type = '"+typeAttributeValue+"']");
	}
	
}