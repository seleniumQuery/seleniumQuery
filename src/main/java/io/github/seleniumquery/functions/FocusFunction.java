package io.github.seleniumquery.functions;

import org.openqa.selenium.WebElement;
import io.github.seleniumquery.SeleniumQueryObject;

public class FocusFunction {

	public static SeleniumQueryObject focus(SeleniumQueryObject caller, WebElement element) {
		return TriggerFunction.trigger(caller, element, "focus");
	}

}