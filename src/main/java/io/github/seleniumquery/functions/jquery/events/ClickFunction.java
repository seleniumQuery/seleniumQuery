package io.github.seleniumquery.functions.jquery.events;

import java.util.List;

import org.openqa.selenium.WebElement;
import io.github.seleniumquery.SeleniumQueryObject;

public class ClickFunction {

	public static SeleniumQueryObject click(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
		for (WebElement element : elements) {
			element.click();
		}
		return seleniumQueryObject;
	}

}