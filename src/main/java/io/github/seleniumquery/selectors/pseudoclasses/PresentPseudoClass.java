package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.selector.xpath.XPathExpression;
import io.github.seleniumquery.selector.xpath.XPathSelectorFactory;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :present
 *
 * @author acdcjunior
 * @since 1.0.0
 */
public class PresentPseudoClass implements PseudoClass {
	
	private static final String PRESENT_PSEUDO_CLASS_NO_COLON = "present";
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return PRESENT_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		return isPresent(element);
	}
	
	public boolean isPresent(WebElement webElement) {
		try {
			// calling ANY method forces a staleness check
			webElement.isEnabled();
			// passed staleness check, thus present
			return true;
		} catch (StaleElementReferenceException expected) {
			// failed staleness check, so not present
			return false;
		}
	}
	
	@Override
	public XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return XPathSelectorFactory.createEmptyXPathExpression();
	}
	
}