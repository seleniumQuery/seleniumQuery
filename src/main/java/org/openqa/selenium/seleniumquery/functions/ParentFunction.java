package org.openqa.selenium.seleniumquery.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.SeleniumQueryObject;

public class ParentFunction {

	public static SeleniumQueryObject parent(SeleniumQueryObject caller, WebElement element) {
		WebElement parentElement = element.findElement(By.xpath(".."));
		return new SeleniumQueryObject(caller.getWebDriver(), parentElement);
	}

}