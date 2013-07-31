package org.openqa.selenium.seleniumquery;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SQueryHtmlElement {
	
	private By by;
	
	private WebDriver driver;
	private WebElement element;
	private String selector;
	
	By getBy() {
		return this.by;
	}
	
	WebDriver getDriver() {
		return this.driver;
	}

	WebElement getElement() {
		return this.element;
	}
	
	void setElement(WebElement webElement) {
		this.element = webElement;
	}

	String getSelector() {
		return this.selector;
	}

	public SQueryHtmlElement(WebDriver driver, String selector) {
		this.driver = driver;
		
		if (selector.matches("^\\(*/.*")) { /* Example: "//..." ou "(//..." ou "(((((//...." */
			// Assuming the selector given is a XPath expression
			this.selector = selector;
			this.by = By.xpath(selector);
		} else {
			this.selector = selector;
			this.by = By.cssSelector(selector);
		}
		
		this.waitUntil().isPresent();
	}
	
	public SQueryHtmlElement val(String value) {
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
	
	public SQueryHtmlElement val(Number valor) {
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
	
	public SQueryHtmlElement click() {
		this.waitUntil().isVisibleAndEnabled();
		this.element.click();
		return this;
	}

	/**
	 * Use <code>sQ('selector').waitUntil().isVisible()</code>
	 */
	@Deprecated
	public SQueryHtmlElement waitUntilVisible() {
		return this.waitUntil().isVisible();
	}
	
	/**
	 * Use <code>sQ('selector').waitUntil().isNotVisible()</code>
	 */
	@Deprecated
	public SQueryHtmlElement waitUntilNotVisible() {
		return this.waitUntil().isNotVisible();
	}
	
	/**
	 * Use <code>sQ('selector').waitUntil().containsText(text)</code>
	 */
	@Deprecated
	public SQueryHtmlElement waitUntilContainsText(final String text) {
		return this.waitUntil().containsText(text);
	}
	
	/**
	 * Use <code>sQ('selector').waitUntil().valueIsNot(value);</code>
	 */
	@Deprecated
	public SQueryHtmlElement waitUntilValueIsNot(final String value) {
		return this.waitUntil().valueIsNot(value);
	}
	
	/**
	 * Use <code>sQ('selector').waitUntil().valueIs(value);</code>
	 */
	@Deprecated
	public SQueryHtmlElement waitUntilValueIs(final String value) {
		return this.waitUntil().valueIs(value);
	}
	
	public String prop(String attributeName) {
		return element.getAttribute(attributeName);
	}
	
	public SQueryHtmlElement trigger(String event) {
		if (by instanceof ByXPath) {
			String id = this.prop("id");
			if (id == null) {
				throw new UnsupportedOperationException("Unable to determine current element's ID!");
			}
			((JavascriptExecutor) driver).executeScript("document.getElementById('" + id + "')." + event + "()");
		} else {
			// CSS selector used
			((JavascriptExecutor) driver).executeScript("document.querySelector('"+selector.replace("\\", "\\\\")+"')."+event+"()");
		}
		return this;
	}
	
	public SQueryHtmlElement focus() {
		return this.trigger("focus");
	}
	
	public String html() {
		return this.element.getAttribute("innerHTML");
	}
	
	public String text() {
		return this.element.getText();
	}
	
	public SQueryWait waitUntil() {
		return new SQueryWait(this);
	}
	
}
