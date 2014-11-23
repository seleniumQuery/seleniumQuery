package io.github.seleniumquery.functions.as;

import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static io.github.seleniumquery.by.WebElementUtils.isSelectTag;

public class AsSelect {
	
	public static SeleniumQueryObject selectOptionByVisibleText(SeleniumQueryObject caller, List<WebElement> elements, String text) {
		for (WebElement element : elements) {
			if (isSelectTag(element)) {
				new Select(element).selectByVisibleText(text);
			}
		}
		return caller;
	}
	
	public static SeleniumQueryObject selectOptionByValue(SeleniumQueryObject caller, List<WebElement> elements, String value) {
		for (WebElement element : elements) {
			if (isSelectTag(element)) {
				new Select(element).selectByValue(value);
			}
		}
		return caller;
	}
	
}