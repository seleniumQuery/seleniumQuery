package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :last
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class LastPseudoClass implements PseudoClass {

	private static final String LAST_PSEUDO_CLASS_NO_COLON = "last";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return LAST_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return EqPseudoClass.isEq(driver, element, pseudoClassSelector, -1);
	}

	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createNoFilterSelectorAppliedToAll("[position() = last()]");
	}

}