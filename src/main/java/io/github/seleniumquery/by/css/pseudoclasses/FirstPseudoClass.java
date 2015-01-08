package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.ConditionToAllComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :first
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class FirstPseudoClass implements PseudoClass<ConditionToAllComponent> {

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
	public ConditionToAllComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionToAllComponent("[position() = 1]");
	}

}