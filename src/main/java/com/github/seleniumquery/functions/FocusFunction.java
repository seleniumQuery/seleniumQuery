package org.openqa.selenium.seleniumquery.functions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;

public class FocusFunction {

	public static SeleniumQueryObject focus(SeleniumQueryObject caller, WebElement element) {
		return TriggerFunction.trigger(caller, element, "focus");
	}

}