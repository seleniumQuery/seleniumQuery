package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This represents the pseudoclasses that check for the type attribute, such as
 * <code>:password</code>, that is equivalent to <code>[type="password"]</code>.
 * 
 * @since 1.0.0
 * @author acdcjunior
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
	
	private final SqCSSFilter itaPseudoClassFilter = new PseudoClassFilter(this);
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// we could simply return the selector input[type=typeAttributeValue], but this breaks the CSS parser
		// as something like input[style]:password would result in input[style]input[type=password] which is not valid CSS.
		// All we can do is filter...
		return CompiledSelector.createFilterOnlySelector(itaPseudoClassFilter);
	}
	
}