package org.openqa.selenium.seleniumquery;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SQueryHtmlElementList {
	
	private By by;
	private WebDriver driver;
	private List<WebElement> elements;
	private String selector;
	
	public SQueryHtmlElementList(WebDriver driver, String cssSelector) {
		this.driver = driver;
		this.selector = cssSelector;
		this.by = By.cssSelector(this.selector);
		this.elements = this.driver.findElements(by);
	}
	
	public int size() {
		return this.elements.size();
	}
	
	public SQueryHtmlElementList click() {
		for (WebElement element : this.elements) {
			element.click();
		}
		return this;
	}
	
	public SQueryHtmlElementList not(String cssSelector) {
		SQueryHtmlElementList newList = new SQueryHtmlElementList(this.driver, this.selector);
		List<WebElement> elementsNot = this.driver.findElements(By.cssSelector(cssSelector));
		newList.elements.removeAll(elementsNot);
		return newList;
	}
	
}
