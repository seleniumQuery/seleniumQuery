package io.github.seleniumquery.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.github.seleniumquery.SeleniumQueryObject;

public class ParentFunction {

	public static SeleniumQueryObject parent(SeleniumQueryObject caller, WebElement element) {
		WebElement parentElement = element.findElement(By.xpath(".."));
		return new SeleniumQueryObject(caller.getWebDriver(), parentElement);
	}

}