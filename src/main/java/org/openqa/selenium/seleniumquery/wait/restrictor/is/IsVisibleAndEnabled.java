package org.openqa.selenium.seleniumquery.wait.restrictor.is;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.wait.restrictor.Restrictor;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public class IsVisibleAndEnabled implements Restrictor {
	
	public static final Restrictor IS_VISIBLE_AND_ENABLED = new IsVisibleAndEnabled();
	
	private IsVisibleAndEnabled() { }

	@Override
	public boolean fulfillsRestriction(WebElement webElement) {
		return webElement.isDisplayed() && webElement.isEnabled();
	}

	@Override
	public String toString() {
		return "visible and enabled";
	}

}