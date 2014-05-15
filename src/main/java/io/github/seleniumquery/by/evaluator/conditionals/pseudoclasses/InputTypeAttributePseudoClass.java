package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

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
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		return "input".equals(element.getTagName()) && typeAttributeValue.equalsIgnoreCase(element.getAttribute("type"));
	}
	
	private final SqCSSFilter itaPseudoClassFilter = new PseudoClassFilter(this, SELECTOR_NOT_USED, PSEUDO_CLASS_VALUE_NOT_USED);
	
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// we could simply return the selector input[type=typeAttributeValue], but this breaks the CSS parser
		// as something like input[style]:password would result in input[style]input[type=password] which is not valid
		// so we just filter...
		return CompiledSelector.createFilterOnlySelector(itaPseudoClassFilter);
	}
	
}