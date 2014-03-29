package org.openqa.selenium.seleniumquery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;
import org.openqa.selenium.seleniumquery.functions.ClickFunction;
import org.openqa.selenium.seleniumquery.functions.FirstFunction;
import org.openqa.selenium.seleniumquery.functions.NotFunction;
import org.openqa.selenium.seleniumquery.functions.TextFunction;
import org.openqa.selenium.seleniumquery.functions.ValFunction;

/**
 * Represents the SeleniumQuery Object: a list of WebElements with special methods.
 *  
 * @author acdcjunior
 */
public class SeleniumQueryObject implements Iterable<WebElement> {
	
	private SeleniumQueryBy by;
	private WebDriver driver;
	public List<WebElement> elements;
	
	/**
	 * List of functions that will hold the flow until the specified condition is met.
	 */
	public final SeleniumQueryWaitUntil waitUntil = new SeleniumQueryWaitUntil(this);
		
	public SeleniumQueryObject(WebDriver driver, String selector) {
		this(driver, selector, driver.findElements(SeleniumQueryBy.byEnhancedSelector(selector)));
	}

	public SeleniumQueryObject(WebDriver driver, String selector, List<WebElement> webElements) {
		this.driver = driver;
		this.by = SeleniumQueryBy.byEnhancedSelector(selector);
		this.elements = webElements;
	}
	
	public SeleniumQueryObject(WebDriver driver, List<WebElement> webElements) {
		this.driver = driver;
		
		this.by = new SeleniumQueryBy(null) {
			@Override
			public List<WebElement> findElements(SearchContext context) {
				throw new RuntimeException("This SeleniumQueryHtmlElementList was instantiated without a selector,"
						+ " this function is unavailable.");
			}
		};
		
		this.elements = webElements;
	}
	
	public SeleniumQueryObject(WebDriver driver, WebElement element) {
		this(driver, new ArrayList<WebElement>(Arrays.asList(element)));
	}

	public int size() {
		return this.elements.size();
	}
	
	public SeleniumQueryObject not(String selector) {
		return NotFunction.not(this, this.elements, selector);
	}
	
	public SeleniumQueryObject first() {
		return FirstFunction.first(this, this.elements);
	}
	
	public String text() {
		return TextFunction.text(this.elements);
	}
	
	public SeleniumQueryObject click() {
		return ClickFunction.click(this, this.elements);
	}
	
	public SeleniumQueryObject val(String value) {
		return ValFunction.val(this, this.elements, value);
	}
	
	public SeleniumQueryObject val(Number value) {
		return ValFunction.val(this, this.elements, value);
	}
	
	public String val() {
		return ValFunction.val(this.elements);
	}

	@Override
	public Iterator<WebElement> iterator() {
		return this.elements.iterator();
	}

	/**
	 * Returns the driver used to retrieve this object.
	 * @return the driver used to retrieve this object.
	 */
	public WebDriver getWebDriver() {
		return this.driver;
	}
	
	public SeleniumQueryBy getBy() {
		return this.by;
	}

	/**
	 * Updates the list of WebElements of this object.
	 * @param elements the new list of WebElements.
	 */
	void setElements(List<WebElement> elements) {
		this.elements = elements;
	}
   
}