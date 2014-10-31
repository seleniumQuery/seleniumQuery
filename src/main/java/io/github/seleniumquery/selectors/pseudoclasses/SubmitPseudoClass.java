package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * http://api.jquery.com/submit-selector/
 * 
 * @author acdcjunior
 * @since 1.0.0
 */
public class SubmitPseudoClass implements PseudoClass {
	
	private static final String SUBMIT = "submit";
	private static final String INPUT = "input";
	private static final String BUTTON = "button";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return SUBMIT.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return (
					INPUT.equals(element.getTagName()) && SUBMIT.equalsIgnoreCase(element.getAttribute("type"))
			   )
				||
			   (
				   BUTTON.equals(element.getTagName()) && (
						   									element.getAttribute("type") == null ||
						   									SUBMIT.equalsIgnoreCase(element.getAttribute("type"))
						   								  )
			  );
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelector("[("
				+ "(local-name() = 'input' and @type = 'submit') or "
				+ "(local-name() = 'button' and (@type = 'submit' or not(@type)) )"
				+ ")]");
	}
	
}