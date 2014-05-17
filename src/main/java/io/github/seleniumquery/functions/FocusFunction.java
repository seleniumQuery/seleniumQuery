package io.github.seleniumquery.functions;

import io.github.seleniumquery.SeleniumQueryObject;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class FocusFunction {

	public static SeleniumQueryObject focus(SeleniumQueryObject caller, List<WebElement> elements) {
		for (WebElement webElement : elements) {
			focusElement(webElement);
		}
		return caller;
	}

	private static void focusElement(WebElement elementToBeFocused) {
		elementToBeFocused.sendKeys(Keys.NULL);
	}

}