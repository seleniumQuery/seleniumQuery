package org.openqa.selenium.seleniumquery.wait.restrictor.is;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.wait.restrictor.Restrictor;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public class IsPresent implements Restrictor {
	
	public static final Restrictor IS_PRESENT = new IsPresent();
	
	private IsPresent() { }
	
	@Override
	public boolean fulfillsRestriction(WebElement webElement) {
		try {
			// Calling any method forces a staleness check
			webElement.isEnabled();
			// passed staleness check, thus present
			return true;
		} catch (StaleElementReferenceException expected) {
			// failed staleness check, so not present
			return false;
		}
	}

	@Override
	public String toString() {
		return "present";
	}

}