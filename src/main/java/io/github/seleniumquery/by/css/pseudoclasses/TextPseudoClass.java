package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.SimpleConditionalComponent;
import io.github.seleniumquery.by.xpath.component.XPathComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :text
 * http://api.jquery.com/text-selector/
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
class TextPseudoClass implements PseudoClass {
	
	private static final String TEXT_PSEUDO_CLASS_NO_COLON = "text";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return TEXT_PSEUDO_CLASS_NO_COLON.equalsIgnoreCase(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return "input".equals(element.getTagName()) &&
				(element.getAttribute("type") == null || "text".equalsIgnoreCase(element.getAttribute("type")));
	}
	
	@Override
	public XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new SimpleConditionalComponent("[self::input and (translate(@type,'TEXT','text') = 'text' or not(@type))]");
	}
	
}