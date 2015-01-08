package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.SimpleConditionalComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * https://developer.mozilla.org/en-US/docs/Web/CSS/:root
 * 
 * @author acdcjunior
 * @since 0.9.0
 */
public class RootPseudoClass implements PseudoClass<SimpleConditionalComponent> {
	
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
	public SimpleConditionalComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new SimpleConditionalComponent("[local-name() = 'html']");
	}
	
}