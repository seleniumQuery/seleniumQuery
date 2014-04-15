package org.openqa.selenium.seleniumquery.functions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;

public class ClickFunction {

	public static SeleniumQueryObject click(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
		for (WebElement element : elements) {
			element.click();
		}
		return seleniumQueryObject;
	}

}