package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.SimpleConditionalComponent;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :present
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class PresentPseudoClass implements PseudoClass<SimpleConditionalComponent> {
	
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
	public SimpleConditionalComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new SimpleConditionalComponent();
	}
	
}