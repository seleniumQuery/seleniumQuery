package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.by.xpath.XPathExpression;
import io.github.seleniumquery.by.xpath.XPathExpressionFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :first
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class FirstPseudoClass implements PseudoClass {

	private static final String FIRST_PSEUDO_CLASS_NO_COLON = "first";

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FIRST_PSEUDO_CLASS_NO_COLON.equalsIgnoreCase(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return EqPseudoClass.isEq(driver, element, pseudoClassSelector, 0);
	}

	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathExpressionFactory.createNoFilterSelectorAppliedToAll("[position() = 1]");
	}

}