package org.openqa.selenium.seleniumquery.element;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryElementWait;
import org.openqa.selenium.support.ui.Select;

public class SeleniumQueryHtmlElement {
	
	private By by;
	private WebDriver driver;
	private WebElement element;
	private String selector;
	
	public final SeleniumQueryElementWait waitUntil = new SeleniumQueryElementWait(this);
	
	public SeleniumQueryHtmlElement(WebDriver driver, String selector, WebElement element) {
		this.driver = driver;
		this.selector = selector;
		this.by = SeleniumQueryBy.byEnhancedSelector(selector);
		this.element = element;
		
		// this.waitUntil.isPresent(); // why was this here??
	}
	
	public SeleniumQueryHtmlElement(WebDriver driver, WebElement element) {
		this.driver = driver;
		this.element = element;
		
		this.selector = null;
		this.by = new By() {
			@Override
			public List<WebElement> findElements(SearchContext context) {
				throw new RuntimeException("This SeleniumQueryHtmlElement was instantiated without a selector,"
						+ " thus this function is unavailable.");
			}
		};
	}
	
	public By getBy() {
		return this.by;
	}
	
	public WebDriver getWebDriver() {
		return this.driver;
	}

	public WebElement getWebElement() {
		return this.element;
	}
	
	// This was not public!
	public void setElement(WebElement webElement) {
		this.element = webElement;
	}

	public String getSelector() {
		return this.selector;
	}
	
	public SeleniumQueryHtmlElement val(String value) {
		if ("select".equals(element.getTagName())) {
			new Select(element).selectByVisibleText(value);
		} else if ("input".equals(element.getTagName()) && "file".equals(element.getAttribute("type"))) {
			element.sendKeys(value);
		} else {
			element.clear();
			element.sendKeys(value);
		}
		return this;
	}
	
	public SeleniumQueryHtmlElement val(Number valor) {
		return this.val(valor.toString());
	}
	
	public String val() {
		if ("input".equals(element.getTagName())) {
			return element.getAttribute("value");
		} else if ("select".equals(element.getTagName())) {
			return new Select(element).getFirstSelectedOption().getText();
		} else {
			return element.getText();
		}
	}
	
	public SeleniumQueryHtmlElement click() {
		this.waitUntil.isVisibleAndEnabled();
		this.element.click();
		return this;
	}

	public String prop(String attributeName) {
		return element.getAttribute(attributeName);
	}
	
	public SeleniumQueryHtmlElement trigger(String event) {
		((JavascriptExecutor) driver).executeScript("return arguments[0]."+event+"();", this.element); 
		return this;
	}
	
	public SeleniumQueryHtmlElement parent() {
		WebElement parentElement = this.element.findElement(By.xpath(".."));
		return new SeleniumQueryHtmlElement(driver, parentElement);
	}
	
	public SeleniumQueryHtmlElement focus() {
		return this.trigger("focus");
	}
	
	public String html() {
		return this.element.getAttribute("innerHTML");
	}
	
	public String text() {
		return this.element.getText();
	}
	
}