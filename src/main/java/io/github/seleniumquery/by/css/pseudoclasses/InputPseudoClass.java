package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

/**
 * http://api.jquery.com/input-selector/
 * 
 * @author acdcjunior
 * @since 0.9.0
 */
public class InputPseudoClass implements PseudoClass<ConditionSimpleComponent> {
	
	public static final List<String> FORM_ELEMENT_TAGS = Arrays.asList("input", "button", "select", "textarea");
	
	private static final String INPUT = "input";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return INPUT.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return FORM_ELEMENT_TAGS.contains(element.getTagName());
	}
	
	@Override
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionSimpleComponent("[(local-name() = 'input' or local-name() = 'button' or local-name() = 'select' or local-name() = 'textarea')]");
	}
	
}