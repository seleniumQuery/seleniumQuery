package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.XPathComponent;
import io.github.seleniumquery.by.xpath.component.XPathComponentFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/button-selector/
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class ButtonPseudoClass implements PseudoClass {
	
	private static final String INPUT = "input";
	private static final String BUTTON = "button";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return BUTTON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return (INPUT.equals(element.getTagName()) && BUTTON.equalsIgnoreCase(element.getAttribute("type")))
				||
			   BUTTON.equals(element.getTagName());
	}
	
	@Override
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathComponentFactory.createNoFilter("[(" +
				"(self::input and translate(@type,'BUTTON','button') = 'button') " +
				"or " +
				"self::button" +
				")]");
	}
	
}