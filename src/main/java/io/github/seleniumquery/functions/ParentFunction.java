package io.github.seleniumquery.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.github.seleniumquery.SQLocalFactory;
import io.github.seleniumquery.SeleniumQueryObject;

public class ParentFunction {

	public static SeleniumQueryObject parent(SeleniumQueryObject caller, WebElement element) {
		WebElement parentElement = element.findElement(By.xpath(".."));
		return SQLocalFactory.getInstance().createWithInvalidSelector(caller.getWebDriver(), parentElement, caller);
	}

}