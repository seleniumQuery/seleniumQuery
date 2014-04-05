package org.openqa.selenium.seleniumquery.wait.restrictor.is;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.wait.restrictor.Restrictor;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public class IsEnabled implements Restrictor {
	
	public static final Restrictor IS_ENABLED = new IsEnabled();
	
	private IsEnabled() { }

	@Override
	public boolean fulfillsRestriction(WebElement webElement) {
		return webElement.isEnabled();
	}

	@Override
	public String toString() {
		return "enabled";
	}
	
}