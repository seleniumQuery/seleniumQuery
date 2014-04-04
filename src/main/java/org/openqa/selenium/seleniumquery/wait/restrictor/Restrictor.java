package org.openqa.selenium.seleniumquery.wait.restrictor;

import org.openqa.selenium.WebElement;

public interface Restrictor {
	
	boolean fulfillsRestriction(WebElement webElement);
	
}