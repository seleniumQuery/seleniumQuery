package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.ConditionalAppliedToAllComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * :last
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public class LastPseudoClass implements PseudoClass<ConditionalAppliedToAllComponent> {

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
	public ConditionalAppliedToAllComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector) {
		return new ConditionalAppliedToAllComponent("[position() = last()]");
	}

}