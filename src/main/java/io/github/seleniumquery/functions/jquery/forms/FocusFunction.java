package io.github.seleniumquery.functions.jquery.forms;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.selector.DriverSupportService;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FocusFunction {
	
	private static final Log LOGGER = LogFactory.getLog(FocusFunction.class);

	public static SeleniumQueryObject focus(SeleniumQueryObject caller, List<WebElement> elements) {
		WebDriver driver = caller.getWebDriver();
		for (WebElement webElement : elements) {
			focusElement(driver, webElement);
		}
		return caller;
	}

	private static void focusElement(WebDriver driver, WebElement elementToBeFocused) {
		// #Cross-Driver
		if (DriverSupportService.isHtmlUnitDriver(driver) && "html".equals(elementToBeFocused.getTagName())) {
			LOGGER.warn("The HTML element is not focusable in HtmlUnitDriver, even with a tabindex attribute!");
		}
		elementToBeFocused.sendKeys(Keys.NULL);
	}

}