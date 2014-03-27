package org.openqa.selenium.seleniumquery.functions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;

public class HtmlFunction {

	public static String html(SeleniumQueryObject caller, WebElement element) {
		return element.getAttribute("innerHTML");
	}

}