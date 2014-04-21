package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class PresentPseudoClass implements PseudoClass {
	
	private static final PresentPseudoClass instance = new PresentPseudoClass();
	public static PresentPseudoClass getInstance() {
		return instance;
	}
	private PresentPseudoClass() { }
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return "present".equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
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
	
}