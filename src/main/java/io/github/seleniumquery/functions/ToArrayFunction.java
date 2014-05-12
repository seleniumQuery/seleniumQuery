package io.github.seleniumquery.functions;

import io.github.seleniumquery.SeleniumQueryObject;

import java.util.List;

import org.openqa.selenium.WebElement;

public class ToArrayFunction {

	public static WebElement[] toArray(@SuppressWarnings("unused") SeleniumQueryObject seleniumQueryObject,
			List<WebElement> elements) {
		return elements.toArray(new WebElement[elements.size()]);
	}

}