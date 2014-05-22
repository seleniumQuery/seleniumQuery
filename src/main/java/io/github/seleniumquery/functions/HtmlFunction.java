package io.github.seleniumquery.functions;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.selector.DriverSupportService;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HtmlFunction {

	public static String html(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
		return html(seleniumQueryObject.getWebDriver(), elements);
	}
	
	public static String html(WebDriver driver, List<WebElement> elements) {
		if (elements.isEmpty()) {
			return null;
		}
		return html(driver, elements.get(0));
	}

	public static String html(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String html = js.executeScript("return arguments[0].innerHTML", element).toString();
		// #Cross-Driver
		// HtmlUnitDriver does not append a "\n" to the HTML of the body tag
		// Chrome, Firefox and IE10 seem to.
		// So we add it!
		if (DriverSupportService.isHtmlUnitDriver(driver) && "body".equals(element.getTagName())) {
			html = html + "\n";
		}
		return html;
	}

}