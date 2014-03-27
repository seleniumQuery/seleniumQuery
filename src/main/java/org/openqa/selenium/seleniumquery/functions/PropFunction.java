package org.openqa.selenium.seleniumquery.functions;

import org.openqa.selenium.WebElement;

public class PropFunction {

	public static String prop(WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}

}