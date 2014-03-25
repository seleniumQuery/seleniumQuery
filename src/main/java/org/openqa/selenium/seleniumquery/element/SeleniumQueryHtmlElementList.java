package org.openqa.selenium.seleniumquery.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.by.SeleniumQueryBy;
import org.openqa.selenium.seleniumquery.element.single.SeleniumQueryHtmlElement;
import org.openqa.selenium.seleniumquery.wait.SeleniumQueryElementListWait2;

public class SeleniumQueryHtmlElementList implements Iterable<SeleniumQueryHtmlElement> {
	
	private SeleniumQueryBy by;
	private WebDriver driver;
	public List<SeleniumQueryHtmlElement> elements;
	public List<WebElement> elementz;
	private String selector;
	
	public final int length;
	
	public final SeleniumQueryElementListWait2 waitUntil = new SeleniumQueryElementListWait2(this);
		
	public SeleniumQueryHtmlElementList(WebDriver driver, String selector) {
		this(driver, selector, buildSeleniumQueryHtmlElements(driver, selector));
	}

	private static List<SeleniumQueryHtmlElement> buildSeleniumQueryHtmlElements(WebDriver driver, String selector) {
		SeleniumQueryBy seleniumQueryBy = SeleniumQueryBy.byEnhancedSelector(selector);
		
		List<WebElement> webElementsFound = driver.findElements(seleniumQueryBy);
		List<SeleniumQueryHtmlElement> webElements = new ArrayList<SeleniumQueryHtmlElement>(webElementsFound.size());
		
		int elementIndex = 0;
		for (WebElement webElement : webElementsFound) {
			webElements.add(new SeleniumQueryHtmlElement(driver, seleniumQueryBy.getSelectorForElementAtPosition(elementIndex), webElement));
			elementIndex++;
		}
		
		return webElements;
	}
	
	private SeleniumQueryHtmlElementList(WebDriver driver, String selector, List<SeleniumQueryHtmlElement> seleniumQueryHtmlElements) {
		this.driver = driver;
		this.selector = selector;
		this.by = SeleniumQueryBy.byEnhancedSelector(selector);
		this.elements = seleniumQueryHtmlElements;
		this.length = this.elements.size();
	}
	
	public SeleniumQueryHtmlElementList(WebDriver driver, List<SeleniumQueryHtmlElement> seleniumQueryHtmlElements) {
		this.driver = driver;
		
		this.selector = null;
		this.by = new SeleniumQueryBy(null) {
			@Override
			public List<WebElement> findElements(SearchContext context) {
				throw new RuntimeException("This SeleniumQueryHtmlElement was instantiated without a selector,"
						+ " thus this function is unavailable.");
			}
		};
		
		this.elements = seleniumQueryHtmlElements;
		this.length = this.elements.size();
	}
	
	public SeleniumQueryHtmlElementList(WebDriver driver, WebElement element) {
		this(driver, new ArrayList<SeleniumQueryHtmlElement>(Arrays.asList(new SeleniumQueryHtmlElement(driver, element))));
	}

	public int size() {
		return this.length;
	}
	
	public SeleniumQueryHtmlElementList not(String selector) {
		SeleniumQueryHtmlElementList newList = new SeleniumQueryHtmlElementList(this.driver, this.selector);
		List<WebElement> elementsNot = this.driver.findElements(SeleniumQueryBy.byEnhancedSelector(selector));
		newList.elements.removeAll(elementsNot);
		return newList;
	}
	
	public SeleniumQueryHtmlElementList first() {
		if (this.elements.size() == 0) {
			return new SeleniumQueryHtmlElementList(driver, this.by.getSelectorForElementAtPosition(0));
		}
		return new SeleniumQueryHtmlElementList(driver, this.by.getSelectorForElementAtPosition(0), Arrays.asList(this.elements.get(0)));
	}
	
	public String text() {
		if (this.elements.isEmpty()) {
			return null;
		}
		return this.elements.get(0).text();
	}
	
	public SeleniumQueryHtmlElementList click() {
		for (SeleniumQueryHtmlElement seleniumQueryHtmlElement : this.elements) {
			seleniumQueryHtmlElement.click();
		}
		return this;
	}
	
	public SeleniumQueryHtmlElementList val(String value) {
		for (SeleniumQueryHtmlElement seleniumQueryHtmlElement : this.elements) {
			seleniumQueryHtmlElement.val(value);
		}
		return this;
	}
	
	public String val() {
		if (this.elements.isEmpty()) {
			return null;
		}
		return SeleniumQueryHtmlElement.val(this.elements.get(0));
	}

	@Override
	public Iterator<SeleniumQueryHtmlElement> iterator() {
		return this.elements.iterator();
	}

	// for wait
	public By getBy() {
		return this.by;
	}

	// for wait
	public WebDriver getWebDriver() {
		return this.driver;
	}

	public void setElements(List<WebElement> element) {
		//this.elements = element;
	}
   
}