package org.openqa.selenium.seleniumquery.functions;

import java.util.List;

import org.openqa.selenium.WebElement;

public class TextFunction {
	
	public static String text(List<WebElement> elements) {
		if (elements.isEmpty()) {
			return null;
		}
		return elements.get(0).getText();
	}
	
}