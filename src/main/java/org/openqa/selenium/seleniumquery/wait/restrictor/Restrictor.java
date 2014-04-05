package org.openqa.selenium.seleniumquery.wait.restrictor;

import org.openqa.selenium.WebElement;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public interface Restrictor {
	
	boolean fulfillsRestriction(WebElement webElement);
	
}