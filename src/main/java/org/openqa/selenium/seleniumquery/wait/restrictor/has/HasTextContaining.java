package org.openqa.selenium.seleniumquery.wait.restrictor.has;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.wait.restrictor.Restrictor;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public final class HasTextContaining implements Restrictor {
	
	public static final Restrictor hasTextContaining(String text) {
		return new HasTextContaining(text);
	}
	
	private String parameterValue;
	
	public HasTextContaining(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	@Override
	public boolean fulfillsRestriction(WebElement webElement) {
		return webElement.getText().contains(parameterValue);
	}
	@Override
	public String toString() {
		return "text containing \""+this.parameterValue+"\"";
	}

}