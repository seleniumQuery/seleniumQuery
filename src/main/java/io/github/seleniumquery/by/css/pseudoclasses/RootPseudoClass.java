package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.ConditionSimpleComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:root
 * 
 * @author acdcjunior
 * @since 0.9.0
 */
public class RootPseudoClass implements PseudoClass<ConditionSimpleComponent> {
	
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
	public ConditionSimpleComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionSimpleComponent("[local-name() = 'html']");
	}
	
}