package org.openqa.selenium.seleniumquery.wait.restrictor.is;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.wait.restrictor.Restrictor;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public class IsVisible implements Restrictor {
	
	public static final Restrictor IS_VISIBLE = new IsVisible();
	
	private IsVisible() { }

	@Override
	public boolean fulfillsRestriction(WebElement webElement) {
		return webElement.isDisplayed();
	}

	@Override
	public String toString() {
		return "visible";
	}

}