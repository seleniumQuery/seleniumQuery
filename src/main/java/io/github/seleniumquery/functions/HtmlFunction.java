package io.github.seleniumquery.functions;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.SeleniumQueryObject;

public class HtmlFunction {

	public static String html(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
		return html(seleniumQueryObject.getWebDriver(), elements);
	}
	
	public static String html(WebDriver driver, List<WebElement> elements) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (elements.isEmpty()) {
			return null;
		}
		return js.executeScript("return arguments[0].innerHTML", elements.get(0)).toString();
	}

}